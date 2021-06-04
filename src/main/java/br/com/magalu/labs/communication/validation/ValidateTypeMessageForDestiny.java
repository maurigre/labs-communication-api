package br.com.magalu.labs.communication.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidateTypeMessageForDestinyValidator.class})
public @interface ValidateTypeMessageForDestiny {

    String message() default "{TypeMessageForDestiny.divergent}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String valueFieldType();

    String valueFieldDestiny();

}
