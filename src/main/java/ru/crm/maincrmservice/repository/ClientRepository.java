package ru.crm.maincrmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crm.maincrmservice.entity.client.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByTelegramId(String telegramId);

    boolean existsByMemberShipNumber(Long id);
}
