package ru.crm.maincrmservice.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crm.maincrmservice.admin.mapper.ClientMapper;
import ru.crm.maincrmservice.config.AppConfig;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.maincrmservice.exception.ExceptionBusyMemberShipNumber;
import ru.crm.maincrmservice.exception.NotFoundException;
import ru.crm.maincrmservice.helpers.ClientHelper;
import ru.crm.maincrmservice.repository.ClientRepository;
import ru.crm.rest.admin.openapi.model.ClientInfo;
import ru.crm.rest.admin.openapi.model.ClientInfoPageable;
import ru.crm.rest.admin.openapi.model.PageParams;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientHelper clientHelper;
    private final ClientMapper clientMapper;
    private final AppConfig appConfig;

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

    /**
     * Получения клиента по номер абонимента
     *
     * @param memberShipNumber Номер аббонимента
     * @return Клиент
     */
    @Transactional
    public Client getClientByMemberShipNumber(Long memberShipNumber) {
        return clientRepository.findByMemberShipNumber(memberShipNumber)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Получения списка клиентов с пагинацией
     *
     * @param pageParams Параметры пагинации
     * @return Список клиентов
     */
    public ClientInfoPageable getClientList(PageParams pageParams) {
        val pageable = getPageRequest(pageParams);

        val pageClient = clientRepository.findAll(pageable);

        return clientMapper.createClientInfoPageable(pageClient, pageable);
    }

    /**
     * Построение параметров пагинации
     *
     * @param pageParams Пагинация ДТО
     * @return Пагинация БД
     */
    private PageRequest getPageRequest(PageParams pageParams) {

        return PageRequest.of(
                Optional.ofNullable(pageParams.getPageCurrent())
                        .orElse(0),
                Optional.ofNullable(pageParams.getPageSize())
                        .orElse(appConfig.getDefaultPageSize()));
    }
}