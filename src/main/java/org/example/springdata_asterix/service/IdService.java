package org.example.springdata_asterix.service;

import java.util.UUID;

public class IdService {

    public String randomId() {
        return UUID.randomUUID().toString();
    }
}
