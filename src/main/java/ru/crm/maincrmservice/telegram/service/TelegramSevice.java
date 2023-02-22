package ru.crm.maincrmservice.telegram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.crm.maincrmservice.mapper.ClientMapper;
import ru.crm.maincrmservice.repository.ClientRepository;
import ru.crm.rest.user.openapi.model.ApiResponseError;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramSevice {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public ResponseEntity addedClientIdByTelegramId(String telegramId, String clientId) {
        var clientOptional = clientRepository.findByMemberShipNumber(Long.parseLong(clientId));

        if (clientOptional.isEmpty()) {
            String msgError = "clientOptional search error by clientID - " + clientId;
            return createResponseError(msgError, null, HttpStatus.BAD_REQUEST);
        }

        var clientEntity = clientOptional.get();
        clientEntity.setTelegramId(telegramId);
        clientRepository.save(clientEntity);
        return ResponseEntity.ok(null);
    }

    public ResponseEntity isActiveClient(String telegramId) {
        var client = clientRepository.findByTelegramId(telegramId);
        var responseIsActiveClient = clientMapper.createClientByResponseIsActiveClientDto(client);
        ResponseEntity response = ResponseEntity.ok(responseIsActiveClient);

        if (responseIsActiveClient == null) {
            String msgError = "error generating response for client with telegramId - " + telegramId;
            response = createResponseError(msgError, null, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    private ResponseEntity createResponseError(String msgError, String detail, HttpStatus httpStatus) {
        log.error(msgError);
        ApiResponseError errorResponse = new ApiResponseError();
        errorResponse.setTitle(msgError);
        long code = httpStatus.value();
        errorResponse.setStatus(code);
        errorResponse.setDetail(detail);

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
