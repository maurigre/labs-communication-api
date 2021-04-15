package br.com.magalu.labs.communication.dataprovider.repository;


import br.com.magalu.labs.communication.dataprovider.model.Destination;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class})
public class DestinationRepositoryIntegrationTest {

    @Autowired
    DestinationRepository destinationRepository;

    @AfterAll
    void tearDown() {
        destinationRepository.deleteAll();
    }

    @Test
    @Order(1)
    void shouldSaveDestinationAndReturnById() {
        Destination destination = Destination.builder()
                .id(1L)
                .destiny("teste@teste.com.br").build();

        Destination destinationSave = destinationRepository.save(destination);
        assertEquals(1L, destinationSave.getId());
    }

    @Test
    @Order(2)
    void shouldFindByIdDestinationSuccess() {
        final Optional<Destination> byDestiny = destinationRepository.findByDestiny("teste@teste.com.br");

        assertTrue(byDestiny.isPresent());
        assertEquals(1L, byDestiny.get().getId());
    }

    @Test
    @Order(3)
    void shouldFindByIdDestinationFail() {
        final Optional<Destination> byDestiny = destinationRepository.findByDestiny("testea@teste.com.br");

        assertFalse(byDestiny.isPresent());
    }


}
