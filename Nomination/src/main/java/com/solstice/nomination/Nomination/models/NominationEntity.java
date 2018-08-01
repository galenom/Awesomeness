package com.solstice.nomination.Nomination.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class NominationEntity {

    @Id
    @GeneratedValue
    Long id;
    Long nominatorId;
    Long nomineeId;
    @CreatedDate
    private Date date;
    @ElementCollection
    @JoinTable(name = "tblPrincipals", joinColumns = @JoinColumn(name = "nomineeId"))
    @Column(name = "principal", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<SolsticePrincipals> principals;
    private String description;

    public NominationEntity(Long nominatorId, Long nomineeId, Date date, Collection<SolsticePrincipals> principals, String description) {
        this.nominatorId = nominatorId;
        this.nomineeId = nomineeId;
        this.date = date;
        this.principals = principals;
        this.description = description;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o instanceof NominationEntity && ((NominationEntity)o).id == this.id){
            return true;
        }
        return false;

    }

}
