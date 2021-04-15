package br.com.magalu.labs.communication.dataprovider.model;


import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationTest {

    private final Long ID = 1L;
    private final String DESTINY = "teste@test.com.br";

    @Test
    @Order(1)
    void shouldCreateDestinationForBuild(){

        Destination builDestinationd = Destination.builder()
                .id(ID).destiny(DESTINY).build();

        assertEquals(ID, builDestinationd.getId());
        assertEquals(DESTINY, builDestinationd.getDestiny());
    }

    @Test
    @Order(2)
    void shouldCreateDestinationConstructorNotArguments(){
        Destination destination = new Destination();
        assertNull(destination.getId());
        assertNull(destination.getDestiny());
    }

    @Test
    @Order(3)
    void shouldCreateDestinationForConstructor() {
        Destination destination = new Destination(ID, DESTINY);
        assertEquals(ID, destination.getId());
        assertEquals(DESTINY, destination.getDestiny());
    }

    @Test
    @Order(4)
    void shouldEqualsAndHashCodeDestination(){
        Destination builDestination = Destination.builder()
                .id(ID).destiny(DESTINY).build();

        Destination destination = new Destination(ID, DESTINY);

        assertTrue(builDestination.equals(destination));
    }

    @Test
    @Order(5)
    void shouldDiffForEqualsAndHashCodeDestination(){
        Destination builDestination = Destination.builder()
                .id(ID).destiny(DESTINY).build();

        Destination destination = new Destination(2L, DESTINY);

        assertFalse(builDestination.equals(destination));
    }

}
