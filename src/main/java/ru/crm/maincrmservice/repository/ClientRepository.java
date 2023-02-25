package ru.crm.maincrmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crm.maincrmservice.entity.client.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByTelegramId(String telegramId);

    boolean existsByMemberShipNumber(Long id);

    Optional<Client> findByMemberShipNumber(Long id);

    boolean existsByTelegramId(String telegramId);

}
