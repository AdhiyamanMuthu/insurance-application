package com.insurance.aggregator.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.aggregator.pojo.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

    List<Policy> findByNameContainingIgnoreCase(String name, Sort sort);
    
//    List<Policy> findByNameStartingWithIgnoreCase(String prefix);
    
//    List<Policy> findByNameEndingWithIgnoreCase(String suffix);
    
}
