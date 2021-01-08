package modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidJSONFileExceptionTest {

    @Test
    public void invalidjsontest()
    {
        assertThrows(InvalidJSONFileException.class, () -> {
            throw new InvalidJSONFileException("error");
        });
    }
}