package modele;

public enum DistributionType {
    LINEAR,
    EXPONENTIAL,
    SIGMOID,
    UNDEFINED;

    public static DistributionType fromText(String value) {
        /* permet de convertir un texte de bouton en commande interpretable pour le simulateur */
        switch(value) {
            case "Linéaire":
                return DistributionType.LINEAR;
            case "Exponentielle":
                return DistributionType.EXPONENTIAL;
            case "Sigmoïde":
                return DistributionType.SIGMOID;
        }
        return null;
    }


    public String toText() {
        switch (this) {
            case LINEAR:
                return "Linéaire";
            case EXPONENTIAL:
                return "Exponentielle";
            case SIGMOID:
                return "Sigmoïde";
        }
        return null;
    }
}