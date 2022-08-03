package br.com.maurireis.labs.communication.validation;

import br.com.maurireis.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.maurireis.labs.communication.dataprovider.model.Destination;
import br.com.maurireis.labs.communication.dataprovider.model.Message;
import br.com.maurireis.labs.communication.dataprovider.model.MessageState;
import br.com.maurireis.labs.communication.dataprovider.model.MessageType;
import br.com.maurireis.labs.communication.service.message.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
class ValidateTypeMessageForDestinyValidatorIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    MessageService service;

    private final Long ID = 1L;
    private final LocalDateTime DATE_TIME_SHEDULE = LocalDateTime.now().plusDays(1);
    private final Destination DESTINY = new Destination(1L, "teste@teste.com.br");
    private final String MESSAGE = "Mensagem de teste";
    private final String TYPE = "EMAIL";
    private final String STATE = "SCHEDULED";
    static final String URL = "/v1/schedules";

    @Test
    void shouldValidateTypeVsDestinyReturnCreated() throws Exception {
        BDDMockito.given(service.create(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberOne());

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayLoad(DATE_TIME_SHEDULE.toString(), MESSAGE, TYPE, DESTINY.getDestiny()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldValidateTypeVsEmailReturnBadRequestTest() throws Exception {
        BDDMockito.given(service.create(any(Message.class)))
                .willReturn(getMockMessageSceneryNumberOne());

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .content(getJsonPayLoad(DATE_TIME_SHEDULE.toString(), MESSAGE, "SMS", DESTINY.getDestiny()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    public Message getMockMessageSceneryNumberOne(){
        return Message.builder()
                .id(ID)
                .destination(DESTINY)
                .dateTimeSchedule(DATE_TIME_SHEDULE)
                .message(MESSAGE)
                .messageType(MessageType.EMAIL)
                .messageState(MessageState.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public String getJsonPayLoad(String dateTime, String message, String type, String destiny)
            throws JsonProcessingException {

        final MessageDto dto = MessageDto.builder()
                .dateTime(LocalDateTime.parse(dateTime))
                .message(message)
                .type(type)
                .destiny(destiny)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(dto);

    }

}
