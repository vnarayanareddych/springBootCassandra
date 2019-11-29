package com.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.SimpleTable;
import com.springboot.repository.CassandraRepository;

@RestController
@RequestMapping("/cassandra")
public class CassandraController {

	@Autowired
	private CassandraRepository cassRepo;

	@PostMapping
	public ResponseEntity<SimpleTable> saveIntoSimpleTable(@RequestBody SimpleTable simpleTable) {
		SimpleTable saveData = cassRepo.save(simpleTable);
		return new ResponseEntity<>(saveData, HttpStatus.OK);
	}

	@GetMapping(path = "{id}/{name}")
	public ResponseEntity<SimpleTable> fetchRecordsFromSimpleTable(@PathVariable("id") String id,
			@PathVariable("name") String name) {
		Optional<SimpleTable> fetchData = cassRepo.findByIdAndName(id, name);
		if (!fetchData.isPresent()) {
			System.out.println("Data is empty");
		}
		return new ResponseEntity<>(fetchData.get(), HttpStatus.OK);
	}
}
