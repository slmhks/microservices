package com.eazybytes.cards.repository;

import com.eazybytes.cards.entity.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    public Optional<Card> findByMobileNumber(String mobileNumber);

    public Optional<Card> findByCardNumber(String cardNumber);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Card c WHERE c.mobileNumber = :mobileNumber")
    public void deleteByMobileNumberQuery(@Param("mobileNumber") String mobileNumber);

    @Query(value = "SELECT COUNT(c) from Card c WHERE c.mobileNumber = :mobileNumber")
    public Long countRecords(@Param("mobileNumber") String mobileNumber);

    @Transactional
    public void deleteByMobileNumber(String mobileNumber);
}
