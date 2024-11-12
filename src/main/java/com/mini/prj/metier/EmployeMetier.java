package com.mini.prj.metier;


import com.mini.prj.entities.Employe;

import java.util.List;

public interface EmployeMetier {

    public Employe saveEmploye(Employe e);
    public List<Employe> listEmployes();
    public void deleteEmploye(Long id);
}
