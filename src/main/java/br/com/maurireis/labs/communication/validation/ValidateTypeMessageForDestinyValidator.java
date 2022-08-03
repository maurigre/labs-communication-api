package br.com.maurireis.labs.communication.validation;

import br.com.maurireis.labs.communication.dataprovider.model.MessageType;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public class ValidateTypeMessageForDestinyValidator
        implements ConstraintValidator<ValidateTypeMessageForDestiny, Object> {

    private String valueFieldType;
    private String valueFieldDestiny;

    @Override
    public void initialize(ValidateTypeMessageForDestiny constraint) {
        this.valueFieldDestiny = constraint.valueFieldDestiny();
        this.valueFieldType = constraint.valueFieldType();
    }

    @Override
    public boolean isValid(Object objetoValidation, ConstraintValidatorContext context) {
        boolean valided = true;
        try {
            String type = (String) BeanUtils.getPropertyDescriptor(objetoValidation.getClass(), valueFieldType)
                    .getReadMethod().invoke(objetoValidation);

            String destiny = (String) BeanUtils.getPropertyDescriptor(objetoValidation.getClass(), valueFieldDestiny)
                    .getReadMethod().invoke(objetoValidation);

            if(type.equals(MessageType.EMAIL.name())) {
                valided =  isValidEmail(destiny);
            } else {
                valided = isValidNumberPhone(destiny);
            }

            return valided;

        } catch (Exception e) {
            throw new ValidationException(e);
        }
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
}
