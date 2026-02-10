package com.project.back_end.repo;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for Patient entity.
 * Provides abstraction for database operations on the 'patients' table.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Custom query method to find a patient by their associated user email.
     * @param email The email address of the patient.
     * @return An Optional containing the Patient if found.
     */
    Optional<Patient> findByUserEmail(String email);

    /**
     * Custom query method to find a patient by their blood group.
     * @param bloodGroup The blood group string (e.g., "O+").
     * @return A list of patients with that blood group.
     */
    java.util.List<Patient> findByBloodGroup(String bloodGroup);
}
