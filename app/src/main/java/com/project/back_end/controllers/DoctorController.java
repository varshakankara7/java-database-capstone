package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Doctor-related API endpoints.
 */
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    // Get a specific doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }

    // Update doctor availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<Doctor> updateAvailability(@PathVariable Long id, @RequestParam boolean status) {
        Doctor updatedDoctor = doctorService.updateAvailability(id, status);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Add a new doctor profile
    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }
}
