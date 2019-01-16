package edu.palevobot.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Date;

@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    public Base(){
        this.dateOfCreation = new Date(System.currentTimeMillis());
    }

    public Base(Date dateOfCreation){this.dateOfCreation = dateOfCreation; }
    public Base(Integer id){
        this();
        this.id = id;
    }

    public Base(int id, Date dateOfCreation) {
        this.id = id;
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }
}
