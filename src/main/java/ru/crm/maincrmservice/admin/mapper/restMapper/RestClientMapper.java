package ru.crm.maincrmservice.admin.mapper.restMapper;

import org.mapstruct.Mapper;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.rest.admin.openapi.model.ClientInfo;

@Mapper
public interface RestClientMapper {

    /**
     * Создание ДТО из сущности
     *
     * @param src Сущность
     * @return ДТО
     */
    ClientInfo createClientInfoDto(Client src);
}
