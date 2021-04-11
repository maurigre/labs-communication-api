package br.com.magalu.labs.communication.controller.v1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ScheduleController.PATH)
public class ScheduleController {

    static final String VERSION = "/v1";
    static final String PATH = VERSION + "/schedules";


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
