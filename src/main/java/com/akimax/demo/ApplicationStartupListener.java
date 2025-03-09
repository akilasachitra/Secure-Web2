package com.akimax.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {


    private final Environment environment;

    public ApplicationStartupListener(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String scheme = environment.getProperty("server.ssl.enabled", Boolean.class, false) ? "https" : "http";
        String port = environment.getProperty("server.port", "8080"); // Default to 8080 if not configured
        String contextPath = environment.getProperty("server.servlet.context-path", ""); // Default to root context

        String message = String.format("Web Application available on localhost via %s://localhost:%s/%s", scheme, port, contextPath);
        System.out.println(message);
    }
}