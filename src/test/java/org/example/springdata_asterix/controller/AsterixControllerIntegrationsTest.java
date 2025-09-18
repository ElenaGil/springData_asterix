package org.example.springdata_asterix.controller;

import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.reposotory.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixControllerIntegrationsTest {
    CharacterRecord characterRecord = new CharacterRecord("1", "testName", 23, "testProfession");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    void getAllCharacters_shouldReturnListOfCharacters_whenCalled() throws Exception {
        //GIVEN
        characterRepository.save(characterRecord);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
        //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").hasJsonPath());
    }


    @Test
    void getCharacterById_shouldReturnCharacter_whenCalledWithId() throws Exception {

        //GIVEN
        characterRepository.save(characterRecord);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/asterix/characters/1"))
        //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("""
                          {
                            "id": "1",
                            "name": "testName",
                            "age": 23,
                            "profession": "testProfession"
                          }
                        """));
    }

    @Test
    void createCharacter_shouldSaveNewCharacterIntoRepo() throws Exception {
        //GIVEN
        //WHEN

        mockMvc.perform(MockMvcRequestBuilders.post("/asterix")
                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                  {
                                    "name": "testNameA",
                                    "age": 33,
                                    "profession": "testProfessionA"
                                  }
                                """))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.content()
                        .json("""
                          {
                            "name": "testNameA",
                            "age": 33,
                            "profession": "testProfessionA"
                          }
                        """));
    }

    @Test
    void updateCharacterById_shouldSaveNewDataOfCharacter() throws Exception {
        //GIVEN
        characterRepository.save(characterRecord);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/asterix/characters/1")
                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                  {
                                    "name": "newName",
                                    "age": 22,
                                    "profession": "newProfession"
                                  }
                                """))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("""
                          {
                            "name": "newName",
                                    "age": 22,
                                    "profession": "newProfession"
                          }
                        """));
    }

    @Test
    void deleteCharacterById_shouldDeleteCharacter() throws Exception {
        //GIVEN
        characterRepository.save(characterRecord);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/asterix/characters/1"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}