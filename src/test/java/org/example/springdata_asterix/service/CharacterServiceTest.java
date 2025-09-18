package org.example.springdata_asterix.service;

import org.example.springdata_asterix.dto.CharacterRecordDto;
import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.reposotory.CharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CharacterServiceTest {

    private final CharacterRepository mockCharacterRepo = mock(CharacterRepository.class);
    private final IdService mockIdService = mock(IdService.class);

    @Test
    public void findCharacterById_shouldReturnCharacterData_whenCalledWithExistingId() {
        //GIVEN
        CharacterRecord characterRecord = new CharacterRecord("1", "Character1", 23, "testProfession");
        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.getCharacterRecordById("1")).thenReturn(characterRecord);

        //WHEN
        CharacterRecord actual = service.findCharacterById("1");

        //THEN
        Assertions.assertEquals(characterRecord, actual);
        verify(mockCharacterRepo).getCharacterRecordById("1");
    }

    @Test
    public void findCharacterById_shouldReturnNull_whenCalledWithNotExistingId() {
        //GIVEN
        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.getCharacterRecordById("XY")).thenReturn(null);

        //WHEN
        CharacterRecord actual = service.findCharacterById("XY");

        //THEN
        Assertions.assertNull(actual);
        verify(mockCharacterRepo).getCharacterRecordById("XY");
    }

    @Test
    public void findAllCharacter_shouldReturnListOfAllCharacters() {
        //GIVEN
        List<CharacterRecord> characters = new ArrayList<>(List.of(
                new CharacterRecord("1", "Character1", 23, "testProfession1"),
                new CharacterRecord("2", "Character2", 24, "testProfession2")));
        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.findAll()).thenReturn(characters);

        //WHEN
        List<CharacterRecord> actual = service.findAllCharacters();

        //THEN
        Assertions.assertEquals(characters, actual);
        verify(mockCharacterRepo).findAll();
    }

    @Test
    public void updateCharacterById_shouldReturnCharacterWithNewData() {
        //GIVEN
        CharacterRecord characterData = new CharacterRecord("1", "testCharacter", 23, "testProfession");
        CharacterRecordDto dto = new CharacterRecordDto("someCharacter", 34, "someProfession");
        CharacterRecord expectedCharacterData = new CharacterRecord("1", "someCharacter", 34, "someProfession");

        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.findById("1")).thenReturn(Optional.of(characterData));
        when(mockCharacterRepo.save(expectedCharacterData)).thenReturn(expectedCharacterData);

        //WHEN
        CharacterRecord actual = service.updateCharacterById("1", dto);

        //THEN
        Assertions.assertEquals(expectedCharacterData, actual);
        verify(mockCharacterRepo).findById("1");
    }

    @Test
    public void deleteCharacterById_shouldRemoveCharacterByGivenId() {
        //GIVEN
        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.existsById("1")).thenReturn(true);

        //WHEN
        service.deleteCharacterById("1");

        //THEN
        verify(mockCharacterRepo).removeById("1");
    }

    @Test
    void addCharacter() {
        //GIVEN
        CharacterRecordDto dto = new CharacterRecordDto("Astrid", 17, "worker");
        CharacterRecord newCharacter = new CharacterRecord("1","Astrid", 17, "worker");
        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);

        when(mockCharacterRepo.save(newCharacter)).thenReturn(newCharacter);
        when(mockIdService.randomId()).thenReturn("1");

        //WHEN
        CharacterRecord actual = service.addCharacter(dto);

        //THEN
        Assertions.assertEquals(newCharacter, actual);
    }

}
