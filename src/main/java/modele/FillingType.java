package modele;

public enum FillingType {
    NAIVE,
    PACKET,
    LIFE_FOCUS,
    MORE_SPACE;

    public static FillingType fromText(String value) {
        /* permet de convertir un texte de bouton en commande interpretable pour le simulateur */
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