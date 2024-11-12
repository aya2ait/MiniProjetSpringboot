package com.mini.prj.services;


import com.mini.prj.entities.Client;
import com.mini.prj.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientRestService {


    //crud create ,read ,update ,delete
    @Autowired
    private ClientMetier clientMetier;


    @RequestMapping(value="/clients",method= RequestMethod.POST)
    public Client saveClient(@RequestBody Client c) {
        return clientMetier.saveClient(c);
    }


    @RequestMapping(value="/clients",method=RequestMethod.GET)
    public List<Client> listClient() {
        return clientMetier.listClient();
    }

    @RequestMapping(value="/clients/{id}",method=RequestMethod.DELETE)
    public void deleteClient(@PathVariable("id")Long id) {
        clientMetier.deleteClient(id);
    }

    // New update method
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        return clientMetier.updateClient(id, client); // Passez l'objet complet
    }
   /* @GetMapping("/index")
    public String index(Model model) {
        List<Client> clients = clientMetier.listClient();
        model.addAttribute("clients", clients);
        return "index.html"; // Renvoie à la vue "index.html" dans le dossier templates
    }*/



}
