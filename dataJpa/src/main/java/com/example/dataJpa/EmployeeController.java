package com.example.dataJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo repo;

    @PostMapping("/save")
    public Employee addEmployee(@RequestBody Employee emp){
        return repo.save(emp);
    }

    @GetMapping("/get")
    public List<Employee> getAllEmployees(){
        return repo.findAll();
    }

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Optional<Employee> emp = repo.findById(id);
        return emp.orElse(null);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable Long id,@RequestBody Employee updatedEmp){
        Employee emp = repo.findById(id).orElse(null);
        if(emp!=null){
            emp.setName(updatedEmp.getName());
            emp.setSalary(updatedEmp.getSalary());
            return repo.save(emp);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        repo.deleteById(id);
        return "Employee deleted with id : " + id;
    }
}
