package com.solstice.nomination.Nomination.repositories;

import com.solstice.nomination.Nomination.models.NominationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NominationRepository extends CrudRepository<NominationEntity, Long> {


    List<NominationEntity> findAllByNomineeId(Long id);

    List<NominationEntity> findAllByDateBetween(String startDate, String endDate);
}
