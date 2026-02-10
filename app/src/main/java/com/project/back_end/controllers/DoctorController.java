package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    /**
     * Secure endpoint to retrieve doctor availability.
     * Path pattern: /availability/{userRole}/{doctorId}/{date}/{token}
     */
    @GetMapping("/availability/{userRole}/{doctorId}/{date}/{token}")
    public ResponseEntity<Object> getDoctorAvailability(
            @PathVariable String userRole,
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        // 1. Validate the Token
        String username = tokenService.extractUsername(token);
        if (username == null || !tokenService.validateToken(token, username)) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }

        // 2. Business Logic: Fetch Doctor and Check availability
        try {
            Doctor doctor = doctorService.findById(doctorId);
            
            Map<String, Object> response = new HashMap<>();
            response.add("doctorId", doctorId);
            response.add("doctorName", doctor.getName());
            response.add("date", date);
            response.add("isAvailable", doctor.isAvailable());
            response.add("requestedByRole", userRole);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Doctor not found", HttpStatus.NOT_FOUND);
        }
    }
    
    // ... other existing methods (getAllDoctors, saveDoctor, etc.)
}
