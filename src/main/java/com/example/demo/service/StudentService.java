package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private final StudentDao studentDao;

  @Autowired
  public StudentService(@Qualifier("fakeDao") StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  public int addStudent(Student student) {
    return studentDao.addStudent(student);
  }

  public int addStudents(List<Student> students) {
    return studentDao.addStudents(students);
  }

  public List<Student> getAllStudents() {
    return studentDao.selectAllStudents();
  }

  public int deleteStudentById(UUID id) {
    return studentDao.deleteStudentById(id);
  }

  public Optional<Student> getStudentById(UUID id) {
    return studentDao.selectStudentById(id);
  }

  public int updateStudentById(UUID id, Student student) {
    return studentDao.updateStudentById(id, student);
  }
}
