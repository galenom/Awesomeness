package com.solstice.nomination.Nomination.services;

import com.solstice.nomination.Nomination.models.Nomination;
import com.solstice.nomination.Nomination.models.NominationEntity;
import com.solstice.nomination.Nomination.models.SolsticePrincipals;
import com.solstice.nomination.Nomination.repositories.NominationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NominationService {

    @Autowired
    NominationRepository repository;

    public List<Nomination> getAllNominationsForEmployee(Long id){
        List<NominationEntity> nominationEntities = repository.findAllByNomineeId(id);
        if(nominationEntities.isEmpty()){
            return null;
        }
        return null;
    }

    public List<Nomination> getAllNominationByDateBetween(String startDate, String endDate ){
        return null;
    }

    public List<Nomination> getAllNominationForWeekOf(String date){
        return null;
    }

    public void createNomination(String nominatorId, String nomineeId, List<SolsticePrincipals> principals, String description){

    }

    private List<Nomination> convertEntityListToNominationList(List<NominationEntity> nominationEntities){
        List<Nomination> nominations = new ArrayList<Nomination>();
        for(NominationEntity entity : nominationEntities){

        }
        return nominations;
    }

    private Nomination convertEntityToNomination(NominationEntity entity){
        // TODO here we have to make rest calls to Employee to get Employee objects by their ID and such
        // TODO Ask Justin how we should go about doing this because we have noooooo idea...mario has an idea but kunal does not
        return null;
    }




}
