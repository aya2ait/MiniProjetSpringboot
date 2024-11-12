package com.mini.prj.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


@Entity
@Table
public class Groupe implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long numGroupe;
    private String nomGroupe;
    @ManyToMany(mappedBy="groupes")
   // @JsonBackReference
    private Collection<Employe> employes;
    public Groupe(String nomGroupe) {
        super();
        this.nomGroupe = nomGroupe;
    }
    public Groupe() {
        super();
// TODO Auto-generated constructor stub
    }
    public Long getNumGroupe() {
        return numGroupe;
    }

    public void setNumGroupe(Long numGroupe) {
        this.numGroupe = numGroupe;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public Collection<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(Collection<Employe> employes) {
        this.employes = employes;
    }
}
