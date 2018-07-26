package com.solstice.nomination.Nomination.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Nomination {
    private Employee nominatedByEmployee;
    private Employee nominatedEmployee;
    private Date date;
    private List<SolsticePrincipals> principals;
    private String description;
}
