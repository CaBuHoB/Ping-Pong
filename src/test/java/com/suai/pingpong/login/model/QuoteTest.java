package com.suai.pingpong.login.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuoteTest {

    @Test
    public void getQuote() {
        Quote quote = new Quote("test", "test");
        assertEquals("test", quote.getQuote());
    }

    @Test
    public void getPerson() {
        Quote quote = new Quote("test", "test");
        assertEquals("test", quote.getPerson());
    }
}