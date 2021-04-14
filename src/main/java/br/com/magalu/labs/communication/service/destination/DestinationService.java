package br.com.magalu.labs.communication.service.destination;

import br.com.magalu.labs.communication.dataprovider.model.Destination;

import java.util.Optional;

public interface DestinationService {
    Optional<Destination> create(String destination);
}
