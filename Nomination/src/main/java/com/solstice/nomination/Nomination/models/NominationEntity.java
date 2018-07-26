package com.solstice.nomination.Nomination.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NominationEntity {

    @Id
    @GeneratedValue
    Long id;
    Long nominatorId;
    Long nomineeId;
    @CreatedDate
    private Date date;
    private List<SolsticePrincipals> principals;
    private String description;

}
