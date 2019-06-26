package com.jacky;

import com.jacky.health.TemplateHealthCheck;
import com.jacky.resources.HelloResource;
import com.jacky.resources.TemplateResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloApplication extends Application<Hello> {

    public static void main(String args[]) throws Exception {
        new HelloApplication().run(args);
    }

    @Override
    public String getName(){
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<Hello> bootstrap){
        bootstrap.addBundle(hibernateBundle);
    }

    ////////////////////////////////////////////////////////////////////////

    private final HibernateBundle<Hello> hibernateBundle = new HibernateBundle<Hello>(
            Template.class
    ) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                Hello configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void run(Hello configuration, Environment environment) throws Exception {
        final HelloResource resource = new HelloResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

        final TemplateDAO templateDAO = new TemplateDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new TemplateResource(templateDAO));
    }
}
