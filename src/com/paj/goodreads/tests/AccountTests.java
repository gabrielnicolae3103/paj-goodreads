package com.paj.goodreads.tests;

import com.paj.goodreads.Exceptions.UserAlreadyExistsException;
import com.paj.goodreads.Model.Constants;
import com.paj.goodreads.Model.ReaderAccount;
import com.paj.goodreads.Model.WriterAccount;
import com.paj.goodreads.domain.GoodReads;
import com.paj.goodreads.domain.GoodReadsRaport;
import com.paj.goodreads.domain.GoodReadsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {
    @Test
    public void accountCreation() throws UserAlreadyExistsException {
        GoodReads goodReads = new GoodReads();
        GoodReadsService goodReadsService = new GoodReadsService();
        GoodReadsRaport goodReadsRaport = new GoodReadsRaport(goodReads);

        ReaderAccount reader = new ReaderAccount("Reader", "1");
        WriterAccount sanderson = new WriterAccount("Brandon", "Sanderson");
        goodReadsService.addUser(goodReads, reader);
        goodReadsService.addUser(goodReads, sanderson);

        assertEquals(reader.getType(), Constants.AccountType.READER);
        assertEquals(sanderson.getType(), Constants.AccountType.WRITER);
        assertEquals(goodReadsRaport.getNumberOfUsers(), 2);
        assertEquals(goodReadsRaport.getNumberOfRegularUsers(), 1);
        assertEquals(goodReadsRaport.getNumberOfAuthors(), 1);
    }
}
