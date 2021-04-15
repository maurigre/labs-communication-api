package integracao;

import br.com.magalu.labs.communication.CommunicationApplication;
import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.dataprovider.model.Destination;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = {CommunicationApplication.class})
class ApiCommunicationIntegrationTest {


    static final String URL_SERVER = "http://localhost:";
    static final String URL_API = "/v1/schedules";

    private final Long ID = 1L;
    private final LocalDateTime DATE_TIME_SHEDULE = LocalDateTime.now().plusDays(1);
    private final Destination DESTINY = new Destination(1L, "teste@teste.com.br");
    private final String MESSAGE = "Mensagem de teste";
    private final String TYPE = "EMAIL";
    private final String STATE = "SCHEDULED";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateMessageScheduleReturnCreated(){

        HttpEntity<MessageDto> httpEntity = new HttpEntity<>(getMockMessageDtoSceneryNumberOne());

        ResponseEntity<MessageDto> responseEntity = this.restTemplate.exchange(
                URL_SERVER + port + URL_API,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<MessageDto>() {
                });

        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    public MessageDto getMockMessageDtoSceneryNumberOne(){

        return MessageDto.builder()
                .dateTime(DATE_TIME_SHEDULE)
                .message(MESSAGE)
                .type(TYPE)
                .destiny(DESTINY.getDestiny())
                .build();

    }

}
