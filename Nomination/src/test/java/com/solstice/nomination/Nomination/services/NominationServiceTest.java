package com.solstice.nomination.Nomination.services;

import com.solstice.nomination.Nomination.models.EmployeeClient;
import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.models.NominationEntity;
import com.solstice.nomination.Nomination.models.SolsticePrincipals;
import com.solstice.nomination.Nomination.repositories.NominationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NominationServiceTest {

    @Mock
    NominationRepository repository;

    @Mock
    EmployeeClient employeeClient;

    NominationService nominationService;

    List<NominationEntity> nominations;

    @Before
    public void setUp(){
        this.nominationService = new NominationService(repository, employeeClient);
    }

    @Test
    public void getAllNominationsForEmployeeTest(){
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        when(repository.findAllByNomineeId(3L)).thenReturn(Arrays.asList(
                new NominationEntity(1L,2L,3L, dateHelper("12/21/2012"), principals,"Mario is the best employee1"),
                new NominationEntity(2L,2L,3L, dateHelper("12/21/2012"), principals,"Mario is the best employee2"),
                new NominationEntity(3L,2L,3L, dateHelper("07/26/2018"), principals,"Mario is the best employee3")
                ));

         List<Nomination> nominations = nominationService.getAllNominationsForEmployee(3L);
         assertEquals(nominations.size(),3);
    }

    public void getAllNominationsForEmployeeReturnsEmptyTest(){
        when(repository.findAllByNomineeId(3L)).thenReturn(Arrays.asList(
        ));

        List<Nomination> nominations = nominationService.getAllNominationsForEmployee(3L);
        assertNull(nominations);
    }

    // TODO Do we need further testing because all we are doing is making a call to Repository and then converting entitiy to pojo
    @Test
    public void getAllNominationsByDateBetweenEmptyTest(){
        when(repository.findAllByDateBetween(new Date(), new Date())).thenReturn(Arrays.asList(
        ));

        List<Nomination> nominations = nominationService.getAllNominationByDateBetween(new Date(), new Date());
        assertNull(nominations);
    }


    // TODO How do we test the complex logic with the calendar and date?
    @Test
    public void getAllNominationsForWeekOfTest(){

    }

    // TODO How do we test this ?
    @Test
    public void createNominationTest(){

    }

    private Date dateHelper(String dateStr) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (Exception e) {
            date = new Date();
        }

        return date;
    }

}
