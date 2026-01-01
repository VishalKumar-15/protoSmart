package com.app.protoSmart.Repositories;

import com.app.protoSmart.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUserId(Long userId);
}
