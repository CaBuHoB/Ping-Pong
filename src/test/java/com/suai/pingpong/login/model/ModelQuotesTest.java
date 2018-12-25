package com.suai.pingpong.login.model;

import org.junit.Test;

public class ModelQuotesTest {

    @Test
    public void getRandomQuote() {
        for (int i = 0; i < 100; i++)
            new ModelQuotes().getRandomQuote();
    }
}