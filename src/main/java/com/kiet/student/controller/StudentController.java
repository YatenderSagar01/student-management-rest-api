package com.kiet.student.controller;

import com.kiet.student.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(101, "Rahul Sharma",
                "rahul@kiet.edu", "CSE", 2, 8.65));
        students.add(new Student(102, "Priya Singh",
                "priya@kiet.edu", "CSE", 2, 9.20));
        students.add(new Student(103, "Aman Verma",
                "aman@kiet.edu", "ECE", 3, 8.10));
        students.add(new Student(104, "Neha Gupta",
                "neha@kiet.edu", "CSE", 3, 9.10));
        students.add(new Student(105, "Rohit Kumar",
                "rohit@kiet.edu", "IT", 2, 7.90));
    }

    // Q3: Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    // Q3: Get student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(
            @PathVariable int studentId) {

        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.status(404)
                .body(Map.of("message", "Student not found"));
    }

    // Q4: Add student
    @PostMapping
    public ResponseEntity<?> addStudent(
            @RequestBody Student newStudent) {

        for (Student student : students) {
            if (student.getStudentId() == newStudent.getStudentId()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message",
                                "Student ID already exists"));
            }
        }

        students.add(newStudent);
        return ResponseEntity.status(201).body(newStudent);
    }

    // Q5: Update student
    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int studentId,
            @RequestBody Student updatedStudent) {

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == studentId) {
                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);
                return ResponseEntity.ok(updatedStudent);
            }
        }

        return ResponseEntity.status(404)
                .body(Map.of("message", "Student not found"));
    }

    // Q5: Delete student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable int studentId) {

        boolean removed = students.removeIf(
                s -> s.getStudentId() == studentId);

        if (removed) {
            return ResponseEntity.ok(
                    Map.of("message",
                            "Student deleted successfully"));
        }

        return ResponseEntity.status(404)
                .body(Map.of("message", "Student not found"));
    }

    // Q6: Search by branch and year
    @GetMapping("/search")
    public List<Student> searchStudents(
            @RequestParam String branch,
            @RequestParam int year) {

        return students.stream()
                .filter(s -> s.getBranch()
                        .equalsIgnoreCase(branch))
                .filter(s -> s.getYear() == year)
                .toList();
    }

    // Q6: Find students by minimum CGPA
    @GetMapping("/topper")
    public List<Student> getToppers(
            @RequestParam double cgpa) {

        return students.stream()
                .filter(s -> s.getCgpa() >= cgpa)
                .toList();
    }
}
