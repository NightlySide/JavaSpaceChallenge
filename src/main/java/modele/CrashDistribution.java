package modele;

public class CrashDistribution {
    /*
    la classe CrashDistribution permet de définir un type de distribution probabiliste lors du jet de dés au décollage et à l'amarsissage.
    Elle peut etre linéaire, exponentielle ou Sigmoide.
    */

    static double weightRatio(Rocket rocket) {
        return (double) rocket.getWeight() / (double) rocket.getMaxWeight();
    }
    // la partie de la probabilité affecté par le poids des objets emportés
    static double LinearDistributionChance(Rocket rocket, double riskAtFullCapacity) {
        // distribution linéaire
        // f(x) = coeff * x
        // https://www.wolframalpha.com/input/?i=y%3Dx+*+5+between+0+and+1
        return riskAtFullCapacity / 100.0 * weightRatio(rocket);
    }

    static double ExponentialDistributionChance(Rocket rocket, double riskAtFullCapacity, double lambda) {
        // distribution exponentielle
        // f(x) = coeff * (1-exp(-lambda * x))
        // https://www.wolframalpha.com/input/?i=y%3D1-exp%28-5*x%29+between+0+and+1
        return riskAtFullCapacity / 100.0 * (1 - Math.exp(-lambda * weightRatio(rocket)));
    }

    static double SigmoidDistributionChance(Rocket rocket, double riskAtFullCapacity, double beta) {
        // distribution Sigmoide
        // f(x) = coeff * (1 / (1 + (x/(1-x))^-beta)
        // https://www.wolframalpha.com/input/?i=y%3D1+%2F+%281+%2B+%28x+%2F+%281-x%29%29%5E-3%29+between+0+and+1
        double proba = 1 / (1 + Math.pow(riskAtFullCapacity / (1-riskAtFullCapacity), -beta));
        return riskAtFullCapacity / 100.0 * proba;
    }
}
