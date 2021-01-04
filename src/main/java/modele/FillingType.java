package modele;

public enum FillingType {
    NAIVE,
    PACKET,
    MORE_SPACE;

    public static FillingType fromText(String value) {
        switch(value) {
            case "Je réparti le matériel":
                return FillingType.NAIVE;
            case "Je bourre":
                return FillingType.PACKET;
        }
        return null;
    }
}