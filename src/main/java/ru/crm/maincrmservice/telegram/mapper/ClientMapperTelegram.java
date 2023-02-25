package ru.crm.maincrmservice.telegram.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.rest.user.openapi.model.ResponseIsActiveClient;

import java.time.Instant;

@Mapper(imports = {
        Instant.class
})
public interface ClientMapperTelegram {

    /**
     * Маппит сущность клиента в ДТО, для ответа на запрос окончания срока действия аббонемента
     *
     * @param src сущность БД
     * @return дто
     */
    @Mapping(target = "expirationDate", source = "memberShipDateEnd")
    ResponseIsActiveClient createClientByResponseIsActiveClientDto(Client src);
}
