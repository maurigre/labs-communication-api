package br.com.magalu.labs.communication.dataprovider.repository;

import br.com.magalu.labs.communication.dataprovider.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
