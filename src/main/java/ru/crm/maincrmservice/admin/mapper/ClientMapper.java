package ru.crm.maincrmservice.admin.mapper;

import lombok.val;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.rest.admin.openapi.model.ClientInfo;
import ru.crm.rest.admin.openapi.model.ClientInfoPageable;
import ru.crm.rest.admin.openapi.model.PageParams;

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
    @Mapping(target = "createDate", expression = "java(Instant.now())")
    Client createClientEntity(ClientInfo src);

    /**
     * Создание ДТО из сущности
     *
     * @param src Сущность
     * @return ДТО
     */
    ClientInfo createClientInfoDto(Client src);

    /**
     * Мапинг в ДТО списка клиентов с пагинацие
     *
     * @param clientPage  Список клиентов
     * @param pageRequest Параметры пагинации
     * @return ДТО с пагинацией
     */
    default ClientInfoPageable createClientInfoPageable(Page<Client> clientPage, PageRequest pageRequest) {
        val clientDtoList = clientPage.stream()
                .map(this::createClientInfoDto)
                .toList();

        PageParams pageParams = new PageParams();
        pageParams.setPageSize(pageRequest.getPageSize());
        pageParams.setPageCurrent(pageRequest.getPageNumber());
        pageParams.setPageTotal(clientPage.getTotalPages());

        ClientInfoPageable clientInfoPageable = new ClientInfoPageable();
        clientInfoPageable.setData(clientDtoList);
        clientInfoPageable.setPageParams(pageParams);

        return clientInfoPageable;
    }
}
