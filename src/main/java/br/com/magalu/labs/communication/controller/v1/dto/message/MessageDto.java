package br.com.magalu.labs.communication.controller.v1.dto.message;

import br.com.magalu.labs.communication.validation.DateTimeMessage;
import br.com.magalu.labs.communication.validation.ValidateTypeMessageForDestiny;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(
            value = "Data hora do agendamento envio",
            name = "dateTime",
            dataType = "LocalDateTime",
            example = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeMessage
    @NotNull(message = "Data hora não pode ser null")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "en-US", timezone = "Brazil/East")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC-3")
    private LocalDateTime dateTime;

    @ApiModelProperty(
            value = "Destino e o endereço do destinatario",
            name = "destiny",
            dataType = "String",
            example = "Type (EMAIL) -> teste@teste.com. Type (SMS|PUSH|WHATSAPP) Phone -> 99999999999999")
    @NotNull(message = "Destino não pode ser null")
    private String destiny;

    @ApiModelProperty(
            value = "Mensagem a ser envida",
            name = "message",
            dataType = "String",
            example = "Texto da mensagem")
    @NotNull(message = "Mensagem não pode ser null")
    private String message;


    @ApiModelProperty(
            value = "Especifica o tipo da mensagem",
            name = "type",
            dataType = "String",
            example = "EMAIL")
    @NotNull(message = "Tipo da mensagem não pode ser null")
    @Pattern(regexp="^(EMAIL|SMS|PUSH|WHATSAPP)$",
            message="Para o type apenas os valores EMAIL, SMS, PUSH e WHATSAPP sao aceitos.")
    private String type;

    @ApiModelProperty(
            value = "Status que se encontra a mensagem",
            name = "state",
            dataType = "String",
            example = "SCHEDULED")
    private String state;

}
