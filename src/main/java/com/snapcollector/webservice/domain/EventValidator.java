package com.snapcollector.webservice.domain;

import com.snapcollector.webservice.dto.event.EventDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {
    public void validate(EventDto eventDto, Errors errors){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0) {
            errors.rejectValue("basePrice","wrongValue", "BasePrice is wrong.");
            errors.rejectValue("maxPrice","wrongValue", "maxPrice is wrong.");
        }

        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime())
                || endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())
                || endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())) {
            errors.rejectValue("endEventDateTime", "wrongValue", "endEventDatetime is wrong.");
        }

        //TODO beginEventTime
        //TODO beginEnrollTime
        //TODO endEnrolllTime
    }
}
