package com.portal.apr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal.apr.dao.APRRepository;
import com.portal.apr.model.Loan;
import com.portal.apr.service.APRService;

@RestController
public class APRController {

	@Autowired
	private APRRepository repo;
	
	@Autowired
	APRService loanService;

	@PostMapping(value = "/store", produces = "application/json")
	public ResponseEntity<String> insertLoan(@RequestBody Loan loan) {
		int result = 0;
		try {
			double apr=loanService.calcApr(loan);
			loan.setApr(apr);
			result = repo.insert(loan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return new ResponseEntity<>("apr stored successfully", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("Failed to store apr", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/fetch/{id}", produces = "application/json")
	public ResponseEntity<Loan> fetchLoan(@PathVariable("id") Long loanId) {
		Loan result = null;
		try {
			result = repo.findById(loanId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
