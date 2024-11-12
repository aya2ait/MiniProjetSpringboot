package com.mini.prj.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typeCpte")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CompteCourant.class, name = "COURANT"),
        @JsonSubTypes.Type(value = CompteEpargne.class, name = "EPARGNE")
})
public abstract class Compte {


    @Id
    private String codeCompte;
    private Date dateCreation;
    private double solde;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name="CODE_CLI")
    private Client client;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name="CODE_EMP")
    private Employe employe;

    @OneToMany(mappedBy="compte",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonBackReference
    private Collection<Operation> operations;
    public Compte(String codeCompte, Date dateCreation, double solde) {
        super();
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
    }
    public Compte() {
        super();
    }
    public String getCodeCompte() {
        return codeCompte;
    }
    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    public double getSolde() {
        return solde;
    }
    public void setSolde(double solde) {

        this.solde = solde;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public Collection<Operation> getOperations() {
        return operations;
    }
    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }


}
