package ru.crm.maincrmservice.entity.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

/**
 * Клиенты
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "client", schema = "client")
public class Client {

    @Id
    @SequenceGenerator(name = "client_clientid_generator", sequenceName = "client_seq", schema = "client", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_clientid_generator")
    private Long id;

    /**
     * Номер абонемента
     */
    private Long memberShipNumber;

    /**
     * Имя клиента
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Фамилия Клиента
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Отчечтво Клиента
     */
    private String middleName;

    /**
     * Номер телефона
     */
    @Column(length = 12)
    private String phone;

    /**
     * Электронная почта
     */
    @Column(length = 100)
    private String mail;

    /**
     * Дата начала действия абонемента
     */
    private Instant memberShipDateStart;

    /**
     * Дата окончания действия абонемента
     */
    private Instant memberShipDateEnd;

    /**
     * Дата создания клиента
     */
    private Instant createDate;

    /**
     * Идентификатор клиента в телеграмме
     */
    @Column(length = 100)
    private String telegramId;

    /**
     * Рабочий абонемент или нет
     */
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client that = (Client) o;
        return memberShipNumber != null && Objects.equals(memberShipNumber, that.memberShipNumber);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
