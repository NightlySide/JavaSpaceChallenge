package modele;

/*
    Class: InvalidJSONFileException
    -------------------------------
    Classe permettant de créer une exception
    personalisée.
 */
public class InvalidJSONFileException extends Exception{
    public InvalidJSONFileException(String message)
    {
        super(message);
    }
}
