package br.com.magalu.labs.communication.validations;

import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.controller.v1.dto.response.ResponseErrorValid;
import br.com.magalu.labs.communication.controller.v1.dto.response.ResponseErrorValidField;
import br.com.magalu.labs.communication.core.model.MessageType;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.event.spi.PreCollectionUpdateEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
public class MessageValidation {

    @Getter
    private final List<ResponseErrorValidField> errors = new ArrayList<>();
    private final MessageDto messageDto;
    public static final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public MessageValidation(final MessageDto dto) {
        this.messageDto = dto;
        this.startValidation();
    }

    private void startValidation(){
        this.validDateTimeSchedulerDateTimeNow();
        this.validateMessageTypeDestiny();
    }

    public boolean haveNoErrors(){
        return (errors.size() == 0);
    }

    public ResponseErrorValid getErrorValid()  {
        return ResponseErrorValid.builder()
                .objectName(MessageDto.class.getCanonicalName())
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .message("Requisição possui campos inválidos")
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase()).build();

    }

    private void validDateTimeSchedulerDateTimeNow() {
        if (!isValidDateTimeSheduler())
            errors.add(ResponseErrorValidField.builder().field("dateTime").message("Data e hora do agendamento e invalido").build());
    }

    private void validateMessageTypeDestiny() {
        if (!isMessageTypeNameIsValid())
            errors.add(ResponseErrorValidField.builder().field("type").message("Campo type conflita com dados do campo destiny").build());
    }

    private boolean isMessageTypeNameIsValid() {
        switch (MessageType.valueOf(messageDto.getType())) {
            case SMS:
            case WHATSAPP:
            case PUSH:
                return isValidNumberPhone(messageDto.getDestiny());
            case EMAIL:
                return isValidEmail(messageDto.getDestiny());
        }
        return false;
    }

    private boolean isValidNumberPhone(final String destiny) {
        return Pattern.compile("\\d+").matcher(destiny).matches() && (destiny.length() > 10);
    }

    private boolean isValidEmail(String destiny) {
        Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(destiny);
        return matcher.find();
    }

    private boolean isValidDateTimeSheduler(){
        final ZonedDateTime dateTimeScheduler = messageDto.getDateTime().atZone(ZoneId.systemDefault());
        final ZonedDateTime dataAtual = ZonedDateTime.now();

        if(dateTimeScheduler.isBefore(dataAtual)
                || dateTimeScheduler.toLocalTime().isBefore(dataAtual.toLocalTime())){
            return false;
        }
        return true;
    }



}