package com.app.protoSmart.Repositories;

import com.app.protoSmart.Entities.AcademicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicDetailsRepository extends JpaRepository<AcademicDetails, Long> {

    AcademicDetails findByStudentId(Long studentId);
}
