package com.example.demo.dao;

import com.example.demo.model.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {

  int insertStudent(UUID id, Student student);

  List<Student> selectAllStudents();

  int deleteStudentById(UUID id);

  int updateStudentById(UUID id, Student student);

  Optional<Student> selectStudentById(UUID id);

  default int addStudent(Student student) {
    UUID id = UUID.randomUUID();
    return insertStudent(id, student);
  }

  default int addStudents(List<Student> students) {
    int totalResult = 1;
    for (Student student : students
    ) {
      // addStudent will return 0 or 1
      totalResult *= addStudent(student);
    }
    return totalResult;
  }
}
