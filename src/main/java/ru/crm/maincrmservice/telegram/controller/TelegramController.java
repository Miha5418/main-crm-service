package ru.crm.maincrmservice.telegram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.crm.maincrmservice.telegram.service.TelegramSevice;
import ru.crm.rest.user.openapi.api.TelegramApi;
import ru.crm.rest.user.openapi.model.RequestIsActiveClient;
import ru.crm.rest.user.openapi.model.ResponseIsActiveClient;
import ru.crm.rest.user.openapi.model.TelegramRegistration;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TelegramController implements TelegramApi {
    private final TelegramSevice telegramSevice;

    @Override
    public ResponseEntity<Void> registrationClient(@Valid TelegramRegistration telegramRegistration) {
        return telegramSevice.addedClientIdByTelegramId(telegramRegistration.getTelegramId(),
                telegramRegistration.getClientId());
    }

    @Override
    public ResponseEntity<ResponseIsActiveClient> isActiveClient(@Valid RequestIsActiveClient requestIsActiveClient) {
        return telegramSevice.isActiveClient(requestIsActiveClient.getTelegramId());
    }
}
