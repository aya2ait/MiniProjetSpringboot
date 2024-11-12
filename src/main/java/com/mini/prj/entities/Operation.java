package com.mini.prj.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Retrait.class, name = "Retrait"),
        @JsonSubTypes.Type(value = Versement.class, name ="Versement")
})
public abstract class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long numOperation;
    private Date dateOperation;
    private double montant;


    @ManyToOne
    @JoinColumn(name="NUM_CPTE")
    private Compte compte;

   @ManyToOne
  // @JsonBackReference
    @JoinColumn(name="CODE_EMP")
    private Employe employe;


    public Operation(Date dateOperation, double montant) {
        super();
        this.dateOperation = dateOperation;
        this.montant = montant;
    }
    public Operation() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Long getNumeroOperation() {
        return numOperation;
    }
    public void setNumeroOperation(Long numeroOperation) {
        this.numOperation = numeroOperation;
    }
    public Date getDateOperation() {
        return dateOperation;
    }
    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public Compte getCompte() {
        return compte;
    }
    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}