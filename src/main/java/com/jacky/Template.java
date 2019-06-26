package com.jacky;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Template")
@NamedQueries({
    @NamedQuery(name = "com.jacky.Template.findAll", query = "select t from Template t")
})

public class Template {
    @Id
    @Column(name = "Gesture")
    private String gesture;

    public String getGesture(){
        return gesture;
    }

    public void setGesture(String gesture){
        this.gesture = gesture;
    }

    public Template() {
    }

    public Template(String gesture){
        this.gesture = gesture;
    }
}
