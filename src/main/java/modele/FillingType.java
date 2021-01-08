package modele;

/*
    Enum : DistributionType
    ------------------------------------------
    Constantes liées à l'algorithme de remplissage
    du vaisseau suivant différentres stratégies.

    Possibilités : Naif, Packet, Life_focus
*/
public enum FillingType {
    NAIVE,
    PACKET,
    LIFE_FOCUS,
    MORE_SPACE;

    /*
        Méthode : fromText(value)
        ------------------------------------------
        Permet de convertir une référence en texte vers
        une constante que le simulateur va pouvoir interpréter...

        value(String): le texte à parser

        returns: constante correspondant au texte
    */
    public static FillingType fromText(String value) {
        switch(value) {
            case "Je réparti le matériel":
            case "chair à canon":
                return FillingType.NAIVE;
            case "Je bourre":
                return FillingType.PACKET;
            case "priorité vie humaine":
                return FillingType.LIFE_FOCUS;
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
            case NAIVE:
                return "Je réparti le matériel";
            case PACKET:
                return "Je bourre";
        }
        return null;
    }
}