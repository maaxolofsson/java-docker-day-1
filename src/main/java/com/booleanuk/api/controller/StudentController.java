package com.booleanuk.api.controller;

import com.booleanuk.api.model.Student;
import com.booleanuk.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    private StudentRepository students;

    @GetMapping
    public List<Student> getAll() {
        return this.students.findAll();
    }

    @GetMapping("{id}")
    public Student getOne(@PathVariable int id) {
        Student toReturn = this.students.findById(id).orElse(null);
        if (toReturn == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return toReturn;
    }

    @PostMapping
    public Student create(@RequestBody Student toAdd) {
        return this.students.save(toAdd);
    }

    @DeleteMapping("{id}")
    public Student delete(@PathVariable int id) {
        Student toDelete = this.students.findById(id).orElse(null);
        if (toDelete == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.students.delete(toDelete);
        return toDelete;
    }

    @PutMapping("{id}")
    public Student update(@PathVariable int id, @RequestBody Student newData) {
        Student toUpdate = this.students.findById(id).orElse(null);
        if (toUpdate == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        toUpdate.setDob(newData.getDob());
        toUpdate.setAverageGrade(newData.getAverageGrade());
        toUpdate.setFirstName(newData.getFirstName());
        toUpdate.setLastName(newData.getLastName());
        toUpdate.setCourseTitle(newData.getCourseTitle());
        toUpdate.setCourseStartDate(newData.getCourseStartDate());

        this.students.save(toUpdate);

        return toUpdate;
    }
}
