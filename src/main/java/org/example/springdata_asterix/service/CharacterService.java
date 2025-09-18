package org.example.springdata_asterix.service;

import org.example.springdata_asterix.dto.CharacterRecordDto;
import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.reposotory.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final IdService idService;

    public CharacterService(CharacterRepository characterRepository, IdService idService) {
        this.characterRepository = characterRepository;
        this.idService = idService;
    }

    public CharacterRecord findCharacterById(String id) {
        return characterRepository.getCharacterRecordById(id);
    }

    public CharacterRecord addCharacter(CharacterRecordDto characterRecordDto) {
        CharacterRecord newCharacter = new CharacterRecord(
                idService.randomId(),
                characterRecordDto.name(),
                characterRecordDto.age(),
                characterRecordDto.profession()
        );
        return characterRepository.save(newCharacter);
    }

    public List<CharacterRecord> findAllCharacters () {
        return characterRepository.findAll();
    }

    public void deleteCharacterById(String id) {
        characterRepository.removeById(id);
    }

    public CharacterRecord updateCharacterById(String id, CharacterRecordDto characterRecordDto) {
        try {
            Optional<CharacterRecord> character = characterRepository.findById(id);
            if (character.isPresent()) {
                CharacterRecord newData = (character.get())
                        .withName(characterRecordDto.name())
                        .withAge(characterRecordDto.age())
                        .withProfession(characterRecordDto.profession());
                return characterRepository.save(newData);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Character Not Found");
        }
        return  null;
    }
}
