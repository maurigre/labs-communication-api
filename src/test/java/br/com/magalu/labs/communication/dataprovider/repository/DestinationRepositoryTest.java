package br.com.magalu.labs.communication.dataprovider.repository;


import br.com.magalu.labs.communication.dataprovider.model.Destination;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
public class DestinationRepositoryTest {

    @Autowired
    DestinationRepository destinationRepository;

    @AfterAll
   void tearDown() {
        destinationRepository.deleteAll();
    }

    @Test
    void shouldSaveDestinationAndReturnById() {

        Destination destination = Destination.builder()
                .destiny("teste@teste.com.br").build();

        Destination destinationSave = destinationRepository.save(destination);
        System.out.println(destinationSave);

        assertEquals(1L, destinationSave.getId());
    }


}
