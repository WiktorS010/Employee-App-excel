package com.stepien.loginthymeleafsb.controller;

import com.stepien.loginthymeleafsb.model.Employees;
import com.stepien.loginthymeleafsb.model.UserDtls;
import com.stepien.loginthymeleafsb.repository.EmployeesRepository;
import com.stepien.loginthymeleafsb.repository.UserRepository;
import com.stepien.loginthymeleafsb.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final EmployeesRepository employeesRepository;

    private final EmployeeService employeeService;

    public UserController(UserRepository userRepository, EmployeesRepository employeesRepository, EmployeeService employeeService) {
        this.userRepository = userRepository;
        this.employeesRepository = employeesRepository;
        this.employeeService = employeeService;
    }

    @GetMapping("/home")
    public String userDetails(Model model, Principal principal) {
        String email = principal.getName();
        UserDtls userDtls = userRepository.findByEmail(email);
        List<Employees> employees = employeeService.getEmployees();
        model.addAttribute("user", userDtls);
        model.addAttribute("employees", employees);
        return "home";
    }

    @Transactional
    @PostMapping("/home/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeesRepository.deleteEmployeesById(id);
        return "redirect:/user/home";
    }

    @PostMapping("/home/addEmployee")
    public String addEmployee(@ModelAttribute Employees employee) {
        employeesRepository.save(employee);
        return "redirect:/user/home";
    }

    @GetMapping("/uploadExcelFile")
    public String uploadExcelFileView(Model model, Principal principal, MultipartFile file) {
        String email = principal.getName();
        UserDtls userDtls = userRepository.findByEmail(email);
        model.addAttribute("user", userDtls);
        model.addAttribute("file", file);
        return "excel";
    }

    @PostMapping("/uploadExcelFile")
    public String uploadEmployeesData(@RequestParam("file") MultipartFile file, Model model, Principal principal) throws IOException {
        String email = principal.getName();
        UserDtls userDtls = userRepository.findByEmail(email);
        model.addAttribute("user", userDtls);
        String color1 = "green";
        String color2 = "red";
        if (this.employeeService.saveEmployeesListToDatabase(file)) {
            model.addAttribute("message", "File: " + file.getOriginalFilename()
                    + " has been uploaded successfully!");
            model.addAttribute("color", color1);
        } else {
            model.addAttribute("message", "File: " + file.getOriginalFilename()
                    + " has not been uploaded, because there are duplicated email addresses in excel employee list !");
            model.addAttribute("color", color2);
        }
        return "excel";

    }


}
