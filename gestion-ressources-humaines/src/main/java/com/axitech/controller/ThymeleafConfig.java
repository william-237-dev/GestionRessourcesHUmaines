package com.axitech.controller;


import jakarta.servlet.ServletContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

public class ThymeleafConfig {

    private static TemplateEngine templateEngine;

    public static TemplateEngine getTemplateEngine(ServletContext servletContext) {

        if (templateEngine == null) {

            JakartaServletWebApplication application =
                    JakartaServletWebApplication.buildApplication(servletContext);

            WebApplicationTemplateResolver resolver =
                    new WebApplicationTemplateResolver(application);

            resolver.setPrefix("/META-INF/templates/");
            resolver.setSuffix(".html");
            resolver.setCharacterEncoding("UTF-8");
            resolver.setTemplateMode("HTML");
            resolver.setCacheable(false);

            templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(resolver);
        }

        return templateEngine;
    }
}
