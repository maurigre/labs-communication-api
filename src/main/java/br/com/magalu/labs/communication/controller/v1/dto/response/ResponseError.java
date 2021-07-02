package br.com.magalu.labs.communication.controller.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@SuperBuilder
@ToString
@Accessors(chain = true)
public class ResponseError {

    @NotNull(message="Timestamp cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private final int code;
    private final String status;
    @NotNull(message="Message cannot be null")
    private String message;
}
