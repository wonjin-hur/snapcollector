package com.snapcollector.webservice.domain;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Description;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    public void builder() {
        Event event = Event.builder().build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        //Given
        String event1 = "Event";
        String desc = "desc";

        //When
        Event event = Event.builder().build();
        event.setName(event1);
        event.setDescription(desc);

        //Then
        assertThat(event.getName()).isEqualTo(event1);
        assertThat(event.getDescription()).isEqualTo(desc);
    }

    @Test
    @Parameters
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        event.update();

        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree() {
        return new Object[][] {
          new Object[] { 0,0,true },
          new Object[] { 0,100,false },
          new Object[] { 100,0,false }
        };
    }


    @Test
    @Parameters
    public void testOffline(String location, boolean isOffline) {
        Event event = Event.builder()
                .location(location)
                .build();

        event.update();

        assertThat(event.isOffline()).isEqualTo(isOffline);
    }

    private Object[] parametersForTestOffline() {
        return new Object[][] {
                new Object[] { "abc",true },
                new Object[] { "",false },
                new Object[] { null,false }
        };
    }
}