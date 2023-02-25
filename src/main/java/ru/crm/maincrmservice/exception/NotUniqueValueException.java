package ru.crm.maincrmservice.exception;

import org.springframework.http.HttpStatus;

public class NotUniqueValueException extends AppException{

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
