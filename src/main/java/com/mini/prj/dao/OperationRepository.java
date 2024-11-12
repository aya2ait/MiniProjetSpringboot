package com.mini.prj.dao;

import com.mini.prj.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OperationRepository extends JpaRepository<Operation, Long> {
}