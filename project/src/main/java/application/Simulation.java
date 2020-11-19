package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    public ArrayList<Item> loadItems(String filename) throws IOException {
        ArrayList<Item> itemsList = new ArrayList<>();

        File file = new File(filename);
        if (file.exists()) {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                
            }
        }
        else {
            throw new FileNotFoundException(String.format("Le fichier %s n'a pas été trouvé !", filename));
        }

        return itemsList;
    }
}
