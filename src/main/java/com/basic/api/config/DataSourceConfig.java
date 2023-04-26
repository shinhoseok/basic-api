package com.basic.api.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

//@EnableJpaAuditing //JPA Auddit 적용(등록자/수정자 세션값 세팅)
//@Configuration
//@EntityScan(basePackages = { "com.basic.entity" })
//@PropertySource(value = {"classpath:/application.yml"})
public class DataSourceConfig {
    @Value("${basic.jpa.database-platform}")
    private String dialect;
    @Value("${basic.jpa.showSql:true}")
    private String showSql;
    @Value("${basic.jpa.properties.hibernate.format_sql:true}")
    private String formatSql;
    @Value("${basic.jpa.hibernate.ddl-auto:none}")
    private String ddlAuto;

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "basic.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(dialect);

        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.basic.entity");

        Properties jpaProperties = new Properties();

        jpaProperties.setProperty("hibernate.show_sql", showSql);
        jpaProperties.setProperty("hibernate.format_sql", formatSql);
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource());
        return jpaTransactionManager;
    }

    @Bean(name = "transactionAdvice")
    public TransactionInterceptor transactionAdvice() {
        TransactionInterceptor txAdvice = new TransactionInterceptor();
        NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();

        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        readOnly.setReadOnly(true);

        HashMap<String, TransactionAttribute> txMethods = new HashMap<String, TransactionAttribute>();
        txMethods.put("insert*", required);
        txMethods.put("update*", required);
        txMethods.put("delete*", required);

        txMethods.put("select*", readOnly);

        txAttributeSource.setNameMap(txMethods);

        txAdvice.setTransactionAttributeSource(txAttributeSource);
        txAdvice.setTransactionManager(jpaTransactionManager());

        return txAdvice;
    }

    @Bean(name = "transactionAdviceAdvisor")
    public Advisor transactionAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.basic.service.impl.*Impl.*(..))");
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }

    @Bean(name = "jpaQueryFactory")
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
