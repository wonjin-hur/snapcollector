package com.snapcollector.webservice.dto.score;

import com.snapcollector.webservice.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder @NoArgsConstructor @AllArgsConstructor
@Data
public class ScoreDto {

    @NotEmpty
    private Account account;
    @NotEmpty
    private String photographer;
    @NotEmpty
    private String location;
    @NotEmpty
    private Integer score;

}
