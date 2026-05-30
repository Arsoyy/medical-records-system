package com.medicalrecords.service.impl;

import com.medicalrecords.dto.doctor.DoctorCreateRequest;
import com.medicalrecords.dto.doctor.DoctorResponse;
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
     * Създава нов лекар и потребителски акаунт към него.
     *
     * @param request входните данни
     * @return информация за създадения лекар
     */
    @Override
    public DoctorResponse createDoctor(DoctorCreateRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new DuplicateResourceException(
                    "Потребител с това потребителско име вече съществува."
            );
        }

        if (doctorRepository.findByDoctorIdentifier(
                request.getDoctorIdentifier()).isPresent()) {

            throw new DuplicateResourceException(
                    "Лекар с този идентификационен номер вече съществува."
            );
        }

        // Създаване на потребителски акаунт
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setRole(RoleType.ROLE_DOCTOR);
        user.setEnabled(true);

        userRepository.save(user);

        // Създаване на лекар
        Doctor doctor = new Doctor();
        doctor.setDoctorIdentifier(request.getDoctorIdentifier());
        doctor.setFullName(request.getFullName());
        doctor.setSpecialty(request.getSpecialty());
        doctor.setCanBePersonalDoctor(
                request.isCanBePersonalDoctor()
        );
        doctor.setUser(user);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return mapToDoctorResponse(savedDoctor);
    }

    /**
     * Връща всички лекари.
     *
     * @return списък с лекари
     */
    @Override
    public List<DoctorResponse> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(this::mapToDoctorResponse)
                .toList();
    }

    /**
     * Връща лекар по ID.
     *
     * @param id ID на лекаря
     * @return информация за лекаря
     */
    @Override
    public DoctorResponse getDoctorById(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Лекар с ID " + id + " не беше намерен."
                        ));

        return mapToDoctorResponse(doctor);
    }

    /**
     * Изтрива лекар по ID.
     *
     * @param id ID на лекаря
     */
    @Override
    public void deleteDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Лекар с ID " + id + " не беше намерен."
                        ));

        doctorRepository.delete(doctor);
    }

    /**
     * Конвертира Doctor entity към DoctorResponse DTO.
     *
     * @param doctor entity обект
     * @return DTO обект
     */
    private DoctorResponse mapToDoctorResponse(Doctor doctor) {

        DoctorResponse response = new DoctorResponse();

        response.setId(doctor.getId());
        response.setDoctorIdentifier(
                doctor.getDoctorIdentifier()
        );
        response.setFullName(doctor.getFullName());
        response.setSpecialty(doctor.getSpecialty());
        response.setCanBePersonalDoctor(
                doctor.isCanBePersonalDoctor()
        );

        return response;
    }
}