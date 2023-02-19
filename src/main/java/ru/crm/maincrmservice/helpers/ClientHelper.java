package ru.crm.maincrmservice.helpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.crm.maincrmservice.exception.NotFoundException;
import ru.crm.maincrmservice.repository.ClientRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientHelper {

    private final ClientRepository clientRepository;

    /**
     * Проверка на наличие клинета по аббонименту
     *
     * @param memberShipNumber Номер аббонимента
     */
    public void checkClientByMemberShipNumber(Long memberShipNumber) {
        log.debug("Проверяем номер аббонимента {}", memberShipNumber);
        clientRepository.findById(memberShipNumber)
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

}
