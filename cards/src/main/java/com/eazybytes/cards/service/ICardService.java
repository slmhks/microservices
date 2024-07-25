package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardDto;

public interface ICardService {

    /*public void createCard(CardDto cardDto);*/
    public void createCard(String mobileNumber);

    public CardDto fetchCard(String cardNumber);

    public boolean updateCard(CardDto cardDto);

    public boolean deleteCard(String cardNumber);

    public boolean deleteAllCardsForMobileNumber(String mobileNumber);
}
