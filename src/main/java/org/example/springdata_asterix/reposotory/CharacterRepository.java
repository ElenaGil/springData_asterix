package org.example.springdata_asterix.reposotory;

import org.example.springdata_asterix.model.CharacterRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterRecord, String> {
    CharacterRecord getCharacterByName(String name);

    CharacterRecord getCharacterRecordById(String id);
}
