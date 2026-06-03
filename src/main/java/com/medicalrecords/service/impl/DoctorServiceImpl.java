package com.medicalrecords.service.impl;

import com.medicalrecords.dto.doctor.DoctorCreateRequest;
import com.medicalrecords.dto.doctor.DoctorResponse;
import com.medicalrecords.dto.doctor.DoctorUpdateRequest;
import com.medicalrecords.entity.Doctor;
import com.medicalrecords.entity.User;
import com.medicalrecords.entity.enums.RoleType;
import com.medicalrecords.exception.DuplicateResourceException;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.DoctorRepository;
import com.medicalrecords.repository.UserRepository;
import com.medicalrecords.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Имплементация на бизнес логиката за работа с лекари.
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Създава нов лекар и потребителски акаунт.
     */
    @Override
    public DoctorResponse createDoctor(
            DoctorCreateRequest request
    ) {

        // Проверка за съществуващ идентификатор на лекар
        if (doctorRepository.findByDoctorIdentifier(
                request.getDoctorIdentifier()).isPresent()) {

            throw new DuplicateResourceException(
                    "Лекар с този идентификатор вече съществува."
            );
        }

        // Проверка за съществуващо потребителско име
        if (userRepository.findByUsername(
                request.getUsername()).isPresent()) {

            throw new DuplicateResourceException(
                    "Потребителското име вече съществува."
            );
        }

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
                RoleType.ROLE_DOCTOR
        );

        user.setEnabled(true);

        userRepository.save(user);

        // Създаване на лекар
        Doctor doctor = new Doctor();

        doctor.setDoctorIdentifier(
                request.getDoctorIdentifier()
        );

        doctor.setFullName(
                request.getFullName()
        );

        doctor.setSpecialty(
                request.getSpecialty()
        );

        doctor.setCanBePersonalDoctor(
                request.isCanBePersonalDoctor()
        );

        // Свързване на лекаря с потребителя
        doctor.setUser(user);

        Doctor savedDoctor =
                doctorRepository.save(doctor);

        return mapToResponse(savedDoctor);
    }

    /**
     * Връща всички лекари.
     */
    @Override
    public List<DoctorResponse> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Връща лекар по ID.
     */
    @Override
    public DoctorResponse getDoctorById(Long id) {

        Doctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Лекарят не беше намерен."
                                ));

        return mapToResponse(doctor);
    }

    /**
     * Обновява информацията за лекар.
     */
    @Override
    public DoctorResponse updateDoctor(
            Long id,
            DoctorUpdateRequest request
    ) {

        Doctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Лекарят не беше намерен."
                                ));

        doctor.setFullName(
                request.getFullName()
        );

        doctor.setSpecialty(
                request.getSpecialty()
        );

        doctor.setCanBePersonalDoctor(
                request.isCanBePersonalDoctor()
        );

        Doctor updatedDoctor =
                doctorRepository.save(doctor);

        return mapToResponse(updatedDoctor);
    }

    /**
     * Изтрива лекар по ID.
     */
    @Override
    public void deleteDoctor(Long id) {

        Doctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Лекарят не беше намерен."
                                ));

        doctorRepository.delete(doctor);
    }

    /**
     * Конвертира Doctor entity към DoctorResponse DTO.
     */
    private DoctorResponse mapToResponse(
            Doctor doctor
    ) {

        DoctorResponse response =
                new DoctorResponse();

        response.setId(
                doctor.getId()
        );

        response.setDoctorIdentifier(
                doctor.getDoctorIdentifier()
        );

        response.setFullName(
                doctor.getFullName()
        );

        response.setSpecialty(
                doctor.getSpecialty()
        );

        response.setCanBePersonalDoctor(
                doctor.isCanBePersonalDoctor()
        );

        return response;
    }
}