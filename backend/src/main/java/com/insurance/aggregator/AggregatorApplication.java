package com.insurance.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class AggregatorApplication {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
	}

	@PostConstruct
	public void load() {
		log.info("Loading seed data");
		try {
			jdbcTemplate.execute("INSERT INTO policy_table (id, name, type, premium, coverage) VALUES (1, 'Secure Future Term Life', 'Term Life', 5000, 1000000);");
			jdbcTemplate.execute("INSERT INTO policy_table (id, name, type, premium, coverage) VALUES (2, 'Health Shield Plan', 'Health', 3000, 500000);");
			jdbcTemplate.execute("INSERT INTO policy_table (id, name, type, premium, coverage) VALUES (3, 'Car Protect Plan', 'Vehicle', 2000, 300000);");
			jdbcTemplate.execute("INSERT INTO policy_table (id, name, type, premium, coverage) VALUES (5, 'Acko Insurance', 'Term Life', 5000, 1000000);");
			log.info("Data loaded successfully");
		} catch(Exception e) {
			e.printStackTrace();
			log.info("Loading initial data failed");
		}
	}
}
