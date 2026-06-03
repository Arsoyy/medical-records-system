package com.medicalrecords.controller;

import com.medicalrecords.dto.doctor.*;
import com.medicalrecords.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за лекари.
 */
@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    /**
     * Създава лекар.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse createDoctor(
            @Valid @RequestBody
            DoctorCreateRequest request
    ) {

        return doctorService.createDoctor(request);
    }

    /**
     * Връща всички лекари.
     */
    @GetMapping
    public List<DoctorResponse> getAllDoctors() {

        return doctorService.getAllDoctors();
    }

    /**
     * Връща лекар по ID.
     */
    @GetMapping("/{id}")
    public DoctorResponse getDoctorById(
            @PathVariable Long id
    ) {

        return doctorService.getDoctorById(id);
    }

    /**
     * Обновява лекар.
     */
    @PutMapping("/{id}")
    public DoctorResponse updateDoctor(
            @PathVariable Long id,
            @Valid @RequestBody
            DoctorUpdateRequest request
    ) {

        return doctorService.updateDoctor(
                id,
                request
        );
    }

    /**
     * Изтрива лекар.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(
            @PathVariable Long id
    ) {

        doctorService.deleteDoctor(id);
    }
}