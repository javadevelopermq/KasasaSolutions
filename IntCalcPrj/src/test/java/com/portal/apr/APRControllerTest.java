package com.portal.apr;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.apr.controller.APRController;
import com.portal.apr.dao.APRRepository;
import com.portal.apr.model.Loan;

public class APRControllerTest extends APRAbstractTest {

	@Mock
	private APRRepository repo;

	@InjectMocks
	private APRController controller;

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void storeApr() throws Exception {
		String uri = "/store";
		Loan loan = new Loan();
		loan.setDays(365);
		loan.setPrincipal(70000);
		loan.setName("test");
		loan.setRate(6.7);
		loan.setSsn(546731);
		loan.setType("employee");

		ObjectMapper objectMapper = new ObjectMapper();
		String output = objectMapper.writeValueAsString(loan);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(output))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void getApr() throws Exception {
		when(repo.findById(1l)).thenReturn(new Loan());
		ResponseEntity<Loan> obj = controller.fetchLoan(1l);

		assertEquals(200, obj.getStatusCodeValue());
	}

}