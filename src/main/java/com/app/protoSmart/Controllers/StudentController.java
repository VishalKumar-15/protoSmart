package com.app.protoSmart.Controllers;

import com.app.protoSmart.Entities.Student;
import com.app.protoSmart.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/studentData")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/byUserId/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudentByUserId(@RequestParam(value = "userId") Long userId) {

        Student student = studentRepository.findByUserId(userId);

        if (student == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
