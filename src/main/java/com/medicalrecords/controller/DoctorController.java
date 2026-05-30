package com.medicalrecords.controller;

import com.medicalrecords.dto.doctor.DoctorCreateRequest;
import com.medicalrecords.dto.doctor.DoctorResponse;
import com.medicalrecords.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за работа с лекари.
 */
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    /**
     * Създава нов лекар.
     *
     * @param request данните за лекаря
     * @return създаденият лекар
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse createDoctor(
            @Valid @RequestBody DoctorCreateRequest request
    ) {

        return doctorService.createDoctor(request);
    }

    /**
     * Връща всички лекари.
     *
     * @return списък с лекари
     */
    @GetMapping
    public List<DoctorResponse> getAllDoctors() {

        return doctorService.getAllDoctors();
    }

    /**
     * Връща лекар по ID.
     *
     * @param id ID на лекаря
     * @return информация за лекаря
     */
    @GetMapping("/{id}")
    public DoctorResponse getDoctorById(
            @PathVariable Long id
    ) {

        return doctorService.getDoctorById(id);
    }

    /**
     * Изтрива лекар по ID.
     *
     * @param id ID на лекаря
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(
            @PathVariable Long id
    ) {

        doctorService.deleteDoctor(id);
    }
}