package br.com.maurireis.labs.communication.controller.v1.dto.message;

import br.com.maurireis.labs.communication.validation.DateTimeMessage;
import br.com.maurireis.labs.communication.validation.ValidateTypeMessageForDestiny;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(
            description = "Data hora do agendamento envio",
            name = "dateTime",
            type = "LocalDateTime",
            example = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeMessage
    @NotNull(message = "Data hora não pode ser null")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC-3")
    private LocalDateTime dateTime;

    @Schema(
            description = "Destino e o endereço do destinatario",
            name = "destiny",
            type = "String",
            example = "Type (EMAIL) -> teste@teste.com. Type (SMS|PUSH|WHATSAPP) Phone -> 99999999999999")
    @NotNull(message = "Destino não pode ser null")
    private String destiny;

    @Schema(
            description = "Mensagem a ser envida",
            name = "message",
            type = "string",
            example = "Texto da mensagem")
    @NotNull(message = "Mensagem não pode ser null")
    private String message;


    @Schema(
            description = "Especifica o tipo da mensagem",
            name = "type",
            type = "string",
            example = "EMAIL")
    @NotNull(message = "Tipo da mensagem não pode ser null")
    @Pattern(regexp="^(EMAIL|SMS|PUSH|WHATSAPP)$",
            message="Para o type apenas os valores EMAIL, SMS, PUSH e WHATSAPP sao aceitos.")
    private String type;

    @Schema(
            description = "Status que se encontra a mensagem",
            name = "state",
            type = "string",
            example = "SCHEDULED")
    private String state;

}
