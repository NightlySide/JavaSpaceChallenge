package modele;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidJSONFileExceptionTest {

    @Test
    public void InvalidJSONFileException()
    {
        assertThrows(InvalidJSONFileException.class, () -> {
            throw new InvalidJSONFileException("error");
        });
    }
}