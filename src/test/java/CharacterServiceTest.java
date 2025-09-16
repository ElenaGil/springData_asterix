import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.reposotory.CharacterRepository;
import org.example.springdata_asterix.service.CharacterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CharacterServiceTest {

    private final CharacterRepository mockCharacterRepo = mock(CharacterRepository.class);

    @Test
    public void testFindCharacterById() {
        //GIVEN
        CharacterRecord character1 = new CharacterRecord("1", "Charachter1", 23, "testProfession");
        CharacterService service = new CharacterService(mockCharacterRepo);
        when(mockCharacterRepo.getCharacterRecordById("1")).thenReturn(character1);

        //WHEN
        CharacterRecord actual = service.getCharacterById("1");

        //THEN
        Assertions.assertEquals(character1, actual);
        verify(mockCharacterRepo).getCharacterRecordById("1");
    }

}
