package com.jacky.resources;


import com.jacky.Template;
import com.jacky.TemplateDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/template")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {

    private TemplateDAO templateDAO;

    public TemplateResource(TemplateDAO templateDAO){
        this.templateDAO = templateDAO;
    }

    @GET
    @Path("/show")
    @UnitOfWork
    public List<Template> show() {

        return templateDAO.findAll();

    }
}
