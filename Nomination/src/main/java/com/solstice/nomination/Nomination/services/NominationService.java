package com.solstice.nomination.Nomination.services;

import com.solstice.nomination.Nomination.models.*;
import com.solstice.nomination.Nomination.repositories.NominationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class NominationService {

    NominationRepository repository;
    EmployeeClient employeeClient;

    @Autowired
    public NominationService(NominationRepository repository ,EmployeeClient employeeClient){
        this.employeeClient = employeeClient;
        this.repository = repository;
    }

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

    //TODO TEST NOMINATION SERVICE
    private List<Nomination> convertEntityListToNominationList(List<NominationEntity> nominationEntities){
        List<Nomination> nominations = new ArrayList<Nomination>();
        for(NominationEntity entity : nominationEntities){
            Nomination nomination = convertEntityToNomination(entity);
            nominations.add(nomination);
        }
        return nominations;
    }

    private Nomination convertEntityToNomination(NominationEntity entity){
        Employee nominee = employeeClient.getEmployeeById(entity.getNomineeId());
        Employee nominator = employeeClient.getEmployeeById(entity.getNominatorId());
        Collection<SolsticePrincipals> principals = entity.getPrincipals();
        Date date= null;
        try {
            date = new SimpleDateFormat("mm/dd/yyyy").parse(entity.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String description = entity.getDescription();
        return new Nomination(nominator,nominee,date,(List)principals,description);
    }




}
