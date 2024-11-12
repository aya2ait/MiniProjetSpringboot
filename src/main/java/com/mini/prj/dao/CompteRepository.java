package com.mini.prj.dao;

import com.mini.prj.entities.Compte;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte,String> {
    List<Compte> findByClientCodeClient(Long codeClient);
}
