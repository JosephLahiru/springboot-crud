package com.ictec.springbootcrud.springbootcrud.controllers;

import com.ictec.springbootcrud.springbootcrud.httpentities.Student;
import com.ictec.springbootcrud.springbootcrud.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo stuRepo;

    @GetMapping("/students")
    public String getAllStudents(Model model){
        List<Student> students = stuRepo.findAll();
        model.addAttribute("students", students);
        return "student-list";
    }

    @GetMapping("/students/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model) {
        Optional<Student> studentOptional = stuRepo.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            model.addAttribute("student", student);
        }
        return "student-details";
    }

    @GetMapping("/students/new")
    public String showStudentCreationForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @PostMapping("/students")
    public String createStudent(@ModelAttribute("student") Student student) {
        stuRepo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/edit")
    public String showStudentUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Student> studentOptional = stuRepo.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            model.addAttribute("student", student);
            return "student-form";
        }
        return "redirect:/students";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student) {
        student.setId(id);
        stuRepo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) {
        stuRepo.deleteById(id);
        return "redirect:/students";
    }
}