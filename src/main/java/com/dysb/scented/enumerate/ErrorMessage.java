package com.dysb.scented.enumerate;

import org.springframework.context.MessageSource;

import java.util.Locale;

public enum ErrorMessage {

      ERROR_BAD_REQUEST              ("400", "ERROR_BAD_REQUEST")
    , ERROR_UNAUTHORIZED             ("401", "ERROR_UNAUTHORIZED")
    , ERROR_ACCESS_DENIED            ("403", "ERROR_ACCESS_DENIED")
    , ERROR_NOT_FOUND                ("404", "ERROR_NOT_FOUND")
    , ERROR_CONFLICT                 ("409", "ERROR_CONFLICT")
    , ERROR_INVALID_PARAMETER       ("461", "ERROR_INVALID_PARAMETER")
    ;

    private final String code;
    private final String msg;
    ErrorMessage(String code, String msg){this.code=code; this.msg=msg;}

    public String getCode(){return this.code;}
    public String getMessage(){return this.msg;}


    public String getMessageLang(MessageSource messageSource, Locale locale, Exception e){
        return messageSource.getMessage(this.getMessage(), null, locale) + " : " + e.getMessage();
    }


}
