package by.tms.findyourtutor;

import by.tms.findyourtutor.controller.EqualPairController;
import by.tms.findyourtutor.entity.UserStudent;
import by.tms.findyourtutor.entity.UserTutor;
import by.tms.findyourtutor.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EqualPairController.class)
public class EqualPairControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb(){
        userRepository.deleteAll();
    }
    @Test
    public void EqualPairControllerTest() throws Exception{
        UserTutor userTutor =new UserTutor( );
        userTutor.setLanguage("English");
        userTutor.setLessonTime("4 p.m.");
        userTutor.setPrice("1000/hour");
        UserStudent userStudent =new UserStudent( );
        userStudent.setLanguage("English");
        userStudent.setLessonTime("4 p.m.");
        userStudent.setPrice("1000/hour");

        mockMvc.perform(post("/user/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userTutor,userTutor)))
                        .andExpect(status().isOk())
                        .andExpect(content().string("You're a match"));


    }
    private static String asJsonString(final Object...objects ){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(objects);
        } catch (Exception e){
            throw  new RuntimeException(e);

        }
    }
}
