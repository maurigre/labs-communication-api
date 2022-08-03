package br.com.maurireis.labs.communication.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeMessageValidator implements ConstraintValidator<DateTimeMessage, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime dateTimeMessage, ConstraintValidatorContext context) {
        boolean valided = true;
        if (dateTimeMessage != null) {
            var dateTimeScheduler = dateTimeMessage.atZone(ZoneId.systemDefault());
            var dataAtual = java.time.ZonedDateTime.now();

            if (dateTimeScheduler.toLocalDateTime().isBefore(dataAtual.toLocalDateTime())) {
                valided = false;
            }
        }
        return valided;
    }
}
