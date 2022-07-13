package com.example.demo.api;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/student")
@RestController
public class StudentController {

  private final StudentService studentService;

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public void addStudent(@Valid @NotNull @RequestBody Student student) {
    studentService.addStudent(student);
  }

  @PostMapping(path = "/group")
  public void addStudents(@RequestBody List<Student> students) {
    studentService.addStudents(students);
  }

  @GetMapping
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @GetMapping(path = "{id}")
  public Student getStudentById(@PathVariable("id") UUID id) {
    return studentService.getStudentById(id).orElse(null);
  }

  @DeleteMapping(path = "{id}")
  public void deleteStudentById(@PathVariable("id") UUID id) {
    studentService.deleteStudentById(id);
  }

  @PutMapping(path = "{id}")
  public void updateStudentById(@PathVariable("id") UUID id, @RequestBody Student student) {
    studentService.updateStudentById(id, student);
  }
}
