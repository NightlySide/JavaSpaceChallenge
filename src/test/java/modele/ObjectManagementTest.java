package modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectManagementTest {

    @Test
    void getObjectsPath() {
        assertEquals(new ObjectManagement(new Phase("phase1")).getObjectsPath(),"res/Objects.json");
    }

    @Test
    void getPhase() {
        Phase phase = new Phase("phase1");
        ObjectManagement objectManagement = new ObjectManagement(phase);
        assertEquals(objectManagement.getPhase(), phase);
    }

    @Test
    void getObjects() {
        Phase phase = new Phase("phase1");
        ObjectManagement objectManagement = new ObjectManagement(phase);
        assertEquals(objectManagement.getObjects(), phase.getObjects());
    }

}