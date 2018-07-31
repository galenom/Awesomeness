package com.solstice.nomination.Nomination.services;

import com.solstice.nomination.Nomination.models.*;
import com.solstice.nomination.Nomination.repositories.NominationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NominationService {

    NominationRepository repository;
    EmployeeClient employeeClient;

    @Autowired
    public NominationService(NominationRepository repository, EmployeeClient employeeClient){
        this.employeeClient = employeeClient;
        this.repository = repository;
    }

    public List<Nomination> getAllNominationsForEmployee(Long id){
        List<NominationEntity> nominationEntities = repository.findAllByNomineeId(id);
        if(nominationEntities.isEmpty()){
            return null;
        }
        return convertEntityListToNominationList(nominationEntities);
    }

    public List<Nomination> getAllNominationByDateBetween(Date startDate, Date endDate ) {
        List<NominationEntity> nominationEntities = repository.findAllByDateBetween(startDate,endDate);
        if(nominationEntities.isEmpty()){
            return null;
        }
        return convertEntityListToNominationList(nominationEntities);
    }

    public List<Nomination> getAllNominationForWeekOf(Date currentDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE,-7);

        Date startDate = calendar.getTime();

        List<NominationEntity> nominationEntities = repository.findAllByDateBetween(startDate,currentDate);
        if(nominationEntities.isEmpty()){
            return null;
        }
        return convertEntityListToNominationList(nominationEntities);
    }


    // TODO Set these up into the controller, write tests for the controller, and then test the controller via url calls
    public void createNomination(Long nominatorId, Long nomineeId, List<SolsticePrincipals> principals, String description){
        NominationEntity nominationEntity = new NominationEntity(nominatorId,nomineeId, new Date(), principals, description);
        repository.save(nominationEntity);
    }

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
        Date date = entity.getDate();
        String description = entity.getDescription();
        return new Nomination(nominator,nominee,date,(List)principals,description);
    }




}
