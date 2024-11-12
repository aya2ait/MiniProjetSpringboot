package com.mini.prj.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table

@DiscriminatorValue("V")
public class Versement extends Operation {

    public Versement() {}

    public Versement(Date dateOperation, double montant) {
        super(dateOperation, montant);
    }

    // Additional methods if needed...
}
