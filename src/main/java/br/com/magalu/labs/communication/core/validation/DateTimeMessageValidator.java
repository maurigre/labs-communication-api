package br.com.magalu.labs.communication.core.validation;

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

            if (dateTimeScheduler.isBefore(dataAtual)
                    || dateTimeScheduler.toLocalTime().isBefore(dataAtual.toLocalTime())) {
                valided = false;
            }
        }
        return valided;
    }
}
