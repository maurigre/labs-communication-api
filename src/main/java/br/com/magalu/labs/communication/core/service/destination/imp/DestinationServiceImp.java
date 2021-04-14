package br.com.magalu.labs.communication.core.service.destination.imp;


import br.com.magalu.labs.communication.core.model.Destination;
import br.com.magalu.labs.communication.core.repository.DestinationRepository;
import br.com.magalu.labs.communication.core.service.destination.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DestinationServiceImp implements DestinationService {


    private final DestinationRepository destinationRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Destination> create(String destiny) {
        return destinationRepository.findByDestiny(destiny)
                .or(() -> Optional.of(
                        destinationRepository.save(newInstanceRecipient(destiny))
                ));
    }

    private Destination newInstanceRecipient(String destiny){
        return Destination.builder().destiny(destiny).build();
    }
}
