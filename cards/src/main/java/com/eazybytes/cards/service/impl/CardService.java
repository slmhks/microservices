package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.constants.CardConstants;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardMapper;
import com.eazybytes.cards.repository.CardRepository;
import com.eazybytes.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardService implements ICardService {

    private CardRepository repository;

    @Override
    /*public void createCard(CardDto cardDto) {
        Card card = CardMapper.mapToEntity(cardDto);
        Optional<Card> optionalCard = this.repository.findByMobileNumber(cardDto.getMobileNumber());
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Mobile number already exists: " + cardDto.getMobileNumber());
        }
        this.repository.save(card);
    }*/
    public void createCard(String mobileNumber) {
        //Card card = CardMapper.mapToEntity(cardDto);
        Optional<Card> optionalCard = this.repository.findByMobileNumber(mobileNumber);
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Mobile number already exists: " + mobileNumber);
        }
        this.repository.save(this.createCardEntity(mobileNumber));
    }

    private Card createCardEntity(String mobileNumber) {
        Card card = new Card();

        long randomCardNumber = 1000000000000000L + new Random().nextInt(900000000);
        long randomAmountUsed = 1000L + new Random().nextInt(900);
        BigDecimal amountUsed = BigDecimal.valueOf(randomAmountUsed);
        BigDecimal totalLimit = BigDecimal.valueOf(3000);

        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setCardType(randomCardNumber % 2 ==0 ? CardConstants.CARD_TYPE_CREDIT : CardConstants.CARD_TYPE_DEBIT);
        card.setMobileNumber(mobileNumber);
        card.setTotalLimit(totalLimit);
        card.setAmountUsed(amountUsed);
        card.setAvailableAmount(totalLimit.subtract(amountUsed));
        return card;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = this.repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card","mobileNumber",mobileNumber)
        );
        return CardMapper.mapToDto(card);
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = this.repository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber())
        );
        CardMapper.mapToEntity(cardDto, card);
        this.repository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = this.repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        this.repository.delete(card);
        return true;
    }

    @Override
    public boolean deleteAllCardsForMobileNumber(String mobileNumber) {
        Long recordsFound = this.repository.countRecords(mobileNumber);
        if (recordsFound == 0) {
            throw new ResourceNotFoundException("Card", "mobileNumber", mobileNumber);
        } else if (recordsFound == 1) {
            throw new RuntimeException("There is only card found for the mobile number " + mobileNumber
                    + ". Use the \"/api/delete\" API.");
        }
//        this.repository.deleteByMobileNumberQuery(mobileNumber);
        this.repository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}
