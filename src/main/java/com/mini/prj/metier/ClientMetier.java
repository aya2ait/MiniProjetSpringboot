package com.mini.prj.metier;

import com.mini.prj.entities.Client;

import java.util.List;



public interface ClientMetier {
    public Client saveClient(Client c);
    public List<Client> listClient();
    public void deleteClient(Long id);
   public Client updateClient(Long id, Client client);

}
