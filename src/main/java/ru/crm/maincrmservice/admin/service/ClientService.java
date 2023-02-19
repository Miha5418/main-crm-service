package ru.crm.maincrmservice.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crm.maincrmservice.admin.mapper.ClientMapper;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.maincrmservice.exception.ExceptionBusyMemberShipNumber;
import ru.crm.maincrmservice.helpers.ClientHelper;
import ru.crm.maincrmservice.repository.ClientRepository;
import ru.crm.rest.admin.openapi.model.ClientInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientHelper clientHelper;
    private final ClientMapper clientMapper;

    /**
     * Добавление нового клиента
     *
     * @param clientInfo ДТО с информацией о клиента
     * @return Клиент из БД
     */
    @Transactional
    public Client addNewClient(ClientInfo clientInfo) {
        if (clientHelper.checkMemberShipNumber(clientInfo.getMemberShipNumber())) {
            throw new ExceptionBusyMemberShipNumber();
        }

        val clientEntity = clientMapper.createClientEntity(clientInfo);

        return clientRepository.save(clientEntity);
    }
}