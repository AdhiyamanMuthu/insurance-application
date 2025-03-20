package com.insurance.aggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.aggregator.pojo.Policy;
import com.insurance.aggregator.repository.PolicyRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	@Autowired
	PolicyRepository policyRepository;

	@PostMapping("/policies")
	public Policy addPolicy(Policy policy) {
		log.info("addPolicy: {}", policy);
		return policyRepository.save(policy);
	}
}
