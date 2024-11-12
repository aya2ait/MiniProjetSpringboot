package com.mini.prj.metier;

import com.mini.prj.dao.CompteRepository;
import com.mini.prj.dao.OperationRepository;
import com.mini.prj.entities.Compte;
import com.mini.prj.entities.Operation;
import com.mini.prj.entities.Retrait;
import com.mini.prj.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OperationMetierImpl implements OperationMetier {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CompteRepository compteRepository;

    @Override
    public Operation saveOperation(Operation c) {
        // TODO Auto-generated method stub
        return operationRepository.save(c);
    }

    @Override
    public List<Operation> listOperation() {
        // TODO Auto-generated method stub
        return operationRepository.findAll();
    }

    @Override
    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }
    //montant

    @Override
    public Operation updateOperation(Long id, Double montant) {
        Optional<Operation> existingOperation = operationRepository.findById(id);
        if(existingOperation .isPresent()) {
            Operation operation = existingOperation.get();
            operation.setMontant(montant);
            return operationRepository.save(operation);
        }else{
            throw new RuntimeException("Operation with ID " + id + " not found");
        }
    }

    @Override
    public String effeffectuerRetrait(String codeCompte, Double montant) {
        Optional<Compte> compteOptional = compteRepository.findById(codeCompte);

        if (compteOptional.isPresent()) {
            Compte compte = compteOptional.get();

            if (montant > 0 && montant <= compte.getSolde()) {
                // Deduct the amount from the account balance
                compte.setSolde(compte.getSolde() - montant);

                // Save the updated account balance
                compteRepository.save(compte); // Save compte2, as it was fetched from the database

                // Optionally, create a Retrait entity to save in the operations table
                Retrait retrait = new Retrait();
                retrait.setMontant(montant);
                retrait.setCompte(compte);
                retrait.setDateOperation(new Date());

                // Save the Retrait operation (this will save it as an operation in the operations table)
                operationRepository.save(retrait);

                return "Retrait effectué avec succès. Nouveau solde: " + compte.getSolde();
            } else {
                return "Erreur: Montant insuffisant ou montant invalide.";
            }
        } else {
            return "Erreur: Compte introuvable.";
        }
    }


    @Override
    public String effeffectuerVersement(String codeCompte, Double montant) {
        Optional<Compte> compteOptional = compteRepository.findById(codeCompte);

        if (compteOptional.isPresent()) {
            Compte compte = compteOptional.get();

            if (montant > 0) {
                // Add the amount to the account balance
                compte.setSolde(compte.getSolde() + montant);

                // Save the updated account balance
                compteRepository.save(compte); // Save the updated account balance

                // Optionally, create a Versement entity to save in the operations table
                Versement versement = new Versement();
                versement.setMontant(montant);
                versement.setCompte(compte);
                versement.setDateOperation(new Date());

                // Save the Versement operation (this will save it as an operation in the operations table)
                operationRepository.save(versement);

                return "Versement effectué avec succès. Nouveau solde: " + compte.getSolde();
            } else {
                return "Erreur: Montant invalide.";
            }
        } else {
            return "Erreur: Compte introuvable.";
        }
    }
    @Override
    public String transfererMontant(String codeCompteSource, String codeCompteDestination, Double montant) {
        // Fetch source and destination accounts from the repository
        Optional<Compte> compteSourceOpt = compteRepository.findById(codeCompteSource);
        Optional<Compte> compteDestOpt = compteRepository.findById(codeCompteDestination);

        if (compteSourceOpt.isPresent() && compteDestOpt.isPresent()) {
            Compte compteSource = compteSourceOpt.get();
            Compte compteDestination = compteDestOpt.get();

            // Validate the transfer amount
            if (montant > 0 && montant <= compteSource.getSolde()) {
                // Deduct the amount from the source account's balance
                compteSource.setSolde(compteSource.getSolde() - montant);
                compteRepository.save(compteSource);

                // Add the amount to the destination account's balance
                compteDestination.setSolde(compteDestination.getSolde() + montant);
                compteRepository.save(compteDestination);

                // Record the Retrait operation for the source account
                Retrait retrait = new Retrait();
                retrait.setCompte(compteSource);
                retrait.setMontant(montant);
                retrait.setDateOperation(new Date());
                operationRepository.save(retrait);
                // Record the Versement operation for the destination account
                Versement versement = new Versement();
                versement.setCompte(compteDestination);
                versement.setMontant(montant);
                versement.setDateOperation(new Date());
                operationRepository.save(versement);

                return "Transfert effectué avec succès. Nouveau solde source: " + compteSource.getSolde() +
                        ", Nouveau solde destination: " + compteDestination.getSolde();
            } else {
                return "Erreur: Montant insuffisant ou montant invalide.";
            }
        } else {
            return "Erreur: Compte source ou destination introuvable.";
        }
    }

}