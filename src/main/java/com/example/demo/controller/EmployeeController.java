package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.FilterDto;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.FilterService;

import exception.ResourceDoesNotExistException;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private FilterService filterService;
	
	@GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {
		return this.employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceDoesNotExistException("Employee does not exist with id:" + employeeId));
    }
	
	@PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.save(employee);
    }
	
	@PutMapping("/employee/{employeeId}")
	    public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employeeBody) {
		return this.employeeRepository.findById(employeeId)
				.map(employee -> {
					employee.setName(employeeBody.getName());
					employee.setSurname(employeeBody.getSurname());
					employee.setAge(employeeBody.getAge());
					return this.employeeRepository.save(employee);
				}).orElseThrow(() -> new ResourceDoesNotExistException("Employee does not exist with id:" + employeeId));
	               
	    }
	
	 @DeleteMapping("/employee/{employeeId}")
	    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
		 	return this.employeeRepository.findById(employeeId)
		 			.map(employee -> {
		 				this.employeeRepository.delete(employee);
		 				return ResponseEntity.ok().build();
		 			}).orElseThrow(() -> new ResourceDoesNotExistException("Employee does not exist with id:" + employeeId));

	    }
	 
	 
	 @PostMapping("/employee/filter")
	    public List<Employee> getFiltredEmployees(@RequestBody FilterDto filterDto) {

			return this.filterService.getFiltredEmployees(filterDto);
		 	 
	    }
	 
	 
}
