/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rest.lottery;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class LotteryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	public void createTikcetwithNlines1() throws Exception {

		this.mockMvc.perform(get("/ticket")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}
	
	private Ticket prepareTestData(int one, int two , int three) {
		Ticket testTicket=new Ticket();
		List<Line> lines=new ArrayList<Line>(); 
		Line line =new Line();
		line.setNumber1(one);
		line.setNumber2(two);
		line.setNumber3(three);
		lines.add(line);
		testTicket.setLines(lines);
		return testTicket;
	}
	
	
	@Test
	public void createTikcetwithOnelinesAndGellAllTickets() throws Exception {
		this.mockMvc.perform(post("/ticket").content(asJsonString(prepareTestData(1, 1, 1)))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/ticket")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createTicketwithTwolinesGetResultSorted() throws Exception {
		Ticket testTicket=new Ticket();
		List<Line> lines=new ArrayList<Line>();
        lines.add(new Line(1, 1, 0));
        lines.add(new Line(2, 2, 2));
        lines.add(new Line(1, 0, 2));
        testTicket.setLines(lines);
        
		this.mockMvc.perform(post("/ticket").content(asJsonString(testTicket))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		
		// here expected output is 
		/*
		 * 110  -> 10      102
		 * 222  -> 5   ->  222
		 * 102  -> 1       110
		 */
		
		
	}
	
		
	@Test
	public void getTikceetById() throws Exception {

		this.mockMvc.perform(get("/ticket/1")).andDo(print()).andExpect(status().isOk());
	}

	
	@Test
	public void getAllTikcets() throws Exception {
		this.mockMvc.perform(get("/ticket")).andDo(print()).andExpect(status().isOk());
	}

	
	/*
	 * @Test public void getStausOfTikcet() throws Exception {
	 * 
	 * this.mockMvc.perform(put("/status/2")).andDo(print()).andExpect(status().isOk
	 * ()); }
	 */

	
	@Test
	public void getAllTikcetsAfterStatusCheck() throws Exception {
		this.mockMvc.perform(get("/ticket")).andDo(print()).andExpect(status().isOk());
	}
	

	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
