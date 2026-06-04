package com.medicalrecords.service.impl;

import com.medicalrecords.dto.patient.PatientCreateRequest;
import com.medicalrecords.dto.patient.PatientResponse;
import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.Patient;
import com.medicalrecords.exception.DuplicateResourceException;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.DoctorRepository;
import com.medicalrecords.repository.PatientRepository;
import com.medicalrecords.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit тестове за PatientServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PatientServiceImpl patientService;

    /**
     * Проверява успешно създаване на пациент.
     */
    @Test
    void createPatient_ShouldCreatePatientSuccessfully() {

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFullName("Д-р Иванов");

        when(patientRepository.findByEgn("1234567890"))
                .thenReturn(Optional.empty());

        when(userRepository.findByUsername("patient1"))
                .thenReturn(Optional.empty());

        when(doctorRepository.findById(1L))
                .thenReturn(Optional.of(doctor));

        when(passwordEncoder.encode(any()))
                .thenReturn("encodedPassword");

        Patient patient = new Patient();

        patient.setId(1L);
        patient.setFullName("Георги Георгиев");
        patient.setEgn("1234567890");
        patient.setPersonalDoctor(doctor);

        when(patientRepository.save(any()))
                .thenReturn(patient);

        PatientCreateRequest request =
                new PatientCreateRequest();

        request.setFullName("Георги Георгиев");
        request.setEgn("1234567890");
        request.setPersonalDoctorId(1L);
        request.setUsername("patient1");
        request.setPassword("123456");

        PatientResponse response =
                patientService.createPatient(request);

        assertEquals(
                "1234567890",
                response.getEgn()
        );
    }

    /**
     * Проверява дали се хвърля exception
     * при дублирано ЕГН.
     */
    @Test
    void createPatient_ShouldThrowException_WhenEgnExists() {

        Patient patient = new Patient();

        when(patientRepository.findByEgn("1234567890"))
                .thenReturn(Optional.of(patient));

        PatientCreateRequest request =
                new PatientCreateRequest();

        request.setEgn("1234567890");

        assertThrows(
                DuplicateResourceException.class,
                () -> patientService.createPatient(request)
        );
    }

    /**
     * Проверява дали се хвърля exception
     * при липсващ пациент.
     */
    @Test
    void getPatientById_ShouldThrowException_WhenPatientNotFound() {

        when(patientRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> patientService.getPatientById(1L)
        );
    }
}