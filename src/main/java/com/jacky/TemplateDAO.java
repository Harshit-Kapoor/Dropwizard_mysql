package com.jacky;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class TemplateDAO extends AbstractDAO<Template> {

    public TemplateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Template> findAll(){
        return list(namedQuery("com.jacky.Template.findAll"));
    }
}
