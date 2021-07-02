package br.com.magalu.labs.communication.dataprovider.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationUnitTest {

    private final Long ID = 1L;
    private final String DESTINY = "teste@test.com.br";

    @Test
    void shouldHashCodeAttributes(){

        Destination destination = Destination.builder().build();
        assertEquals(6061, destination.hashCode());

        destination = Destination.builder().id(ID).build();
        assertEquals(3583, destination.hashCode());

        destination  = Destination.builder().id(ID).destiny(DESTINY).build();
        assertEquals(-38935584, destination.hashCode());
    }

    @Test
    public void testEqualsSymmetricReturnTrue() {
        Destination x = Destination.builder()
                .id(ID).destiny(DESTINY).build();
        Destination y = Destination.builder()
                .id(ID).destiny(DESTINY).build();
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    public void testEqualsSymmetricReturnFalse() {
        Destination x = Destination.builder()
                .id(ID).destiny(DESTINY).build();
        Destination y = Destination.builder()
                .id(10L).destiny(DESTINY).build();
        assertFalse(x.equals(y) && y.equals(x));
        assertFalse(x.hashCode() == y.hashCode());
    }

    @Test
    void shouldCompareTwoDestinationOneDestinationAndReturnTrue(){
        Destination destination =  Destination.builder()
                .id(ID).destiny(DESTINY).build();
        Destination destination2 =  Destination.builder()
                .id(ID).destiny("1699999999999").build();
        assertEquals(false, destination.equals(destination2));
    }

    @Test
    void shouldCreateDestinationForBuild(){

        Destination builDestinationd = Destination.builder()
                .id(ID).destiny(DESTINY).build();

        assertEquals(ID, builDestinationd.getId());
        assertEquals(DESTINY, builDestinationd.getDestiny());
    }

    @Test
    void shouldCreateDestinationConstructorNotArguments(){
        Destination destination = new Destination();
        assertNull(destination.getId());
        assertNull(destination.getDestiny());
    }

    @Test
    void shouldCreateDestinationForConstructor() {
        Destination destination = new Destination(ID, DESTINY);
        assertEquals(ID, destination.getId());
        assertEquals(DESTINY, destination.getDestiny());
    }

    @Test
    void shouldEqualsAndHashCodeDestination(){
        Destination builDestination = Destination.builder()
                .id(ID).destiny(DESTINY).build();

        Destination destination = new Destination(ID, DESTINY);

        assertTrue(builDestination.equals(destination));
    }

    @Test
    void shouldDiffForEqualsAndHashCodeDestination(){
        Destination builDestination = Destination.builder()
                .id(ID).destiny(DESTINY).build();

        Destination destination = new Destination(2L, DESTINY);

        assertFalse(builDestination.equals(destination));
    }

    @Test
    void shouldDestinationBuilderToString(){
        assertEquals("Destination.DestinationBuilder(id=1, destiny=teste@test.com.br)",
                Destination.builder()
                .id(ID).destiny(DESTINY).toString());
    }

}
