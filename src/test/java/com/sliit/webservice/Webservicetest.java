package com.sliit.webservice;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sliit.webservice.dao.UserDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Webservicetest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserDAO userDAO;

	@Test
	public void contextLoads() throws Exception {

		Mockito.when(userDAO.findAll()).thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/accounts/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		System.out.println("================All Users===================");
		System.out.println(mvcResult.getResponse());
		System.out.println("============================================");

		Mockito.verify(userDAO).findAll();
	}
}
