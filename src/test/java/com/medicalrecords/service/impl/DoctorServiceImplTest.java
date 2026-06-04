package com.medicalrecords.service.impl;

import com.medicalrecords.dto.doctor.DoctorCreateRequest;
import com.medicalrecords.dto.doctor.DoctorResponse;
import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.User;
import com.medicalrecords.exception.DuplicateResourceException;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.DoctorRepository;
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
 * Unit тестове за DoctorServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    /**
     * Проверява успешно създаване на лекар.
     */
    @Test
    void createDoctor_ShouldCreateDoctorSuccessfully() {

        DoctorCreateRequest request = new DoctorCreateRequest();

        request.setDoctorIdentifier("DOC001");
        request.setFullName("Иван Петров");
        request.setSpecialty("Кардиология");
        request.setUsername("doctor1");
        request.setPassword("123456");

        when(doctorRepository.findByDoctorIdentifier("DOC001"))
                .thenReturn(Optional.empty());

        when(userRepository.findByUsername("doctor1"))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(any()))
                .thenReturn("encodedPassword");

        Doctor doctor = new Doctor();

        doctor.setId(1L);
        doctor.setDoctorIdentifier("DOC001");
        doctor.setFullName("Иван Петров");
        doctor.setSpecialty("Кардиология");

        when(doctorRepository.save(any()))
                .thenReturn(doctor);

        DoctorResponse response =
                doctorService.createDoctor(request);

        assertEquals(
                "DOC001",
                response.getDoctorIdentifier()
        );
    }

    /**
     * Проверява дали се хвърля exception
     * при дублиран идентификатор.
     */
    @Test
    void createDoctor_ShouldThrowException_WhenIdentifierExists() {

        Doctor doctor = new Doctor();

        when(doctorRepository.findByDoctorIdentifier("DOC001"))
                .thenReturn(Optional.of(doctor));

        DoctorCreateRequest request =
                new DoctorCreateRequest();

        request.setDoctorIdentifier("DOC001");

        assertThrows(
                DuplicateResourceException.class,
                () -> doctorService.createDoctor(request)
        );
    }

    /**
     * Проверява дали се хвърля exception
     * при липсващ лекар.
     */
    @Test
    void getDoctorById_ShouldThrowException_WhenDoctorNotFound() {

        when(doctorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> doctorService.getDoctorById(1L)
        );
    }
}