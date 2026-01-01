package com.app.protoSmart.Controllers;


import com.app.protoSmart.Entities.AcademicDetails;
import com.app.protoSmart.Repositories.AcademicDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/academicData/")
public class AcademicDetailsController {

    @Autowired
    AcademicDetailsRepository academicDetailsRepository;

    @RequestMapping(value = "/byStudentId/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AcademicDetails> getAcademicDataByStudentId(@RequestParam(value = "studentId") Long studentId) {
        AcademicDetails academicData = academicDetailsRepository.findByStudentId(studentId);

        if (academicData == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(academicData, HttpStatus.OK);
    }
}
