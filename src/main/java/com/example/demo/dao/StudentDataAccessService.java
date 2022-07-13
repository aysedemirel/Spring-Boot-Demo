package com.example.demo.dao;

import com.example.demo.model.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class StudentDataAccessService implements StudentDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public StudentDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int insertStudent(UUID id, Student student) {
    String sql = "" +
        "INSERT INTO students (" +
        " id, " +
        " fullName, " +
        " classNumber, " +
        " email) " +
        "VALUES (?, ?, ?, ?)";
    return jdbcTemplate.update(
        sql,
        id,
        student.getFullName(),
        student.getClassNumber(),
        student.getEmail());
  }

  @Override
  public List<Student> selectAllStudents() {
    final String sql = "SELECT id,fullName,classNumber,email FROM students";
    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      UUID id = UUID.fromString(rs.getString("id"));
      String name = rs.getString("fullName");
      int classNumber = Integer.parseInt(rs.getString("classNumber"));
      String email = rs.getString("email");
      return new Student(id, name, classNumber, email);
    });
  }

  @Override
  public int deleteStudentById(UUID id) {
    boolean isPresent = selectStudentById(id).isPresent();
    if (!isPresent) {
      throw new IllegalStateException("student with id " + id + " does not exist");
    }
    String sql = "DELETE FROM students WHERE id=?";
    return jdbcTemplate.update(sql, id);
  }

  @Override
  public int updateStudentById(UUID id, Student student) {
    boolean isPresent = selectStudentById(id).isPresent();
    if (!isPresent) {
      throw new IllegalStateException("student with id " + id + " does not exist");
    }
    String sql = "UPDATE students " +
        "SET fullName = ?, classNumber = ?, email = ? " +
        "WHERE id = ?";
    return jdbcTemplate.update(sql, student.getFullName(), student.getClassNumber(),
        student.getEmail(), id);
  }

  @Override
  public Optional<Student> selectStudentById(UUID id) {
    final String sql = "SELECT id,fullName,classNumber,email FROM students WHERE id = ?";
    Student student = jdbcTemplate.queryForObject(
        sql,
        (rs, i) -> {
          UUID studentId = UUID.fromString(rs.getString("id"));
          String name = rs.getString("fullName");
          int classNumber = Integer.parseInt(rs.getString("classNumber"));
          String email = rs.getString("email");
          return new Student(studentId, name, classNumber, email);
        },
        id);
    return Optional.ofNullable(student);
  }
}
