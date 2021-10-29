package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student ayse = new Student("Ayse", "aysedemireldeniz@gmail.com", LocalDate.of(1996, Month.JULY, 23));
            Student mustafa = new Student("Mustafa", "mustafaahmetdeniz@gmail.com", LocalDate.of(1994, Month.SEPTEMBER, 15));
            repository.saveAll(List.of(ayse, mustafa));
        };
    }
}
