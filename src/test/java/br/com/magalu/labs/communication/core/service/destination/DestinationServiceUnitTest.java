package br.com.magalu.labs.communication.core.service.destination;

import br.com.magalu.labs.communication.core.model.Destination;
import br.com.magalu.labs.communication.core.repository.DestinationRepository;
import br.com.magalu.labs.communication.core.service.destination.imp.DestinationServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

public class DestinationServiceUnitTest {

    @Autowired
    DestinationService destinationService;

    DestinationRepository destinationRepository;

    final String DESTINY = "teste@teste.com.br";

    @BeforeEach
    void setUp() {
        this.destinationRepository = spy(DestinationRepository.class);
        this.destinationService = new DestinationServiceImp(this.destinationRepository);
    }

    @Test
    void shouldSaveDestinationAndReturnDestiny(){
        BDDMockito.given(destinationRepository.save(Mockito.any(Destination.class)))
                .willReturn(getMockDestination());

        Optional<Destination> destination = destinationService.create("");

        assertTrue(destination.isPresent());
        assertEquals(DESTINY, destination.get().getDestiny());

    }


    public Destination getMockDestination(){
        return Destination.builder()
                .id(1l)
                .destiny(DESTINY)
                .build();
    }

}
