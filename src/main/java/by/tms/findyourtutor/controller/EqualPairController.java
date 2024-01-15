package by.tms.findyourtutor.controller;

import by.tms.findyourtutor.entity.UserStudent;
import by.tms.findyourtutor.entity.UserTutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class EqualPairController {
    @PostMapping("/match")
    public ResponseEntity<String> matchStudentAndTutor(@RequestBody UserStudent userStudent,
                                                       @RequestBody UserTutor userTutor) {
        if (userStudent.getLessonTime().equals(userTutor.getLessonTime())
                && (userStudent.getPrice().equals(userTutor.getPrice()))
                && (userStudent.getLanguage().equals(userTutor.getLanguage()))) {

            return ResponseEntity.ok("You're a match.");
        } else {
            return ResponseEntity.ok("There are no matches for you now.Try to change your requirements.");

        }

    }

}


