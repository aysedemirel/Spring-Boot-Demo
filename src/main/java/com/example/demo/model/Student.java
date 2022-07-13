package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Student {

  private final UUID id;
  @NotBlank
  private final String fullName;

  @Min(1)
  @Max(8)
  @NotNull
  private final int classNumber;
  @NotBlank
  private final String email;

  public Student(
      @JsonProperty("id") UUID id,
      @JsonProperty("fullName") String name,
      @JsonProperty("classNumber") int classNumber,
      @JsonProperty("email") String email
  ) {
    this.id = id;
    this.fullName = name;
    this.classNumber = classNumber;
    this.email = email;
  }

  public UUID getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public int getClassNumber() {
    return classNumber;
  }

  public String getEmail() {
    return email;
  }

}
