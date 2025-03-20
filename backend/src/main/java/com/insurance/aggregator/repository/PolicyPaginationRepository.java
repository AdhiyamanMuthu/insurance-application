package com.insurance.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.aggregator.pojo.Policy;

@Repository
public interface PolicyPaginationRepository extends JpaRepository<Policy, Integer>{

}
