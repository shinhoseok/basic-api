package com.basic.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BasicWebServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicWebServletContextListener.class);

    public BasicWebServletContextListener() {
        setEgovProfileSetting();
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        if (System.getProperty("spring.profiles.active") == null) {
            setEgovProfileSetting();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (System.getProperty("spring.profiles.active") != null) {
            System.clearProperty("spring.profiles.active");
        }
    }

    public void setEgovProfileSetting() {
        try {
            LOGGER.debug("===========================Start EgovServletContextLoad START ===========");
            System.setProperty("spring.profiles.active", "basic.datasource");
            LOGGER.debug("Setting spring.profiles.active>" + System.getProperty("spring.profiles.active"));
            LOGGER.debug("Setting basic.datasource>" + System.getProperty("basic.datasource"));
            LOGGER.debug("===========================END   EgovServletContextLoad END ===========");
        } catch (IllegalArgumentException e) {
            LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("[" + e.getClass() + "] search fail : " + e.getMessage());
        }
    }
}
