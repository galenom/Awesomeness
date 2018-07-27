package com.solstice.nomination.Nomination.services;

import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.models.NominationEntity;
import com.solstice.nomination.Nomination.models.SolsticePrincipals;
import com.solstice.nomination.Nomination.repositories.NominationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NominationServiceTest {

    @Mock
    NominationRepository repository;

    NominationService nominationService;

    List<NominationEntity> nominations;

    @Before
    public void setUp(){
        this.nominationService = new NominationService(repository);
    }

    @Test
    public void getAllNominationsForEmployeeTest(){
        List<SolsticePrincipals> principals = Arrays.asList(SolsticePrincipals.CATCH_EXCELLENCE);
        when(repository.findAllByNomineeId(3L)).thenReturn(Arrays.asList(
                new NominationEntity(1L,2L,3L,"12/21/2012",principals,"Mario is the best employee1"),
                new NominationEntity(2L,2L,3L,"12/21/2012",principals,"Mario is the best employee2"),
                new NominationEntity(3L,2L,3L,"07/26/2018",principals,"Mario is the best employee3"),
                new NominationEntity(4L,2L,4L,"07/25/2018",principals,"Mario is the best employee3"),
                new NominationEntity(5L,2L,4L,"07/25/2018",principals,"Mario is the best employee3"),
                new NominationEntity(6L,2L,5L,"07/25/2018",principals,"Mario is the best employee3"),
                new NominationEntity(7L,2L,6L,"07/25/2018",principals,"Mario is the best employee3")
                ));

         List<Nomination> nominations = nominationService.getAllNominationsForEmployee(3L);
         assertEquals(nominations.size(),3);

    }

}
