package com.mini.prj.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;


@Entity
@Table

public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numEmploye;
    private String nomEmploye;

    @ManyToOne
    @JoinColumn(name = "CODE_EMP_SUP")
    @JsonIgnore
    private Employe employeSup;

    @ManyToMany
    @JsonBackReference

    @JoinTable(name = "T_EMP_GR", joinColumns = @JoinColumn(name = "NUM_EMP"), inverseJoinColumns = @JoinColumn(name = "NUM_GR"))
    private Collection<Groupe> groupes;

    public Employe(String nomEmploye) {
        super();
        this.nomEmploye = nomEmploye;
    }

    public Employe() {
        super();
// TODO Auto-generated constructor stub
    }

    public Long getNumEmploye() {
        return numEmploye;
    }

    public void setNumEmploye(Long numEmploye) {
        this.numEmploye = numEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public Employe getEmployeSup() {
        return employeSup;
    }

    public void setEmployeSup(Employe employeSup) {
        this.employeSup = employeSup;
    }

    public Collection<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Collection<Groupe> groupes) {
        this.groupes = groupes;
    }
}


