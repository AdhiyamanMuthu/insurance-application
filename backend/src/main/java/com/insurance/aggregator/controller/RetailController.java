package com.insurance.aggregator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.aggregator.pojo.Policy;
import com.insurance.aggregator.repository.PolicyRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class RetailController {
	
	@Autowired
	PolicyRepository policyRepository;
	
	@GetMapping("/policies")
	public List<Policy> fetch(
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "sorted", required = true) String sorted,	
			@RequestParam(value = "field", required = true) String field)		
	{
		
		List<Policy> out = null;
		
		Sort sort = null;
		if(sorted != null) {
			Sort.Direction direction = sorted.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
			sort = Sort.by(direction, field);
		}
		
		
		if (name != null && !name.isBlank() && !name.isEmpty()) {
			out = policyRepository.findByNameContainingIgnoreCase(name, sort);
		} else {
			out = policyRepository.findAll(sort);
		}
		return out;
	}
	
	@GetMapping("/search")
	public List<Policy> search(
			@RequestParam(value = "coverBetween", required = true) String cover, 
			@RequestParam(value = "premBetween", required = true) String premium,	
			@RequestParam(value = "type", required = true) String type)		
	{
		String coverFrom = cover.substring(0, cover.indexOf("-"));
		String coverTo = cover.substring(cover.indexOf("-") + 1);
		String premFrom = premium.substring(0, premium.indexOf("-"));
		String premTo = premium.substring(premium.indexOf("-") + 1);
		
		
		System.out.println(coverFrom +" " +coverTo +" " + premFrom + " " + premTo);
		List<Policy> out = policyRepository.findAll();
		out = policyRepository.findAll().stream()
				.filter(policy -> type.isEmpty() ? true:policy.getType().equalsIgnoreCase(type))
				.filter(policy -> (policy.getCoverage() >= Integer.parseInt(coverFrom)) &&  (policy.getCoverage() <= Integer.parseInt(coverTo)))
				.filter(policy -> (policy.getPremium() >= Integer.parseInt(premFrom)) &&  (policy.getCoverage() <= Integer.parseInt(premTo)))
				.toList();
		return out;
	}
	
}
