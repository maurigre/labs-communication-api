package br.com.maurireis.labs.communication.service.destination;

import br.com.maurireis.labs.communication.dataprovider.model.Destination;
import br.com.maurireis.labs.communication.dataprovider.repository.DestinationRepository;
import br.com.maurireis.labs.communication.service.destination.imp.DestinationServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

class DestinationServiceUnitTest {

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
                .willReturn(getMockDestinationSceneryNumberOne());

        Optional<Destination> destination = destinationService.create("");

        assertTrue(destination.isPresent());
        assertEquals(DESTINY, destination.get().getDestiny());

    }


    private Destination getMockDestinationSceneryNumberOne(){
        return Destination.builder()
                .id(1l)
                .destiny(DESTINY)
                .build();
    }

}
