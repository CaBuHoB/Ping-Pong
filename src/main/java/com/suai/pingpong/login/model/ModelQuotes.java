package com.suai.pingpong.login.model;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Log4j2
public class ModelQuotes {
    @Getter
    private static final ModelQuotes instance = new ModelQuotes();
    private final List<Quote> quoteList;

    private ModelQuotes() {
        quoteList = new ArrayList<>();
        loadQuotes();
    }

    public Quote getRandomQuote(){
        Random random;
        int index = 0;
        try {
            random = SecureRandom.getInstanceStrong();
            index = random.nextInt(quoteList.size());
        } catch (NoSuchAlgorithmException e) {
            log.info(e.getMessage());
        }
        return quoteList.get(index);
    }

    private void loadQuotes(){
        Resource resource = new ClassPathResource("/static/Quotes");
        try (FileReader fileReader = new FileReader(resource.getFile());
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNext()) {
                Quote quote = new Quote(scanner.nextLine(), scanner.nextLine());
                quoteList.add(quote);
            }
        } catch (IOException e) {
            log.info(e.toString());
        }
    }
}
