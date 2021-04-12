package br.com.magalu.labs.communication.core.dataprovider.repository;

import br.com.magalu.labs.communication.core.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
