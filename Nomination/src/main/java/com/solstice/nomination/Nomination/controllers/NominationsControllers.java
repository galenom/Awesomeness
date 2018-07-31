package com.solstice.nomination.Nomination.controllers;

import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.services.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nominations")
public class NominationsControllers {

    @Autowired
    private NominationService nominationService;

    @GetMapping("/{nomineeId}")
    public List<Nomination> getNominationsForNominee(@PathVariable Long nomineeId) {
        return nominationService.getAllNominationsForEmployee(nomineeId);
    }
}
