package com.solstice.nomination.Nomination.services;

import com.solstice.nomination.Nomination.models.*;
import com.solstice.nomination.Nomination.repositories.NominationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NominationServiceTest {

    @Autowired
    NominationRepository repository;

    @Mock
    EmployeeClient employeeClient;

    NominationService nominationService;

    private List<NominationEntity> nominationEntities;

    @Before
    public void setUp(){
        this.nominationService = new NominationService(repository, employeeClient);

        nominationEntities = getMockNominations();
        repository.save(nominationEntities);
    }

    @Test
    public void getAllNominationsForEmployeeTest(){
         List<Nomination> nominations = nominationService.getAllNominationsForEmployee(3L);
         assertEquals(nominations.size(),4);
    }

    @Test
    public void getAllNominationsForEmployeeReturnsEmptyTest(){
        List<Nomination> nominations = nominationService.getAllNominationsForEmployee(0L);
        assertNull(nominations);
    }

    @Test
    public void getAllNominationsByDateBetweenEmptyTest(){
        List<Nomination> nominations = nominationService.getAllNominationByDateBetween(new Date(), new Date());
        assertNull(nominations);
    }


    @Test
    public void getAllNominationsForWeekOfTest(){
        List<Nomination> nominations = nominationService.getAllNominationForWeekOf(dateHelper("07/26/2018"));
        assertEquals(nominations.size(), 3);
    }

    @Test
    public void createNominationTest(){

        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);

        Date now = new Date();

        Employee nominator = new Employee("Mario", "Galeno", 2L, "111", "Technical Analyst", "mgaleno@solstice.com", "url");
        Employee nominee = new Employee("Kunal", "Shah", 1L, "111", "Technical Analyst", "kshah@solstice.com", "url");

        when(employeeClient.getEmployeeById(1L)).thenReturn(nominee);
        when(employeeClient.getEmployeeById(2L)).thenReturn(nominator);

        Nomination nomination = new Nomination(nominator, nominee, now, principals, "Description");

        nominationService.createNomination(nomination);

        List<Nomination> fetchedNominations = nominationService.getAllNominationsForEmployee(1L);

        Nomination returnedNomination = fetchedNominations.get(0);

        assertEquals((long) returnedNomination.getNominatedEmployee().getEmployeeNumber(), 1L);
        assertEquals((long) returnedNomination.getNominatedByEmployee().getEmployeeNumber(), 2L);
        assertEquals(returnedNomination.getDate(), now);
        assertEquals(new ArrayList(returnedNomination.getPrincipals()), principals);
        assertEquals(returnedNomination.getDescription(), "Description");
    }   

    private List<NominationEntity> getMockNominations(){
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        return Arrays.asList(
                new NominationEntity(1L,2L,3L, dateHelper("12/21/2012"), principals,"Mario is the best employee1"),
                new NominationEntity(2L,2L,3L, dateHelper("12/21/2012"), principals,"Mario is the best employee2"),
                new NominationEntity(3L,2L,3L, dateHelper("07/26/2018"), principals,"Mario is the best employee3"),
                new NominationEntity(4L,2L,3L, dateHelper("07/25/2018"), principals,"Mario is the best employee3"),
                new NominationEntity(5L,2L,5L, dateHelper("07/22/2018"), principals,"Mario is the best employee3"),
                new NominationEntity(6L,2L,4L, dateHelper("06/26/2018"), principals,"Mario is the best employee3")
        );
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

}
