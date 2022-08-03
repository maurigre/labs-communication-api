package br.com.maurireis.labs.communication.service.destination;

import br.com.maurireis.labs.communication.dataprovider.model.Destination;

import java.util.Optional;

public interface DestinationService {
    Optional<Destination> create(String destination);
}
