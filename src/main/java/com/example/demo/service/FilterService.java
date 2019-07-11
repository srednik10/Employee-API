package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;
import com.example.demo.model.FilterDto;

public interface FilterService {

	List<Employee> getFiltredEmployees(FilterDto filterDto);
}
