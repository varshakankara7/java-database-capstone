package com.project.back_end.services;

import com.project.back_end.models.Appointment;
import com.project.back_end.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to Appointments.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Retrieve all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Schedule a new appointment
    public Appointment scheduleAppointment(Appointment appointment) {
        // Business logic (e.g., checking doctor availability) would go here
        return appointmentRepository.save(appointment);
    }

    // Find appointments by Patient ID
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    // Cancel an appointment
    public void cancelAppointment(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if (appointment.isPresent()) {
            Appointment existingAppointment = appointment.get();
            existingAppointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
            appointmentRepository.save(existingAppointment);
        }
    }
}
