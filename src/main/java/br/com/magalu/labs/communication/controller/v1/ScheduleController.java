package br.com.magalu.labs.communication.controller.v1;

import br.com.magalu.labs.communication.core.service.message.MessageService;
import br.com.magalu.labs.communication.service.rabbitmq.RabbitMqService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(ScheduleController.PATH)
public class ScheduleController {

    static final String VERSION = "/v1";
    static final String PATH = VERSION + "/schedules";

    private final MessageService messageService;
    private final RabbitMqService rabbitMqService;

    @GetMapping
    public String findAllSchedules() {
        return "Ola mundo";
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable Long id) {

    }

    @PostMapping
    public void create() {
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    }

    @PutMapping
    public void update() {

    }

}
