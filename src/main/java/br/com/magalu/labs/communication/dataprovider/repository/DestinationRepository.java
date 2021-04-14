package br.com.magalu.labs.communication.dataprovider.repository;

import br.com.magalu.labs.communication.dataprovider.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    Optional<Destination> findByDestiny(String destiny);
}
