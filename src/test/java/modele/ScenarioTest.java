package modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {

    @Test
    void getPercentage_u1() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        assertEquals(scenario.getPercentage_u1(),0.5);
    }

    @Test
    void getPercentage_fill() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        assertEquals(scenario.getPercentage_fill(),0.9);
    }

    @Test
    void getAlgo_fill() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        assertEquals(scenario.getAlgo_fill(),FillingType.NAIVE);
    }

    @Test
    void getCrashDistoType() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        assertEquals(scenario.getCrashDistoType(),DistributionType.LINEAR);
    }

    @Test
    void getRocketP1() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        assertEquals(scenario.getRocketP1(),"U1");
    }

    @Test
    void getRocketP2() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        assertEquals(scenario.getRocketP2(),"U2");
    }

    @Test
    void setPercentage_u1() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        scenario.setPercentage_u1(0.6);
        assertEquals(scenario.getPercentage_u1(), 0.6);
    }

    @Test
    void setPercentage_fill() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        scenario.setPercentage_fill(1);
        assertEquals(scenario.getPercentage_fill(), 1);
    }

    @Test
    void setAlgo_fill() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        scenario.setAlgo_fill(FillingType.LIFE_FOCUS);
        assertEquals(scenario.getAlgo_fill(), FillingType.LIFE_FOCUS);
    }

    @Test
    void setCrashDistoType() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        scenario.setCrashDistoType(DistributionType.SIGMOID);
        assertEquals(scenario.getCrashDistoType(), DistributionType.SIGMOID);
    }

    @Test
    void setRocketP1() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        scenario.setRocketP1("R1");
        assertEquals(scenario.getRocketP1(), "R1");
    }

    @Test
    void setRocketP2() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        scenario.setRocketP2("R2");
        assertEquals(scenario.getRocketP2(), "R2");
    }

    @Test
    void testToString() {
        Scenario scenario = new Scenario(0.5,0.9,FillingType.NAIVE,DistributionType.LINEAR,"U1", "U2");
        String res = "Scenario{" +
                "percentage_u1=" + scenario.getPercentage_u1() +
                ", percentage_fill=" + scenario.getPercentage_fill() +
                ", algo_fill='" + scenario.getAlgo_fill() + '\'' +
                ", crashDistroType=" + scenario.getCrashDistoType() +
                '}';
        assertEquals(scenario.toString(), res);
    }
}