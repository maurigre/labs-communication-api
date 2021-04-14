package br.com.magalu.labs.communication.controller.v1.dto.message;

import br.com.magalu.labs.communication.core.validation.DateTimeMessage;
import br.com.magalu.labs.communication.core.validation.ValidateTypeMessageForDestiny;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
@ValidateTypeMessageForDestiny(valueFieldType="type", valueFieldDestiny="destiny")
public class MessageDto extends RepresentationModel<MessageDto> {

    @DateTimeMessage
    @NotNull(message = "Data hora não pode ser null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC-3")
    private LocalDateTime dateTime;

    @NotNull(message = "Destino não pode ser null")
    private String destiny;

    @NotNull(message = "Mensagem não pode ser null")
    private String message;

    @NotNull(message = "Tipo da mensagem não pode ser null")
    @Pattern(regexp="^(EMAIL|SMS|PUSH|WHATSAPP)$",
            message="Para o type apenas os valores EMAIL, SMS, PUSH e WHATSAPP sao aceitos.")
    private String type;

    private String state;

}
