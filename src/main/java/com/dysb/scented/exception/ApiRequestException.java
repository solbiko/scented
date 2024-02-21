package com.dysb.scented.exception;

import com.dysb.scented.enumerate.ErrorMessage;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException(String errMsg){super(errMsg);}
    public ApiRequestException(String errMsg, RuntimeException e){super(errMsg, e);}
}
