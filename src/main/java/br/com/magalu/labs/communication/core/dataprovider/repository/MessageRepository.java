package br.com.magalu.labs.communication.core.dataprovider.repository;

import br.com.magalu.labs.communication.core.dataprovider.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
