package br.com.magalu.labs.communication.core.service.destination;

import br.com.magalu.labs.communication.core.dataprovider.model.Destination;

import java.util.Optional;

public interface DestinationService {
    Optional<Destination> save(String destination);
}
