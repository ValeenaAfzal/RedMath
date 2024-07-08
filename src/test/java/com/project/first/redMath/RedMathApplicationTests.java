package com.project.first.redMath;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc

class RedMathApplicationTests {
	@Autowired
	private MockMvc mockMvc;
/*
	@Test
	void testHomePageControllerSuccess() throws Exception{
		String expectedResponse = "{\"aa\":1,\"foo\":2,\"key\":12}";
		String responseString= this.mockMvc.perform(get("/home")).andDo(print()).andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
		assertEquals(expectedResponse, responseString);
	}*/


}
