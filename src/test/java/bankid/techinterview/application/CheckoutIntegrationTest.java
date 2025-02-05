package bankid.techinterview.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class CheckoutIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void checkout_withValidId0001_thenStatus200()
            throws Exception {

        String[] ids = new String[]{"0001"};
        mvc.perform(post("/api/v1/checkout")
                        .content(new ObjectMapper().writeValueAsString(ids))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
    }

    @Test
    public void checkout_withValidId0001WithDiscount_thenStatus200()
            throws Exception {

        String[] ids = new String[]{"0001", "0001", "0001"};
        mvc.perform(post("/api/v1/checkout")
                        .content(new ObjectMapper().writeValueAsString(ids))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(2000));
    }


    @Test
    public void checkout_mixedValidIds_thenStatus200()
            throws Exception {

        String[] ids = new String[]{"0001", "0002", "0003", "0004"};
        mvc.perform(post("/api/v1/checkout")
                        .content(new ObjectMapper().writeValueAsString(ids))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000 + 80 + 400 + 30));
    }

    @Test
    public void checkout_longInput_thenStatus200()
            throws Exception {

        //13 ids of 0001
        //12 are 3 for 2 (2000 * 4) + 1 regular price 1000
        String[] ids = new String[]{"0001","0001","0001","0001","0001","0001","0001","0001","0001","0001","0001","0001","0001"};
        mvc.perform(post("/api/v1/checkout")
                        .content(new ObjectMapper().writeValueAsString(ids))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(2000 * 4 + 1000));
    }

    @Test
    public void checkout_invalidIdInput_thenStatus4000()
            throws Exception {

        String[] ids = new String[]{"invalid"};
        mvc.perform(post("/api/v1/checkout")
                        .content(new ObjectMapper().writeValueAsString(ids))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("unknown product Id: invalid"));
    }
}
