package com.medicalrecords.service.impl;

import com.medicalrecords.dto.patient.PatientCreateRequest;
import com.medicalrecords.dto.patient.PatientResponse;
import com.medicalrecords.dto.patient.PatientUpdateRequest;
import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.Patient;
import com.medicalrecords.entity.User;
import com.medicalrecords.entity.enums.RoleType;
import com.medicalrecords.exception.DuplicateResourceException;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.DoctorRepository;
import com.medicalrecords.repository.PatientRepository;
import com.medicalrecords.repository.UserRepository;
import com.medicalrecords.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Имплементация на бизнес логиката за работа с пациенти.
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Създава нов пациент.
     */
    @Override
    public PatientResponse createPatient(
            PatientCreateRequest request
    ) {

        // Проверка за съществуващо ЕГН
        if (patientRepository.findByEgn(
                request.getEgn()).isPresent()) {

            throw new DuplicateResourceException(
                    "Пациент с това ЕГН вече съществува."
            );
        }

        // Проверка за съществуващо потребителско име
        if (userRepository.findByUsername(
                request.getUsername()).isPresent()) {

            throw new DuplicateResourceException(
                    "Потребителското име вече съществува."
            );
        }

        // Намиране на личния лекар
        Doctor personalDoctor =
                doctorRepository.findById(
                                request.getPersonalDoctorId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Личният лекар не беше намерен."
                                ));

        // Създаване на потребителски акаунт
        User user = new User();

        user.setUsername(
                request.getUsername()
        );

        // Криптиране на паролата
        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(
                RoleType.ROLE_PATIENT
        );

        user.setEnabled(true);

        userRepository.save(user);

        // Създаване на пациент
        Patient patient = new Patient();

        patient.setFullName(
                request.getFullName()
        );

        patient.setEgn(
                request.getEgn()
        );

        patient.setInsured(
                request.isInsured()
        );

        patient.setPersonalDoctor(
                personalDoctor
        );

        patient.setUser(user);

        Patient savedPatient =
                patientRepository.save(patient);

        return mapToResponse(savedPatient);
    }

    /**
     * Връща всички пациенти.
     */
    @Override
    public List<PatientResponse> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Връща пациент по ID.
     */
    @Override
    public PatientResponse getPatientById(Long id) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Пациентът не беше намерен."
                                ));

        return mapToResponse(patient);
    }

    /**
     * Обновява информацията за пациент.
     */
    @Override
    public PatientResponse updatePatient(
            Long id,
            PatientUpdateRequest request
    ) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Пациентът не беше намерен."
                                ));

        Doctor personalDoctor =
                doctorRepository.findById(
                                request.getPersonalDoctorId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Личният лекар не беше намерен."
                                ));

        patient.setFullName(
                request.getFullName()
        );

        patient.setEgn(
                request.getEgn()
        );

        patient.setInsured(
                request.isInsured()
        );

        patient.setPersonalDoctor(
                personalDoctor
        );

        Patient updatedPatient =
                patientRepository.save(patient);

        return mapToResponse(updatedPatient);
    }

    /**
     * Изтрива пациент по ID.
     */
    @Override
    public void deletePatient(Long id) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Пациентът не беше намерен."
                                ));

        patientRepository.delete(patient);
    }

    /**
     * Конвертира Patient entity към DTO.
     */
    private PatientResponse mapToResponse(
            Patient patient
    ) {

        PatientResponse response =
                new PatientResponse();

        response.setId(
                patient.getId()
        );

        response.setFullName(
                patient.getFullName()
        );

        response.setEgn(
                patient.getEgn()
        );

        response.setInsured(
                patient.isInsured()
        );

        if (patient.getPersonalDoctor() != null) {

            response.setPersonalDoctorName(
                    patient.getPersonalDoctor()
                            .getFullName()
            );
        }

        return response;
    }
}