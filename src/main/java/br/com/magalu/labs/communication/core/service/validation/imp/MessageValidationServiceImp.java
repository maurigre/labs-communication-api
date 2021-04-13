package br.com.magalu.labs.communication.core.service.validation.imp;

import br.com.magalu.labs.communication.controller.v1.dto.message.MessageDto;
import br.com.magalu.labs.communication.controller.v1.dto.response.ResponseErrorValid;
import br.com.magalu.labs.communication.controller.v1.dto.response.ResponseErrorValidField;
import br.com.magalu.labs.communication.core.exception.MessageValidException;
import br.com.magalu.labs.communication.core.model.Message;
import br.com.magalu.labs.communication.core.service.validation.MessageValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

/**
 * @author Mauri Reis
 * @since 13/04/21
 */
@Service
public class MessageValidationServiceImp implements MessageValidationService {


    private List<ResponseErrorValidField> errors;
    private Message message;

    @Override
    public boolean isValidate(Message message) throws MessageValidException {
        this.message = message;
        this.errors = new ArrayList<>();
        validDateTimeSchedulerDateTimeNow();
        validateMessageTypeDestiny();
        if (!haveNoErrors()) throw new MessageValidException(getErrorValid());
        return true;
    }

    @Override
    public ResponseErrorValid getErrorValid()  {
        return ResponseErrorValid.builder()
                .objectName(MessageDto.class.getSimpleName())
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .message("Requisição possui campos inválidos")
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase()).build();

    }

    private boolean isMessageTypeNameIsValid() {
        switch (message.getMessageType()) {
            case SMS:
            case WHATSAPP:
            case PUSH:
                return isValidNumberPhone(message.getDestination().getDestiny());
            case EMAIL:
                return isValidEmail(message.getDestination().getDestiny());
        }
        return false;
    }


    private boolean isValidNumberPhone(String contact) {
        return compile("\\d+").matcher(contact).matches() && (contact.length() > 10);
    }


    private boolean isValidEmail(String contact) {
        Pattern regex_email_address =
                compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", CASE_INSENSITIVE);

        Matcher matcher = regex_email_address.matcher(contact);
        return matcher.find();
    }


    private boolean isValidDateTimeSheduler() {
        final ZonedDateTime dateTimeScheduler = message.getDateTimeSchedule().atZone(ZoneId.systemDefault());
        final ZonedDateTime dataAtual = ZonedDateTime.now();

        if(dateTimeScheduler.isBefore(dataAtual)
                || dateTimeScheduler.toLocalTime().isBefore(dataAtual.toLocalTime())){
            return false;
        }
        return true;
    }


    private void validDateTimeSchedulerDateTimeNow() {
        if (!isValidDateTimeSheduler())
            errors.add(ResponseErrorValidField.builder().field("dateTime").message("Data e hora do agendamento e invalido").build());
    }

    private void validateMessageTypeDestiny() {
        if (!isMessageTypeNameIsValid())
            errors.add(ResponseErrorValidField.builder().field("type").message("Campo type conflita com dados do campo destiny").build());
    }

    private boolean haveNoErrors(){
        return (errors.size() == 0);
    }

}
