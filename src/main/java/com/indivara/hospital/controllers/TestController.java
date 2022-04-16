package com.indivara.hospital.controllers;
import com.indivara.hospital.models.entities.Registration;
import com.indivara.hospital.models.entities.Treatment;
import com.indivara.hospital.models.entities.User;
import com.indivara.hospital.security.services.RegistrationService;
import com.indivara.hospital.security.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private TreatmentService treatmentService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    //PASIEN
    @GetMapping("/pasien")
    @PreAuthorize("hasRole('PASIEN')")
    public String userAccess() {
        ///funsgi crud registrasi
        return "Pasien Content.";
    }

    @PostMapping("/pasien/registrasi")
    @PreAuthorize("hasRole('PASIEN')")
    public Registration create(@RequestBody Registration registration){

        return registrationService.create(registration);
    }


    //DOKTER
    @GetMapping("/dokter")
    @PreAuthorize("hasRole('DOKTER')")
    public String adminAccess() {
        return "Dokter Board.";
    }

    @GetMapping("/dokter/daftarpasien")
    @PreAuthorize("hasRole('DOKTER')")
    public List<Registration> findAllRegistration(){
        return registrationService.findAllRegistration();
    }

    @PostMapping("/dokter/treatment")
    @PreAuthorize("hasRole('DOKTER')")
    public Treatment create(@RequestBody Treatment treatment){
        return treatmentService.create(treatment);
    }

    @GetMapping("/dokter/daftartreatment")
    @PreAuthorize("hasRole('DOKTER')")
    public List<Treatment> findAllTreatment(){
        return treatmentService.findAllTreatment();
    }


    //ADMIN
    @GetMapping("/superadmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String moderatorAccess() {
        return "Admin Board.";
    }
}
