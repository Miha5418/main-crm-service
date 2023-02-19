package ru.crm.maincrmservice.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import ru.crm.maincrmservice.admin.mapper.restMapper.RestClientMapper;
import ru.crm.maincrmservice.admin.service.ClientService;
import ru.crm.rest.admin.openapi.model.ClientInfo;
import ru.crm.rest.admin.openapi.model.ClientInfoPageable;
import ru.crm.rest.admin.openapi.model.PageParams;
import ru.crm.rest.admin.openapi.support.ClientApi;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;
    private final RestClientMapper restClientMapper;


    @Override
    public ResponseEntity<ClientInfo> addNewClient(@Valid ClientInfo clientInfo) {
        log.debug("Вызван метод addNewClient для аббонемента {}", clientInfo.getMemberShipNumber());
        val client = clientService.addNewClient(clientInfo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restClientMapper.createClientInfoDto(client));
    }

    @Override
    public ResponseEntity<ClientInfo> getClientInfoById(Integer clientId) {
        return null;
    }

    @Override
    public ResponseEntity<ClientInfoPageable> getListClientInfo(@Valid PageParams pageParams) {
        return null;
    }
}
