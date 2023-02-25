package ru.crm.maincrmservice.helpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.crm.maincrmservice.entity.client.Client;
import ru.crm.maincrmservice.exception.NotFoundException;
import ru.crm.maincrmservice.exception.NotUniqueValueException;
import ru.crm.maincrmservice.repository.ClientRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientHelper {

    private final ClientRepository clientRepository;

    /**
     * Возвращает сущность клиента найденну по идентифитикатору абонемента
     *
     * @param memberShipNumber Номер аббонимента
     */
    public Client findClientByMemberShipNumber(Long memberShipNumber) {
        return clientRepository.findByMemberShipNumber(memberShipNumber)
                .orElseThrow(NotFoundException::new);

    }

    /**
     * Проверка занял ли номер аббонимента
     *
     * @param memberShipNumber Номер аббонимента
     * @return Налие аббонимента
     */
    public boolean checkMemberShipNumber(Long memberShipNumber) {
        return clientRepository.existsByMemberShipNumber(memberShipNumber);
    }

    /**
     * Найти клиента по telegramId
     *
     * @param telegramId входной прамметр
     * @return сущность клиента из БД
     */
    public Client findClientByTelegramId(String telegramId) {
        return clientRepository.findByTelegramId(telegramId)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Проверить идентификатор телеграма на уникальность
     *
     * @param telegramId проверяемый идентификатор
     */
    public void checkForUniqueTelegramId(String telegramId) {

        if (clientRepository.existsByTelegramId(telegramId)) {
            throw new NotUniqueValueException();
        }
    }

}
