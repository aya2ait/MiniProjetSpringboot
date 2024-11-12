package com.mini.prj.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;


@Entity
@Table

@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
    private double taux;
    public double getTaux() {
        return taux;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }
    public CompteEpargne(String codeCompte, Date dateCreation, double solde,
                         double taux) {
        super(codeCompte, dateCreation, solde);
        this.taux = taux;
    }
    public CompteEpargne() {
        super();
// TODO Auto-generated constructor stub
    }
}
