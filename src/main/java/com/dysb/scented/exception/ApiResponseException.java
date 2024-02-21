package com.dysb.scented.exception;

import com.dysb.scented.enumerate.ErrorMessage;
import lombok.Data;
import org.springframework.context.MessageSource;

import java.util.Locale;

@Data
public class ApiResponseException extends RuntimeException{

    private ErrorMessage em;
    private String code;    // i18n code
    private String[] vars;  // i18n vars

    public ApiResponseException(ErrorMessage em){
        super(em.getMessage());
    }
    public ApiResponseException(ErrorMessage em, String detail){
        super(em.getMessage(), new Throwable(detail));
    }

}
