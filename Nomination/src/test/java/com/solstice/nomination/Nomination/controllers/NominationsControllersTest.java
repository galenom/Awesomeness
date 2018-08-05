package com.solstice.nomination.Nomination.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.solstice.nomination.Nomination.models.Employee;
import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.models.NominationEntity;
import com.solstice.nomination.Nomination.models.SolsticePrincipals;
import com.solstice.nomination.Nomination.services.NominationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NominationsControllers.class)
public class NominationsControllersTest {

    @MockBean
    NominationService nominationService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getNominationsForNominee_Returns200OK() throws Exception{
        Employee nominator = new Employee("Mario", "Galeno", 2L, "111", "Technical Analyst", "mgaleno@solstice.com", "url");
        Employee nominee = new Employee("Kunal", "Shah", 1L, "111", "Technical Analyst", "kshah@solstice.com", "url");
        Date today = new Date();
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        Nomination expectedNomination = new Nomination(nominator,nominee,today,principals,"Description");
        List<Nomination> expectedNominationsList = Arrays.asList(expectedNomination);

        when(nominationService.getAllNominationsForEmployee(1L)).thenReturn(expectedNominationsList);

        mockMvc.perform(get("/api/nominations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nominatedEmployee.employeeNumber", Matchers.is(1)))
                .andExpect(jsonPath("$[0].nominatedByEmployee.employeeNumber", Matchers.is(2)));
    }

    @Test
    public void getNominationForNominee_Returns404() throws Exception{
        when(nominationService.getAllNominationsForEmployee(1L)).thenReturn(null);
        mockMvc.perform(get("/api/nominations/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getNominationsForDateBetweenTest_Returns200OK() throws Exception {
        Employee nominator = new Employee("Mario", "Galeno", 2L, "111", "Technical Analyst", "mgaleno@solstice.com", "url");
        Employee nominee = new Employee("Kunal", "Shah", 1L, "111", "Technical Analyst", "kshah@solstice.com", "url");
        Date startDate = dateHelper("01/01/2012");
        Date endDate = dateHelper("02/01/2012");
        Date betweenDate = dateHelper("01/02/2012");
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        Nomination expectedNomination = new Nomination(nominator,nominee,betweenDate,principals,"Description");
        List<Nomination> expectedNominationsList = Arrays.asList(expectedNomination);

        when(nominationService.getAllNominationByDateBetween(startDate,endDate)).thenReturn(expectedNominationsList);

        mockMvc.perform(get("/api/nominations/dateBetween?start=01/01/2012&end=02/01/2012"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nominatedEmployee.employeeNumber", Matchers.is(1)))
                .andExpect(jsonPath("$[0].nominatedByEmployee.employeeNumber", Matchers.is(2)));
    }

    @Test
    public void getNominationsForDateBetweenTest_ReturnsEmpty() throws Exception {
        Date startDate = dateHelper("01/01/2012");
        Date endDate = dateHelper("02/01/2012");

        when(nominationService.getAllNominationByDateBetween(startDate,endDate)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/nominations/dateBetween?start=01/01/2012&end=02/01/2012"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getNominationsForWeekOfTest_Returns200OK() throws Exception {
        Employee nominator = new Employee("Mario", "Galeno", 2L, "111", "Technical Analyst", "mgaleno@solstice.com", "url");
        Employee nominee = new Employee("Kunal", "Shah", 1L, "111", "Technical Analyst", "kshah@solstice.com", "url");
        Date endDate = dateHelper("02/07/2012");
        Date betweenDate = dateHelper("02/02/2012");
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        Nomination expectedNomination = new Nomination(nominator,nominee,betweenDate,principals,"Description");
        List<Nomination> expectedNominationsList = Arrays.asList(expectedNomination);

        when(nominationService.getAllNominationForWeekOf(endDate)).thenReturn(expectedNominationsList);

        mockMvc.perform(get("/api/nominations/weekOf?dateOfWeek=02/07/2012"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nominatedEmployee.employeeNumber", Matchers.is(1)))
            .andExpect(jsonPath("$[0].nominatedByEmployee.employeeNumber", Matchers.is(2)));
    }

    @Test
    public void getNominationsForWeekOfTest_ReturnsEmpty() throws Exception {
        Date endDate = dateHelper("02/07/2012");

        when(nominationService.getAllNominationForWeekOf(endDate)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/nominations/weekOf?dateOfWeek=02/07/2012"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void postNominationTest() throws Exception {
        Employee nominator = new Employee("Mario", "Galeno", 2L, "111", "Technical Analyst", "mgaleno@solstice.com", "url");
        Employee nominee = new Employee("Kunal", "Shah", 1L, "111", "Technical Analyst", "kshah@solstice.com", "url");
        Date betweenDate = dateHelper("01/02/2012");
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        Nomination newNomination = new Nomination(nominator,nominee,betweenDate,principals,"Description");

        when(nominationService.createNomination(newNomination)).thenReturn(newNomination);

        String json = (new ObjectMapper()).valueToTree(newNomination).toString();

        mockMvc.perform(post("/api/nominations/").contentType(contentType).content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.description", Matchers.is("Description")));
    }

    private Date dateHelper(String dateStr) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        try {
            date = format.parse(dateStr);
        } catch (Exception e) {
            date = new Date();
        }

        return date;
    }

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));
}