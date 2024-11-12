package com.mini.prj.metier;

import com.mini.prj.dao.EmployeRepository;
import com.mini.prj.dao.GroupeRepository;
import com.mini.prj.entities.Employe;
import com.mini.prj.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GrouprMetierImpl implements GroupeMetier {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private EmployeRepository employeRepository; // Ajoutez l'annotation @Autowired ici

    @Override
    public Groupe saveGroupe(Groupe c) {
        return groupeRepository.save(c);
    }

    @Override
    public List<Groupe> listGroupe() {
        return groupeRepository.findAll();
    }

    public void deleteGroupe(Long groupeId) {
        Groupe groupe = groupeRepository.findById(groupeId).orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        // Dissocier tous les employés du groupe
        for (Employe employe : groupe.getEmployes()) {
            employe.getGroupes().remove(groupe);
            employeRepository.save(employe);  // Met à jour l'employé dans la base de données
        }

        // Après avoir dissocié les employés, supprimer le groupe
        groupeRepository.delete(groupe);
    }


    @Override
    public Groupe updateGroupe(Long id, String nomGroupe) {
        Optional<Groupe> existingClient = groupeRepository.findById(id);
        if (existingClient.isPresent()) {
            Groupe groupe = existingClient.get();
            groupe.setNomGroupe(nomGroupe);
            return groupeRepository.save(groupe);
        } else {
            throw new RuntimeException("Groupe avec ID " + id + " non trouvé");
        }
    }

    @Transactional
    @Override
    public void affecterEmployeAGroupe(List<Long> employeIds, Long groupeId) {
        // Récupérer le groupe
        Groupe groupe = groupeRepository.findById(groupeId)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        // Pour chaque employé, ajouter au groupe et ajouter le groupe à l'employé
        for (Long employeId : employeIds) {
            Employe employe = employeRepository.findById(employeId)
                    .orElseThrow(() -> new RuntimeException("Employé avec ID " + employeId + " non trouvé"));

            // Ajouter le groupe à l'employé
            employe.getGroupes().add(groupe);

            // Ajouter l'employé au groupe
            groupe.getEmployes().add(employe);

            // Sauvegarder les modifications pour chaque employé
            employeRepository.save(employe);
        }

        // Sauvegarder le groupe après avoir ajouté tous les employés
        groupeRepository.save(groupe);
    }

}
