package com.stepien.loginthymeleafsb.repository;

import com.stepien.loginthymeleafsb.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    List<Employees> findAll();

    void deleteEmployeesById(int id);

    Boolean findByEmail(String email);

}
