package br.com.magalu.labs.communication.core.service.destination.imp;


import br.com.magalu.labs.communication.core.dataprovider.model.Destination;
import br.com.magalu.labs.communication.core.dataprovider.repository.DestinationRepository;
import br.com.magalu.labs.communication.core.service.destination.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DestinationServiceImp implements DestinationService {

    DestinationRepository destinationRepository;

    @Override
    public Optional<Destination> save(String destiny) {
        return destinationRepository.findByDestiny(destiny)
                .or(() -> Optional.of(
                        destinationRepository.save(newInstanceRecipient(destiny))
                ));
    }

    private Destination newInstanceRecipient(String destiny){
        return Destination.builder().destiny(destiny).build();
    }
}
