package com.solstice.nomination.Nomination.repositories;

import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.models.NominationEntity;
import com.solstice.nomination.Nomination.models.SolsticePrincipals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NominationRepositoryTest {

    //getAllNominationsByWeek
    //getAllNominationsByDateRange
    //postNewNominationForEmployee

    @Autowired
    private NominationRepository repository;
    private List<NominationEntity> nominations;

    @Before
    public void setUp(){
        nominations = getMockNominations();
        repository.save(nominations);
    }

    @Test
    public void getAllNominationsForEmployeeTest(){
        List<NominationEntity> nominations = repository.findAllByNomineeId(3L);
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        List<NominationEntity> expectedList = Arrays.asList(
                new NominationEntity(1L,2L,3L,"12/21/2012",principals,"Mario is the best employee1"),
                new NominationEntity(2L,2L,3L,"12/21/2012",principals,"Mario is the best employee2"),
                new NominationEntity(3L,2L,3L,"07/26/2018",principals,"Mario is the best employee3"),
                new NominationEntity(4L,2L,3L,"07/25/2018",principals,"Mario is the best employee3")
        );
        assertEquals(nominations,expectedList);
    }


    private List<NominationEntity> getMockNominations(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        return Arrays.asList(
                new NominationEntity(1L,2L,3L,"12/21/2012",principals,"Mario is the best employee1"),
                new NominationEntity(2L,2L,3L,"12/21/2012",principals,"Mario is the best employee2"),
                new NominationEntity(3L,2L,3L,"07/26/2018",principals,"Mario is the best employee3"),
                new NominationEntity(4L,2L,3L,"07/25/2018",principals,"Mario is the best employee3"),
                new NominationEntity(5L,2L,5L,"07/22/2018",principals,"Mario is the best employee3"),
                new NominationEntity(6L,2L,4L,"06/26/2018",principals,"Mario is the best employee3")
        );
    }
}
