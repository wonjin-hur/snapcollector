package com.snapcollector.webservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.snapcollector.webservice.domain.Event;
import com.snapcollector.webservice.domain.EventRepository;
import com.snapcollector.webservice.domain.EventStatus;
import com.snapcollector.webservice.dto.event.EventDto;
import com.snapcollector.webservice.util.RestDocsConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ActiveProfiles("test, oauth")
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEvent1() throws Exception {

    }

    @Test
    public void createEvent() throws Exception {

        EventDto event = EventDto.builder()
                .description("desc")
                .beginEventDateTime(LocalDateTime.of(2018,11,13,13,00))
                .endEventDateTime(LocalDateTime.of(2018,12,20,18,00))
                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,15,13,00))
                .beginEventDateTime(LocalDateTime.of(2018,12,23,13,00))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("sdf")
                .build();

        mockMvc.perform(post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                  .andExpect(status().is(403))
//                .andExpect(jsonPath("name").exists())
//                .andExpect(jsonPath("free").value(false))
//                .andExpect(jsonPath("offline").value(true))
//                .andExpect(header().exists(HttpHeaders.LOCATION))
//                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaTypes.HAL_JSON_VALUE))
//                .andExpect(jsonPath("_links.self").exists())
//                .andExpect(jsonPath("_links.query-events").exists())
//                .andExpect(jsonPath("_links.update-event").exists())
                .andDo(document("create-event",
//                        links(linkWithRel("self").description("link to self"),
//                                linkWithRel("query-events").description("link to query-events"),
//                                linkWithRel("update-event").description("link to update-event")
//                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        )
                        ));
    }
//
    @Test
    public void createEvent_badrequest() throws Exception {

        Event event = Event.builder()
                .id(100).name("name")
                .description("desc")
                .beginEventDateTime(LocalDateTime.of(2018,11,13,13,00))
                .endEventDateTime(LocalDateTime.of(2018,12,20,18,00))
                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,15,13,00))
                .beginEventDateTime(LocalDateTime.of(2018,12,23,13,00))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("sdf")
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("_links.index").exists());
    }
//
//    @Test
//    public void createEvent_Bad_Request_Empty_Input() throws Exception {
//        EventDto eventDto = EventDto.builder().build();
//
//        this.mockMvc.perform(post("/api/events")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(eventDto)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void createEvent_Bad_Request_rejectedvalue() throws Exception {
//        EventDto eventDto = EventDto.builder()
//                .description("desc")
//                .beginEventDateTime(LocalDateTime.of(2018,11,26,13,00))
//                .closeEnrollmentDateTime(LocalDateTime.of(2018,11,15,13,00))
//                .endEventDateTime(LocalDateTime.of(2018,12,20,18,00))
//                .beginEventDateTime(LocalDateTime.of(2018,12,23,13,00))
//                .basePrice(10000)
//                .maxPrice(200)
//                .limitOfEnrollment(100)
//                .location("sdf")
//                .build();
//
//        this.mockMvc.perform(post("/api/events")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(eventDto)))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$[0].field").exists())
//                .andExpect(jsonPath("$[0].defaultMessage").exists())
//                .andExpect(jsonPath("$[0].code").exists())
//        ;
//    }


}
