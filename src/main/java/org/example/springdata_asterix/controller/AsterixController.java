package org.example.springdata_asterix.controller;

import org.example.springdata_asterix.dto.CharacterRecordDto;
import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final CharacterService characterService;

    public AsterixController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public List<CharacterRecord> getCharacters() {
        return characterService.findAll();
    }

    @GetMapping("/characters/{id}")
    public CharacterRecord getCharacterById(@PathVariable String id) {
        return characterService.getCharacterById(id);
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacterById(@PathVariable String id) {
        characterService.deleteCharacterById(id);
    }

    @PutMapping("/characters/{id}")
    public CharacterRecord updateCharacterById(
            @PathVariable String id,
            @RequestBody CharacterRecordDto characterRecordDto)
    {
        return characterService.updateCharacterById(id, characterRecordDto);
    }

    @PostMapping
    public CharacterRecord createCharacter(@RequestBody CharacterRecordDto characterRecordDto) {
        return characterService.save(characterRecordDto);
    }
}
