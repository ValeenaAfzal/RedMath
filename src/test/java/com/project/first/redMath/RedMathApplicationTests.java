package com.project.first.redMath;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc

class RedMathApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testAccountGetSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/redMath/1"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.account_number", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.account_holder_name", Matchers.is("John Doe")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.balance", Matchers.is(1000.0)))
				/*.andExpect(MockMvcResultMatchers.jsonPath("$.updated_at", Matchers.is("reporter 123")))*/
				.andExpect(MockMvcResultMatchers.jsonPath("$.created_at", Matchers.notNullValue()));
	}

	@Test
	public void testAccountGetNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/redMath/456"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Order(1)
	@Test
	public void testAccountGetListSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/redMath/"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(11)));
	}

	@Order(2)
	@Test
	public void testAccountPostSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/redMath/")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{\"account_holder_name\":\"John Doe\",\"balance\":1000.0}")
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.with(SecurityMockMvcRequestPostProcessors.user("admin")
								.authorities(new SimpleGrantedAuthority("admin"))))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.account_number", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.account_holder_name", Matchers.is("John Doe")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.balance", Matchers.is(1000.0)));
//				.andExpect(MockMvcResultMatchers.jsonPath("$.reportedBy", Matchers.is("test")))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.reportedAt", Matchers.notNullValue()));
	}
}
