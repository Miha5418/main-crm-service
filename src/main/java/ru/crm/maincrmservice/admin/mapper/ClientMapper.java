package ru.crm.maincrmservice.admin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.rest.admin.openapi.model.ClientInfo;

import java.time.Instant;

@Mapper(imports = {
        Instant.class
})
public interface ClientMapper {

    /**
     * Создание сущности из ДТО
     *
     * @param src ДТО
     * @return Сущность
     */
    @Mapping(target = "createDate",  expression = "java(Instant.now())")
    Client createClientEntity(ClientInfo src);
}
