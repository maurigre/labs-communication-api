package br.com.magalu.labs.communication.controller.v1;

import br.com.magalu.labs.communication.controller.v1.dto.mapper.MessageMapper;
import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.controller.v1.dto.response.Response;
import br.com.magalu.labs.communication.core.model.Message;
import br.com.magalu.labs.communication.core.model.MessageState;
import br.com.magalu.labs.communication.core.service.message.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static br.com.magalu.labs.communication.controller.v1.dto.mapper.MessageMapper.DtoToMessage;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController("Schedule")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(ScheduleController.PATH)
@Api(value = "Labs Communication API", tags = "Labs Communication API")
public class ScheduleController {

    static final String VERSION = "/v1";
    static final String PATH = VERSION + "/schedules";

    private final MessageService messageService;

    @ApiOperation(value = "List all scheduled messages")
    @ApiResponses({
            @ApiResponse(code = 200, message = "List scheduled messages sucessFully"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
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

    @ApiOperation(value = "Get message schedulle by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "returned sucess schedule message"),
            @ApiResponse(code = 404, message = "not found  schedule message"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/{id}")
    public ResponseEntity<Response<MessageDto>> findById(@PathVariable Long id) {
        Response<MessageDto> response = new Response<>();
        final Message message = messageService.findById(id);
        MessageDto dto = MessageMapper.messageToDto(message);
        createSelfLink(dto, id);
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Create schedule message")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created schedule message sucessfully"),
            @ApiResponse(code = 400, message = "bad request schedule message"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<MessageDto>>  create(@RequestBody @Valid MessageDto messageDto) {
        Response<MessageDto> response = new Response<>();
        final Message message = messageService.create(DtoToMessage(messageDto));
        MessageDto dto = MessageMapper.messageToDto(message);
        createSelfLink(dto, message.getId());
        response.setData(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete schedule message")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Deleted schedule message sucessfully"),
            @ApiResponse(code = 404, message = "not found  schedule message"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void createSelfLink(MessageDto dto, Long id) {
        dto.add(linkTo(methodOn(ScheduleController.class).findAllSchedules()).withRel("List Scheduler"));
        dto.add(linkTo(methodOn(ScheduleController.class).findById(id)).withRel("Scheduler"));
        if (!dto.getState().equals(MessageState.DELETED.name()))
            dto.add(linkTo(methodOn(ScheduleController.class).delete(id)).withRel("Delete Scheduler"));
    }

}
