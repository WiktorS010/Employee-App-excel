package com.stepien.loginthymeleafsb.service;

import com.stepien.loginthymeleafsb.model.Employees;
import com.stepien.loginthymeleafsb.repository.EmployeesRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Employees> getEmployeesDataFromExcel(InputStream inputStream) {
        List<Employees> employees = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("employees");
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Employees employee = new Employees();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> employee.setFirstName(cell.getStringCellValue());
                        case 1 -> employee.setLastName(cell.getStringCellValue());
                        case 2 -> employee.setEmail(cell.getStringCellValue());
                        case 3 -> employee.setSalary((int) cell.getNumericCellValue());
                        case 4 -> employee.setAge((int) cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                employees.add(employee);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return employees;
    }
    @Autowired
    private EmployeesRepository employeesRepository;

    public Boolean checkEmployeesExcelIfDuplicate(List<Employees> employees) {
        List<Employees> employeesFromDatabase = employeesRepository.findAll();
        return employeesFromDatabase.stream().map(Employees::getEmail)
                .anyMatch(employees.stream().map(Employees::getEmail).collect(Collectors.toSet())::contains);
    }
}



