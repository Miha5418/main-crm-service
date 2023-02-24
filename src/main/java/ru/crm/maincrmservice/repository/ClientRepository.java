package ru.crm.maincrmservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.crm.maincrmservice.entity.client.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByTelegramId(String telegramId);

    boolean existsByMemberShipNumber(Long id);

    Optional<Client> findByMemberShipNumber(Long id);

    @Query(value = "select count(c)>0 from Client c where telegramId=?1")
    boolean checkIfTelegramIdIsFree(String telegramId);

}
