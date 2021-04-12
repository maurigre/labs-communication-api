package br.com.magalu.labs.communication.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class MessageDto {

    @NotNull(message = "Data hora não pode ser null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC-3")
    private LocalDateTime dateTime;

    @NotNull(message = "Destino não pode ser null")
    private String destiny;

    @NotNull(message = "Mensagem não pode ser null")
    private String message;

    @NotNull(message = "Tipo da mensagem não pode ser null")
    private String type;

    private String status;
}
