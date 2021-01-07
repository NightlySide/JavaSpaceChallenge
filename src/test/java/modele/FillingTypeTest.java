package modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FillingTypeTest {

    @Test
    void fromText() {
        assertEquals(FillingType.fromText("Je réparti le matériel"), FillingType.NAIVE);
        assertEquals(FillingType.fromText("chair à canon"), FillingType.NAIVE);
        assertEquals(FillingType.fromText("Je bourre"), FillingType.PACKET);
        assertEquals(FillingType.fromText("priorité vie humaine"), FillingType.LIFE_FOCUS);
    }

    @Test
    void toText() {
        assertEquals(FillingType.NAIVE.toText(), "Je réparti le matériel");
        assertEquals(FillingType.PACKET.toText(), "Je bourre");
    }

}