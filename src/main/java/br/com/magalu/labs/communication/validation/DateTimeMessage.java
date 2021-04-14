package br.com.magalu.labs.communication.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {DateTimeMessageValidator.class })
public @interface DateTimeMessage {

    String message() default "Data do agendamento da mensagem e inválida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
