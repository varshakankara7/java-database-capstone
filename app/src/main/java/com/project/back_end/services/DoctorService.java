package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Doctor business logic.
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Retrieve all doctors from the database
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    // Find a specific doctor by ID
    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    // Save or Update a doctor profile
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Toggle a doctor's availability status
    public Doctor updateAvailability(Long id, boolean status) {
        Doctor doctor = findById(id);
        doctor.setAvailable(status);
        return doctorRepository.save(doctor);
    }

    // Find doctors by specialization
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
}
