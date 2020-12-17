package modele;

enum DistributionType {
    LINEAR,
    EXPONENTIAL,
    UNDEFINED
}

public class CrashDistribution {

    static double weightRatio(Rocket rocket) {
        return (double) rocket.getWeight() / (double) rocket.getMaxWeight();
    }

    static double LinearDistributionChance(Rocket rocket, double riskAtFullCapacity) {
        // f(x) = coeff * x
        // https://www.wolframalpha.com/input/?i=y%3Dx+*+5+between+0+and+1
        return riskAtFullCapacity / 100.0 * weightRatio(rocket);
    }

    static double ExponentialDistributionChance(Rocket rocket, double riskAtFullCapacity, double lambda) {
        // f(x) = coeff * 1-exp(-lambda * x)
        // https://www.wolframalpha.com/input/?i=y%3D1-exp%28-5*x%29+between+0+and+1    
        return riskAtFullCapacity / 100.0 * 1 - Math.exp(-lambda * weightRatio(rocket));
    }
}
