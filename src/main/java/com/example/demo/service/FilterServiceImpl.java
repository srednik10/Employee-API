package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.model.FilterDto;
import com.example.demo.model.PredicateDto;

@Component
public class FilterServiceImpl implements FilterService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private CriteriaBuilder cb;
	private CriteriaQuery<Employee> q;
	private Root<Employee> c;
	
	

	@Override
	public List<Employee> getFiltredEmployees(FilterDto filterDto) {
	
		cb = entityManager.getCriteriaBuilder();
		q = cb.createQuery(Employee.class);
		c = q.from(Employee.class);
			
		Predicate[] predicates = filterDto.getPredicates().stream().map(item -> parseFilterDto(item)).toArray(Predicate[]::new);
		
		q.where(predicates);
		
		List<Employee> list = entityManager.createQuery(q).getResultList();
		
		return list;
	}
	
	private Predicate parseFilterDto(PredicateDto predicateDto) {
		
		switch(predicateDto.getPredicateName()) {
		case "lessThan":
			return cb.lessThan(c.get(predicateDto.getColumnName()), (int)predicateDto.getValues()[0]);
			
		case "equal":
			return cb.equal(c.get(predicateDto.getColumnName()), (int)predicateDto.getValues()[0]);
			
		case "gt":
			return cb.gt(c.get(predicateDto.getColumnName()), (int)predicateDto.getValues()[0]);

		case "like":
			return cb.like(c.get(predicateDto.getColumnName()), (String)predicateDto.getValues()[0]);
			
		case "between":
			return cb.between(c.get(predicateDto.getColumnName()), (int)predicateDto.getValues()[0],(int)predicateDto.getValues()[1]);
			
		default:
			throw new RuntimeException();
		
		}
		
	}
	
	
}
