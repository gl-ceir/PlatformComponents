package com.gl.ceir.config.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
//@RefreshScope
// @EnableScheduling

@PropertySources({
    @PropertySource(value = {"file:application.properties"}, ignoreResourceNotFound = true),
    @PropertySource(value = {"file:configuration.properties"}, ignoreResourceNotFound = true)
})


public class PropertiesReader {


}
