package com.example.demo.dao;

import com.example.demo.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository("fakeDao")
public class FakeStudentDataAccessService implements StudentDao {

  private static final List<Student> DB = new ArrayList<>();

  @Override
  public int insertStudent(UUID id, Student student) {
    DB.add(new Student(id, student.getFullName(), student.getClassNumber(), student.getEmail()));
    return 1;
  }

  @Override
  public List<Student> selectAllStudents() {
    return DB;
  }

  @Override
  public int deleteStudentById(UUID id) {
    Optional<Student> maybeStudent = selectStudentById(id);
    if (maybeStudent.isEmpty()) {
      return 0;
    }
    DB.remove(maybeStudent.get());
    return 1;
  }

  @Override
  public int updateStudentById(UUID id, Student student) {
    return selectStudentById(id).map(p -> {
      int indexOfStudentToUpdate = DB.indexOf(p);
      if (indexOfStudentToUpdate >= 0) {
        DB.set(indexOfStudentToUpdate,
            new Student(id, student.getFullName(), student.getClassNumber(), student.getEmail()));
        return 1;
      }
      return 0;
    }).orElse(0);
  }

  @Override
  public Optional<Student> selectStudentById(UUID id) {
    return DB.stream().filter(student -> student.getId().equals(id)).findFirst();
  }
}
