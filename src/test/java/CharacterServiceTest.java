import org.example.springdata_asterix.model.CharacterRecord;
import org.example.springdata_asterix.reposotory.CharacterRepository;
import org.example.springdata_asterix.service.CharacterService;
import org.example.springdata_asterix.service.IdService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CharacterServiceTest {

    private final CharacterRepository mockCharacterRepo = mock(CharacterRepository.class);
    private final IdService mockIdService = mock(IdService.class);

    @Test
    public void testFindCharacterById() {
        //GIVEN
        CharacterRecord character1 = new CharacterRecord("1", "Character1", 23, "testProfession");
        CharacterService service = new CharacterService(mockCharacterRepo, mockIdService);
        when(mockCharacterRepo.getCharacterRecordById("1")).thenReturn(character1);

        //WHEN
        CharacterRecord actual = service.findCharacterById("1");

        //THEN
        Assertions.assertEquals(character1, actual);
        verify(mockCharacterRepo).getCharacterRecordById("1");
    }

}
