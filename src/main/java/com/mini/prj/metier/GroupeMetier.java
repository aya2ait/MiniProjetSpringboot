package com.mini.prj.metier;

import com.mini.prj.entities.Groupe;

import java.util.List;

public interface GroupeMetier {
    public Groupe saveGroupe (Groupe c);
    public List<Groupe> listGroupe ();
    public void deleteGroupe (Long id);
    public Groupe updateGroupe (Long id,String nomClient);
    public void affecterEmployeAGroupe(List<Long> employeIds, Long groupeId);
}
