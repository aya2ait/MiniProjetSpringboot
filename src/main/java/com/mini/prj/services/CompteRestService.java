package com.mini.prj.services;

import com.mini.prj.entities.Compte;
import com.mini.prj.entities.CompteCourant;
import com.mini.prj.entities.CompteEpargne;
import com.mini.prj.entities.Operation;
import com.mini.prj.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteRestService {

    @Autowired
    private CompteMetier compteMetier;

   // Endpoint to add a new account
   @PostMapping("/add")
   public Compte addCompte(@RequestBody Compte compte) {
       return compteMetier.addCompte(compte);
   }

    // Endpoint to list all accounts
    @GetMapping("/list")
    public List<Compte> listComptes() {
        return compteMetier.listComptes();
    }

    @GetMapping("/{codeCompte}")
    public Compte consulterCompte(@PathVariable String codeCompte) {
        return compteMetier.consulterCompte(codeCompte);
    }
    @GetMapping("/client/{codeClient}")
    public List<Compte> getComptesByClient(@PathVariable Long codeClient) {
        return compteMetier.getComptesByClient(codeClient);
    }
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void deleteCompte(@PathVariable("id")String id) {
        compteMetier.deleteCompte(id);
    }
    @GetMapping("/{codeCompte}/operations")
    public List<Operation> getOperationsByCompte(@PathVariable String codeCompte) {
        return compteMetier.getOperationsByCompte(codeCompte);
    }






}
