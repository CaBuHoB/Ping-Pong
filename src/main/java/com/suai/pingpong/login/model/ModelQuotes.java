package com.suai.pingpong.login.model;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
public class ModelQuotes {
    @Getter
    private static final ModelQuotes instance = new ModelQuotes();
    private final List<Quote> quoteList;

    private ModelQuotes() {
        quoteList = new ArrayList<>();
        loadQuotes();
    }

    public Quote getRandomQuote() {
        int index = ThreadLocalRandom.current().nextInt(0, quoteList.size());
        return quoteList.get(index);
    }

    private void loadQuotes() {
        try (InputStream is = getClass().getResourceAsStream("/static/Quotes");
             InputStreamReader isr = new InputStreamReader(is);
             Scanner scanner = new Scanner(isr)) {
            while (scanner.hasNext()) {
                Quote quote = new Quote(scanner.nextLine(), scanner.nextLine());
                quoteList.add(quote);
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
    }
}
