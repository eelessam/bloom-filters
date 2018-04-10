package com.eelessam.bloomfilters;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws URISyntaxException, IOException {

        Path path = Paths.get(BloomFilter.class.getClassLoader().getResource("words.txt").toURI());
        List<String> loadMe;

        try (Stream<String> lines = Files.lines(path)) {
            loadMe = lines.collect(Collectors.toList());
        }

        BloomFilter bloomFilter = new BloomFilter(loadMe);

        for (int i = 0; i < 10; i++) {
            String word = RandomStringUtils.random(5, true, false);
            LOGGER.log(Level.INFO, String.format("%s: %s", word, bloomFilter.inDict(word)));
        }
    }
}
