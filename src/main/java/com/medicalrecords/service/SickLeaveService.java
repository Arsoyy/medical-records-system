package com.medicalrecords.service;

import com.medicalrecords.dto.sickleave.*;

import java.util.List;

/**
 * Интерфейс за работа с болнични листове.
 */
public interface SickLeaveService {

    /**
     * Създава болничен лист.
     */
    SickLeaveResponse createSickLeave(
            SickLeaveCreateRequest request
    );

    /**
     * Връща всички болнични листове.
     */
    List<SickLeaveResponse> getAllSickLeaves();

    /**
     * Връща болничен лист по ID.
     */
    SickLeaveResponse getSickLeaveById(Long id);

    /**
     * Обновява болничен лист.
     */
    SickLeaveResponse updateSickLeave(
            Long id,
            SickLeaveUpdateRequest request
    );

    /**
     * Изтрива болничен лист.
     */
    void deleteSickLeave(Long id);

    List<SickLeaveResponse> getMySickLeaves();
}