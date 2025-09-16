package org.example.springdata_asterix.service;

import org.example.springdata_asterix.dto.CharacterRecordDto;
import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.reposotory.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private IdService idService;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public CharacterRecord getCharacterById(String id) {
        return characterRepository.getCharacterRecordById(id);
    }

    public CharacterRecord save(CharacterRecordDto characterRecordDto) {
        CharacterRecord newCharacter = new CharacterRecord(
                idService.randomId(),
                characterRecordDto.name(),
                characterRecordDto.age(),
                characterRecordDto.profession()
        );
        return characterRepository.save(newCharacter);
    }

    public List<CharacterRecord> findAll() {
        return characterRepository.findAll();
    }

    public void deleteCharacterById(String id) {
        characterRepository.deleteById(id);
    }

    public CharacterRecord updateCharacterById(String id, CharacterRecordDto characterRecordDto) {
        CharacterRecord character = characterRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Character Not Found"));

        CharacterRecord newData = character
                .withName(characterRecordDto.name())
                .withAge(characterRecordDto.age())
                .withProfession(characterRecordDto.profession());
        return characterRepository.save(newData);
    }
}
