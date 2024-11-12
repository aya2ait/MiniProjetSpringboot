package com.mini.prj.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@DiscriminatorValue("R")
public class Retrait extends Operation{
    public Retrait() {}

    public Retrait(Date dateOperation, double montant) {
        super(dateOperation, montant);
    }
}
