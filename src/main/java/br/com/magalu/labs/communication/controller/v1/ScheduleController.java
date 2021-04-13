package br.com.magalu.labs.communication.controller.v1;

import br.com.magalu.labs.communication.controller.v1.dto.mapper.MessageMapper;
import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.controller.v1.dto.response.Response;
import br.com.magalu.labs.communication.core.exception.MessageValidException;
import br.com.magalu.labs.communication.core.model.Message;
import br.com.magalu.labs.communication.core.service.message.MessageService;
import br.com.magalu.labs.communication.core.service.rabbitmq.RabbitMqService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.magalu.labs.communication.controller.v1.dto.mapper.MessageMapper.DtoToMessage;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(ScheduleController.PATH)
public class ScheduleController {

    static final String VERSION = "/v1";
    static final String PATH = VERSION + "/schedules";

    private final MessageService messageService;
    private final RabbitMqService rabbitMqService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<MessageDto>>> findAllSchedules() {
        List<MessageDto> messageDtos = new ArrayList<>();
        messageService.findAll().stream()
                .forEach(message -> {
                    MessageDto dto = MessageMapper.messageToDto(message);
                    createSelfLink(dto, message.getId());
                    messageDtos.add(dto);
                });
        Response<List<MessageDto>> response = new Response<>();
        response.setData(messageDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Response<MessageDto>> findById(@PathVariable Long id) {
        Optional<Message> messageOptional = messageService.findById(id);
        if (!messageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Response<MessageDto> response = new Response<>();
        MessageDto dto = MessageMapper.messageToDto(messageOptional.get());
        createSelfLink(dto, id);
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>  create(@RequestBody @Valid MessageDto messageDto) throws MessageValidException {
        Response<MessageDto> response = new Response<>();
        final Message message = messageService.save(DtoToMessage(messageDto)).get();
        MessageDto dto = MessageMapper.messageToDto(message);
        createSelfLink(dto, message.getId());
        rabbitMqService.producer(message);
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> delete(@PathVariable Long id) {
        Response<?> response = new Response<>();
        Optional<Message> messageOptional = messageService.deleteById(id);
        if (!messageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    private void createSelfLink(MessageDto dto, Long id) {
        dto.add(linkTo(methodOn(ScheduleController.class).findAllSchedules()).withRel("List Scheduler"));
        dto.add(linkTo(methodOn(ScheduleController.class).findById(id)).withRel("Scheduler"));
        dto.add(linkTo(methodOn(ScheduleController.class).delete(id)).withRel("Delete Scheduler"));
    }

}
