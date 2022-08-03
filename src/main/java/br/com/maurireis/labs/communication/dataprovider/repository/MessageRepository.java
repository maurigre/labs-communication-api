package br.com.maurireis.labs.communication.dataprovider.repository;

import br.com.maurireis.labs.communication.dataprovider.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
