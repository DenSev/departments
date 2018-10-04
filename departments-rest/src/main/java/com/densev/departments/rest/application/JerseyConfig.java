package com.densev.departments.rest.application;


import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JerseyConfig.class);

    public JerseyConfig() {
        registerContentRoot();
    }

    private void registerContentRoot() {
        LOG.warn("registering content root");
        packages("com.densev.departments.rest.web");
    }

}
