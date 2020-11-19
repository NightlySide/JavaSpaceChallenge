package application;

import java.io.File;
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
                String[] data = reader.nextLine().split("=");
                Item i = new Item(data[0], Integer.parseInt(data[1]));
                itemsList.add(i);
            }

            return itemsList;
        }

        throw new FileNotFoundException(String.format("Le fichier %s n'a pas été trouvé !", filename));
    }

    public ArrayList<U1> loadU1(ArrayList<Item> itemsList) {
        ArrayList<U1> rocketsList = new ArrayList<>();
        rocketsList.add(new U1());

        U1 currentRocket = rocketsList.get(rocketsList.size() - 1);

        for (Item item : itemsList) {
            if (!currentRocket.canCarry(item)) {
                rocketsList.add(new U1());
                currentRocket = rocketsList.get(rocketsList.size() - 1);
            }

            currentRocket.carry(item);
        }

        return rocketsList;
    }

    public ArrayList<U2> loadU2(ArrayList<Item> itemsList) {
        ArrayList<U2> rocketsList = new ArrayList<>();
        rocketsList.add(new U2());

        U2 currentRocket = rocketsList.get(rocketsList.size() - 1);

        for (Item item : itemsList) {
            if (!currentRocket.canCarry(item)) {
                rocketsList.add(new U2());
                currentRocket = rocketsList.get(rocketsList.size() - 1);
            }

            currentRocket.carry(item);
        }

        return rocketsList;
    }

    public int runSimulation(ArrayList<? extends Rocket> rocketsList) {
        int budget = 0;

        for (Rocket rocket : rocketsList) {
            do {
                budget += rocket.getPrice();
            } while(!rocket.launch() || !rocket.land());
        }

        return budget;
    }
}
