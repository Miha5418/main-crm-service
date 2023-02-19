package ru.crm.maincrmservice.exception;

import org.springframework.http.HttpStatus;

public class ExceptionBusyMemberShipNumber extends AppException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_ACCEPTABLE;
    }
}
