package modele;

/*
    Enum : DistributionType
    ------------------------------------------
    Constantes liées à la distribution de probabilité
    pour une rocket de se crasher..

    Possibilités : Linéaire, Exponentielle, Sigmoïde
*/
public enum DistributionType {
    LINEAR,
    EXPONENTIAL,
    SIGMOID,
    UNDEFINED;

    /*
        Méthode : fromText(value)
        ------------------------------------------
        Permet de convertir une référence en texte vers
        une constante que le simulateur va pouvoir interpréter...

        value(String): le texte à parser

        returns: constante correspondant au texte
    */
    public static DistributionType fromText(String value) {
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


    /*
        Méthode : toText(value)
        ------------------------------------------
        Permet de convertir une constante vers un texte
        qui sera plus agréable à lire pour l'utilisateur

        returns: texte correspondant à la constante
    */
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