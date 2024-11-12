package com.mini.prj.services;

import com.mini.prj.entities.Employe;
import com.mini.prj.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeRestService {
    @Autowired
    private EmployeMetier employeMetier;
    @GetMapping("/employes")
    @RequestMapping(value="/employes",method= RequestMethod.POST)
    public Employe saveEmploye(@RequestBody Employe e) {
        return employeMetier.saveEmploye(e);
    }
  //  @RequestMapping(value="/employes",method=RequestMethod.GET)
   /* public List<Employe> listEmployes() {
        return employeMetier.listEmployes();
    }*/
    @RequestMapping(value="/employes/{id}",method=RequestMethod.DELETE)
    public void deleteEmploye(@PathVariable("id")Long id) {
        employeMetier.deleteEmploye(id);
    }
    @RequestMapping(value="/employes",method=RequestMethod.GET)
    public String listEmployes(Model model) {
        List<Employe> employes = employeMetier.listEmployes();
        model.addAttribute("employes", employes);
        return "listEmployes"; // Le nom du fichier Thymeleaf (listEmployes.html)
    }
}
