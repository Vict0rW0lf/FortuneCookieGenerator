package test;

import static org.junit.Assert.assertNotSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.monsanto.FortuneCookieGenerator.FortuneCookieGeneratorApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FortuneCookieGeneratorApplication.class)
@AutoConfigureMockMvc
public class TestFortuneCookieController {
	
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void exampleTest() throws Exception {
		
		 MvcResult result1 = this.mockMvc.perform(get("/generateFortuneCookie?client=example&company=example"))
		 .andExpect(status().isOk())
         .andReturn();
		 
		 String content1 = result1.getResponse().getContentAsString();
		 
		 MvcResult result2 = this.mockMvc.perform(get("/generateFortuneCookie?client=example&company=example"))
				 .andExpect(status().isOk())
		         .andReturn();
		 
		 String content2 = result2.getResponse().getContentAsString();
		 
		 assertNotSame(content1, content2);
	}
}
