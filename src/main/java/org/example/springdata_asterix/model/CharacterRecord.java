package org.example.springdata_asterix.model;

import lombok.With;

@With
public record CharacterRecord(String id, String name, int age, String profession) {
}
