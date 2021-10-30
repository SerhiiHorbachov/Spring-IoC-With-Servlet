package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class NameProvider {

    private final static String NAME = "Serhii";

    public String getName() {
        return NAME;
    }
}
