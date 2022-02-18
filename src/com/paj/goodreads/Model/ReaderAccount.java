package com.paj.goodreads.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReaderAccount extends AbstractAccount {

    public ReaderAccount(String firstName, String lastname) {
        super(firstName, lastname, LocalDateTime.now());
        this.setType(Constants.AccountType.READER);
    }
}
