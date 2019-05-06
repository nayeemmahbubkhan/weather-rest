package de.creatision.weather.restcontroller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.creatision.weather.datatransferobject.UserSignUpRequest;
import de.creatision.weather.service.UserService;
import de.creatision.weather.service.WeatherService;
import de.creatision.weather.service.security.JWTService;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private UserController userController;
 
    @MockBean
    private UserService userService;
    
    @MockBean
    private WeatherService reminderService;
    
    @MockBean
    private JWTService jwtService;

    @Autowired
    WebApplicationContext context;
    
    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void createUsers() throws Exception {
    	
    	UserSignUpRequest userSignUpRequest = UserSignUpRequest.builder()
    			.username("testUser")
    			.password("testPassword")
    			.emailAddress("test@gmx.de")
    			.city("Berlin")
    			.country("Germany")
    			.build();
         
        mvc.perform(post("/v1/users/signup")
        		.content(new ObjectMapper().writeValueAsString(userSignUpRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }
    
}
