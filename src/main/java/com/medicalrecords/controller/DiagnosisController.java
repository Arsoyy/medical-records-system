package com.medicalrecords.controller;

import com.medicalrecords.dto.diagnosis.*;
import com.medicalrecords.service.DiagnosisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за работа с диагнози.
 */
@RestController
@RequestMapping("/api/diagnoses")
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    /**
     * Създава диагноза.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DiagnosisResponse createDiagnosis(
            @Valid @RequestBody
            DiagnosisCreateRequest request
    ) {

        return diagnosisService.createDiagnosis(request);
    }

    /**
     * Връща всички диагнози.
     */
    @GetMapping
    public List<DiagnosisResponse> getAllDiagnoses() {

        return diagnosisService.getAllDiagnoses();
    }

    /**
     * Връща диагноза по ID.
     */
    @GetMapping("/{id}")
    public DiagnosisResponse getDiagnosisById(
            @PathVariable Long id
    ) {

        return diagnosisService.getDiagnosisById(id);
    }

    /**
     * Обновява диагноза.
     */
    @PutMapping("/{id}")
    public DiagnosisResponse updateDiagnosis(
            @PathVariable Long id,
            @Valid @RequestBody
            DiagnosisUpdateRequest request
    ) {

        return diagnosisService.updateDiagnosis(
                id,
                request
        );
    }

    /**
     * Изтрива диагноза.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiagnosis(
            @PathVariable Long id
    ) {

        diagnosisService.deleteDiagnosis(id);
    }
}