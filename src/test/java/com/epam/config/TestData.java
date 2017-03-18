package com.epam.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Артур.
 */
public class TestData {
    private List<String> englishNumbers = new ArrayList<String>() {{
        add("One");
        add("Two");
        add("Three");
        add("Four");
        add("Five");
        add("Six");
    }};

    private List<String> frenchNumbers = new ArrayList<String>() {{
        add("Un");
        add("Deux");
        add("Trois");
        add("Quatre");
        add("Le Five");
        add("Six");
    }};

    public List<String> getEnglishNumbers() {
        return englishNumbers;
    }

    public List<String> getFrenchNumbers() {
        return frenchNumbers;
    }
}
