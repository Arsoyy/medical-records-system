package com.medicalrecords.controller;

import com.medicalrecords.dto.sickleave.*;
import com.medicalrecords.service.SickLeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST контролер за работа с болнични листове.
 */
@RestController
@RequestMapping("/api/sick-leaves")
@RequiredArgsConstructor
public class SickLeaveController {

    private final SickLeaveService sickLeaveService;

    /**
     * Създава болничен лист.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SickLeaveResponse createSickLeave(
            @Valid @RequestBody
            SickLeaveCreateRequest request
    ) {

        return sickLeaveService.createSickLeave(request);
    }

    /**
     * Връща всички болнични листове.
     */
    @GetMapping
    public List<SickLeaveResponse> getAllSickLeaves() {

        return sickLeaveService.getAllSickLeaves();
    }

    /**
     * Връща болничен лист по ID.
     */
    @GetMapping("/{id}")
    public SickLeaveResponse getSickLeaveById(
            @PathVariable Long id
    ) {

        return sickLeaveService.getSickLeaveById(id);
    }

    /**
     * Обновява болничен лист.
     */
    @PutMapping("/{id}")
    public SickLeaveResponse updateSickLeave(
            @PathVariable Long id,
            @Valid @RequestBody
            SickLeaveUpdateRequest request
    ) {

        return sickLeaveService.updateSickLeave(
                id,
                request
        );
    }

    /**
     * Изтрива болничен лист.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSickLeave(
            @PathVariable Long id
    ) {

        sickLeaveService.deleteSickLeave(id);
    }
    @GetMapping("/my")
    public List<SickLeaveResponse> getMySickLeaves() {

        return sickLeaveService
                .getMySickLeaves();
    }
}