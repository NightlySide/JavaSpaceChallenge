package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Classe : Simulation
    -------------------
    Cette classe permet de prendre en charge les éléments nécessare à la conception
    et au lancement d'une simulation de programme spatial en envoyant des fusées
    sur mars avec un premier chargement et une colonie.

    Remarque: Cette classe ne contenant aucun attribut on aurait très bien pu créer une classe
    statique avec des méthodes statiques
 */
public class Simulation {

    /*
        Méthode : loadU1(ArrayList<Item>)
        ---------------------------------
        Permet de charger les objets de la liste dans des fusées de type U1.
        Des qu'une fusée est pleine on ajoute une nouvelle fusée du type au programme.

        itemsList (ArrayList<Item>): la liste des objets à emmener

        returns: la liste des fusées de type U1 à lancer
     */
    public ArrayList<U1> loadU1(ArrayList<Object> objList) {
        // on instancie la liste des fusées que l'on va renvoyer
        ArrayList<U1> rocketsList = new ArrayList<>();
        // on y ajoute la première fusée que l'on va remplir
        rocketsList.add(new U1());

        // on récupère la référence à la dernière fusée ajoutée à la liste
        U1 currentRocket = rocketsList.get(rocketsList.size() - 1);
        // pour chaque objet à emmener
        for (Object obj : objList) {
            // si la fusée actuelle ne peut plus emmener cet objet
            if (!currentRocket.canCarry(obj)) {
                // on ajoute une fusée au programme et on met à jour la référence
                // de la fusée actuelle
                rocketsList.add(new U1());
                currentRocket = rocketsList.get(rocketsList.size() - 1);
            }

            // on ajoute l'objet à la cargaison de la fusée
            currentRocket.carry(obj);
        }

        // on retourne la liste des fusées que l'on va envoyer
        return rocketsList;
    }

    /*
        Méthode : loadU2(ArrayList<Item>)
        ---------------------------------
        Permet de charger les objets de la liste dans des fusées de type U2.
        Des qu'une fusée est pleine on ajoute une nouvelle fusée du type au programme.

        itemsList (ArrayList<Item>): la liste des objets à emmener

        returns: la liste des fusées de type U2 à lancer
     */
    public ArrayList<U2> loadU2(ArrayList<Object> objList) {
        // on instancie la liste des fusées que l'on va renvoyer
        ArrayList<U2> rocketsList = new ArrayList<>();
        // on y ajoute la première fusée que l'on va remplir
        rocketsList.add(new U2());

        // on récupère la référence à la dernière fusée ajoutée à la liste
        U2 currentRocket = rocketsList.get(rocketsList.size() - 1);
        // pour chaque objet à emmener
        for (Object obj : objList) {
            // si la fusée actuelle ne peut plus emmener cet objet
            if (!currentRocket.canCarry(obj)) {
                // on ajoute une fusée au programme et on met à jour la référence
                // de la fusée actuelle
                rocketsList.add(new U2());
                currentRocket = rocketsList.get(rocketsList.size() - 1);
            }

            // on ajoute l'objet à la cargaison de la fusée
            currentRocket.carry(obj);
        }

        // on retourne la liste des fusées que l'on va envoyer
        return rocketsList;
    }

    /*
        Méthode : runSimulation(ArrayList<Rocket>)
        ------------------------------------------
        Permet de "lancer la simulation" c'est à dire de lancer les fusées avec
        le matériel. Si l'une d'entre elle se crashe au décollage ou bien à l'arrivée
        on relance une nouvelle fusée avec le meme chargement tout en ajoutant son cout
        au budget du programme. Retourne le cout total du programme spatial.

        rocketsList (ArrayList<Rocket>): la liste des fusées à lancer

        returns: le cout de la mission
     */
    public int runSimulation(ArrayList<? extends Rocket> rocketsList, DistributionType crashDistrib) {
        int budget = 0;
        // pour chacune des fusées à lancer
        for (Rocket rocket : rocketsList) {
            do {
                // on la lance puis on vérifie qu'elle ne se soit pas crashée
                budget += rocket.getPrice();
            } while(!rocket.launch(crashDistrib) || !rocket.land(crashDistrib));
        }

        // on retourne le cout de la mission
        return budget;
    }
}
