package com.mini.prj.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import java.util.Date;


@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{

    private Double decouvert;

    public double getDecouvert() {
        return decouvert;
    }
    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    public CompteCourant(String codeCompte, Date dateCreation, double solde,
                         double decouvert) {
        super(codeCompte, dateCreation, solde);
        this.decouvert = decouvert;
    }
    public CompteCourant() {
super();
        // TODO Auto-generated constructor stub
    }
}

