package com.portal.apr.service;

import org.springframework.stereotype.Service;

import com.portal.apr.model.Loan;

@Service
public class APRService {

	public double calcApr(Loan loan) {

		double fees = 0;
		switch (loan.getType()) {
		case "student":
			fees = 0.00;
			break;
		case "auto":
			fees = 500;
			break;
		case "personal":
			fees = 750;
			break;
		case "mortgage":
			fees = 1500;
			break;
		default:
			fees = 0;
			break;
		}
		double interest = (loan.getPrincipal() * loan.getRate() * loan.getDays()) / 100;

		return ((((fees + interest) / loan.getPrincipal()) / loan.getDays()) * 365) * 100;
	}
}
