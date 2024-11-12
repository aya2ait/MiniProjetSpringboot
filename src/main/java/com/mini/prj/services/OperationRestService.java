package com.mini.prj.services;


import com.mini.prj.entities.Client;
import com.mini.prj.entities.Compte;
import com.mini.prj.entities.Operation;
import com.mini.prj.entities.Retrait;
import com.mini.prj.metier.OperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class OperationRestService {


    @Autowired
    private OperationMetier operationMetier;


    @RequestMapping(value="/operations",method= RequestMethod.POST)
    public Operation saveOperation(@RequestBody Operation o) {
        return operationMetier.saveOperation(o);
    }


    @RequestMapping(value="/operations",method=RequestMethod.GET)
    public List<Operation> listOperation() {
        return operationMetier.listOperation();
    }

    @RequestMapping(value="/operations/{id}",method=RequestMethod.DELETE)
    public void deleteOperation(@PathVariable("id")Long id) {
        operationMetier.deleteOperation(id);
    }

    // New update method
    @RequestMapping(value = "/operations/{id}", method = RequestMethod.PUT)
    public Operation updateOperation(@PathVariable("id") Long id, @RequestParam("montant") Double montant) {
        return operationMetier.updateOperation(id,montant);
    }

    //la methode retrait

    @PostMapping("/retirer")
    public String effectuerRetrait(@RequestBody Map<String, Object> requestBody) {
        String codeCompte = (String) requestBody.get("codeCompte");
        Double montant = (Double) requestBody.get("montant");

        return operationMetier.effeffectuerRetrait(codeCompte, montant);
    }

    @PostMapping("/verser")
    public String effectuerVersement(@RequestBody Map<String, Object> requestBody) {
        String codeCompte = (String) requestBody.get("codeCompte");
        Double montant = (Double) requestBody.get("montant");

        // Call the service method
        return operationMetier.effeffectuerVersement(codeCompte, montant);
    }
    //verser fonction
    @PostMapping("/transferer")
    public String transfererMontant(@RequestBody Map<String, Object> requestBody) {
        String codeCompteSource = (String) requestBody.get("codeCompteSource");
        String codeCompteDestination = (String) requestBody.get("codeCompteDestination");
        Double montant = (Double) requestBody.get("montant");

        return operationMetier.transfererMontant(codeCompteSource, codeCompteDestination, montant);
    }

}