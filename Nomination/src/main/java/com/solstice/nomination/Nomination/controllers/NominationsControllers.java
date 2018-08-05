package com.solstice.nomination.Nomination.controllers;

import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.services.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/nominations")
public class NominationsControllers {

    @Autowired
    private NominationService nominationService;

    @GetMapping("/{nomineeId}")
    public List<Nomination> getNominationsForNominee(@PathVariable Long nomineeId) {
        List<Nomination> nominations =  nominationService.getAllNominationsForEmployee(nomineeId);
        if(nominations == null){
            throw new NominationNotFoundException();
        }
        return nominations;
    }

    @GetMapping("/dateBetween")
    public List<Nomination> getNominationsForDateBetween(
            @RequestParam("start")
            @DateTimeFormat(pattern="MM/dd/yyyy") Date startDate,
            @RequestParam("end")
            @DateTimeFormat(pattern="MM/dd/yyyy") Date endDate){
        List<Nomination> nominations = nominationService.getAllNominationByDateBetween(startDate,endDate);
        if(nominations == null){
            return Arrays.asList();
        }
        return nominations;
    }

    @GetMapping("/weekOf")
    public List<Nomination> getNominationsForWeekOf(
            @RequestParam("dateOfWeek")
            @DateTimeFormat(pattern="mm-dd-yyyy") Date dateOfWeek){
        return null;
        // return nominationService.getAllNominationForWeekOf(dateOfWeek);
    }

    @PostMapping("/")
    public Nomination postNomination(
            @RequestBody Nomination newNomination ){
        return null;

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Nomination(s) Not Found")
    class NominationNotFoundException extends RuntimeException {
    }


}
