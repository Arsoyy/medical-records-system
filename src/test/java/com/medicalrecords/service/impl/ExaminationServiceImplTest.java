package com.medicalrecords.service.impl;

import com.medicalrecords.dto.examination.ExaminationCreateRequest;
import com.medicalrecords.dto.examination.ExaminationResponse;
import com.medicalrecords.entity.*;
import com.medicalrecords.entity.enums.PaymentType;
import com.medicalrecords.exception.ResourceNotFoundException;
import com.medicalrecords.repository.DiagnosisRepository;
import com.medicalrecords.repository.DoctorRepository;
import com.medicalrecords.repository.ExaminationRepository;
import com.medicalrecords.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit тестове за ExaminationServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class ExaminationServiceImplTest {

    @Mock
    private ExaminationRepository examinationRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DiagnosisRepository diagnosisRepository;

    @InjectMocks
    private ExaminationServiceImpl examinationService;

    /**
     * Проверява дали осигурен пациент
     * получава NHIF като платец.
     */
    @Test
    void createExamination_ShouldSetNHIF_WhenPatientIsInsured() {

        Doctor doctor = new Doctor();
        doctor.setId(1L);

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setInsured(true);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setName("Грип");

        ExaminationCreateRequest request =
                new ExaminationCreateRequest();

        request.setDoctorId(1L);
        request.setPatientId(1L);
        request.setDiagnosisId(1L);
        request.setTreatment("Почивка");
        request.setPrice(BigDecimal.valueOf(50));

        when(doctorRepository.findById(1L))
                .thenReturn(Optional.of(doctor));

        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));

        when(diagnosisRepository.findById(1L))
                .thenReturn(Optional.of(diagnosis));

        when(examinationRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ExaminationResponse response =
                examinationService.createExamination(request);

        assertEquals(
                "NHIF",
                response.getPaymentType()
        );
    }

    /**
     * Проверява дали неосигурен пациент
     * плаща сам прегледа.
     */
    @Test
    void createExamination_ShouldSetPatient_WhenPatientIsNotInsured() {

        Doctor doctor = new Doctor();
        doctor.setId(1L);

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setInsured(false);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1L);
        diagnosis.setName("Грип");

        ExaminationCreateRequest request =
                new ExaminationCreateRequest();

        request.setDoctorId(1L);
        request.setPatientId(1L);
        request.setDiagnosisId(1L);
        request.setTreatment("Почивка");
        request.setPrice(BigDecimal.valueOf(50));

        when(doctorRepository.findById(1L))
                .thenReturn(Optional.of(doctor));

        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));

        when(diagnosisRepository.findById(1L))
                .thenReturn(Optional.of(diagnosis));

        when(examinationRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ExaminationResponse response =
                examinationService.createExamination(request);

        assertEquals(
                "PATIENT",
                response.getPaymentType()
        );
    }

    /**
     * Проверява дали се хвърля exception,
     * когато лекарят не съществува.
     */
    @Test
    void createExamination_ShouldThrowException_WhenDoctorNotFound() {

        ExaminationCreateRequest request =
                new ExaminationCreateRequest();

        request.setDoctorId(1L);

        when(doctorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> examinationService.createExamination(request)
        );
    }
}