package ru.crm.maincrmservice.telegram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.crm.maincrmservice.helpers.ClientHelper;
import ru.crm.maincrmservice.repository.ClientRepository;
import ru.crm.maincrmservice.telegram.mapper.ClientMapperTelegram;
import ru.crm.rest.user.openapi.model.ResponseIsActiveClient;
import ru.crm.rest.user.openapi.model.TelegramRegistration;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramSevice {
    private final ClientMapperTelegram clientMapperAdmin;
    private final ClientRepository clientRepository;
    private final ClientHelper clientHelper;

    public ResponseEntity<Void> addTelegramIdByClientId(TelegramRegistration telegramRegistration) {
        String telegramId = telegramRegistration.getTelegramId();
        clientHelper.checkForUniqueTelegramId(telegramId);
        var client = clientHelper.findClientByMemberShipNumber(Long.parseLong(telegramRegistration.getClientId()));
        client.setTelegramId(telegramId);
        clientRepository.save(client);
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<ResponseIsActiveClient> isActiveClient(String telegramId) {
        var client = clientHelper.findClientByTelegramId(telegramId);
        var responseIsActiveClient = clientMapperAdmin.createClientByResponseIsActiveClientDto(client);
        return ResponseEntity.ok(responseIsActiveClient);
    }
}
