package br.com.magalu.labs.communication.service.destination;

import br.com.magalu.labs.communication.dataprovider.model.Destination;
import br.com.magalu.labs.communication.dataprovider.repository.DestinationRepository;
import br.com.magalu.labs.communication.service.destination.imp.DestinationServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class DestinationServiceIntegracaoTest {

    @Autowired
    DestinationService destinationService;

    @Autowired
    DestinationRepository destinationRepository;

    final String DESTINY = "teste@teste.com.br";

    @Test
    void shouldSaveDestinationAndReturnDestiny(){
        Optional<Destination> destination = destinationService.create(DESTINY);
        assertTrue(destination.isPresent());
        assertEquals(DESTINY, destination.get().getDestiny());
    }


}
