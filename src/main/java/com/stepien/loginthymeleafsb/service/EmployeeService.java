package com.stepien.loginthymeleafsb.service;

import com.stepien.loginthymeleafsb.model.Employees;
import com.stepien.loginthymeleafsb.repository.EmployeesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@AllArgsConstructor
@Service
public class EmployeeService {
    private EmployeesRepository employeesRepository;
    private ExcelUploadService excelUploadService;

    public boolean saveEmployeesListToDatabase(MultipartFile file){
        if(ExcelUploadService.isValidExcelFile(file)){
            try{
                List<Employees> employees = ExcelUploadService.getEmployeesDataFromExcel(file.getInputStream());
                if(excelUploadService.checkEmployeesExcelIfDuplicate(employees)){
                    employees.clear();
                    return false;
                } else
                    this.employeesRepository.saveAll(employees);
            } catch (IOException e){
                throw new IllegalArgumentException("This file is not a valid excel file");
            }
        }
        return true;
    }
    public List<Employees> getEmployees(){
        return employeesRepository.findAll();
    }

    public String addEmployee(Employees employees){
        String email = employees.getEmail();
        String message;
        if(employeesRepository.findByEmail(email)){
            message = "Employee with this email already exists. Try another email" ;
            return message;
        } else
            employeesRepository.save(employees);
        return message = "Employee added";
    }

}
