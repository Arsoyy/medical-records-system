package com.medicalrecords.controller;

import com.medicalrecords.dto.examination.*;
import com.medicalrecords.service.ExaminationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за работа с прегледи.
 */
@RestController
@RequestMapping("/api/examinations")
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService examinationService;

    /**
     * Създава преглед.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExaminationResponse createExamination(
            @Valid @RequestBody
            ExaminationCreateRequest request
    ) {

        return examinationService.createExamination(request);
    }

    /**
     * Връща всички прегледи.
     */
    @GetMapping
    public List<ExaminationResponse> getAllExaminations() {

        return examinationService.getAllExaminations();
    }

    /**
     * Връща преглед по ID.
     */
    @GetMapping("/{id}")
    public ExaminationResponse getExaminationById(
            @PathVariable Long id
    ) {

        return examinationService.getExaminationById(id);
    }

    /**
     * Обновява преглед.
     */
    @PutMapping("/{id}")
    public ExaminationResponse updateExamination(
            @PathVariable Long id,
            @Valid @RequestBody
            ExaminationUpdateRequest request
    ) {

        return examinationService.updateExamination(
                id,
                request
        );
    }

    /**
     * Изтрива преглед.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExamination(
            @PathVariable Long id
    ) {

        examinationService.deleteExamination(id);
    }
}