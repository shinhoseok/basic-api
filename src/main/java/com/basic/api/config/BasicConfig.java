package com.basic.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Import({
	BasicConfigCommon.class,
	BasicConfigDatasource.class,
	BasicConfigTransaction.class
})
@PropertySources({
	@PropertySource("classpath:/egovframework/egovProps/globals.properties")
}) //CAUTION: min JDK 8
public class BasicConfig {

}
