package com.mini.prj.metier;

import com.mini.prj.dao.EmployeRepository;
import com.mini.prj.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeMtierImpl implements EmployeMetier{


    @Autowired
    private EmployeRepository employeRepository;


    @Override
    public Employe saveEmploye(Employe e) {
       // TODO Auto-generated method stub
        return employeRepository.save(e);    }

    @Override
    public List<Employe> listEmployes() {
        // TODO Auto-generated method stub
        return employeRepository.findAll();
    }
    public void deleteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
