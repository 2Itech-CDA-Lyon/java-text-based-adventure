package com.example.game;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.*;

import com.example.entity.Command;
import com.example.entity.Direction;
import com.example.entity.Item;
import com.example.entity.Room;
import com.example.entity.RoomConnection;
import com.example.entity.effect.AbstractEffect;

/**
 * Represents a game played by the user
 */
public class Game
{
    /**
     * Interface used to read user input
     */
    private Scanner scanner;
    /**
     * Whether the game is running or not
     */
    private boolean isRunning;
    /**
     * List of all existing directions
     */
    private Direction[] directions;
    /**
     * The room the player is currently in
     */
    private Room currentRoom;
    /**
     * List of all existing commands
     */
    private Command[] commands;

    /**
     * Create new game
     */
    public Game()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Initialize game
     */
    public void setup()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        EntityManager entityManager = factory.createEntityManager();
        List<AbstractEffect> allEffects = entityManager.createQuery("SELECT effect FROM AbstractEffect effect", AbstractEffect.class).getResultList();








        Room room = Room.getById(1);

        for (RoomConnection connection : room.getConnectionsTo()) {
            System.out.println(
                connection.getFromRoom().getName() + " connects to " + connection.getToRoom().getName() + " through " + connection.getDirection().getName()
            );
        }

        // Choisit le lieu de départ
        currentRoom = room;

        isRunning = true;
    }

    /**
     * Describes the game's execution cycle
     */
    public void update()
    {
        // Décrit le lieu
        System.out.println("");
        System.out.println("You are in the " + currentRoom.getName() + ".");
        // Affiche la liste des directions possibles
        for (Map.Entry<Direction, Room> entry : currentRoom.getConnectedRooms().entrySet()) {
            System.out.println(entry.getKey().getName() + " is the " + entry.getValue().getName() + ".");
        }
        // Affiche la liste des objets interactif diposnibles
        if (currentRoom.getItems().isEmpty()) {
            System.out.println("No visible items.");
        } else {
            System.out.println("Visible items:");
            for (Item item : currentRoom.getItems()) {
                System.out.println("* " + item.getName());
            }
        }

        // Attendre une saisie de l'utilisateur
        System.out.print("> ");
        String userInput = scanner.nextLine();
        // Vérifier la saisie de l'utilisateur
        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

        // Cherche parmi toutes les directions existantes, à laquelle correspond la saisie de l'utilisateur 
        for (Direction direction : directions) {
            if (direction.getCommand().equals(userInput)) {
                // Récupère le lieu vers lequel la direction choisie doit nous emmener
                Room targetRoom = currentRoom.getRoomInDirection(direction);
                // Si le lieu n'existe pas, c'est donc qu'il n'est pas possible d'aller dans cette direction
                if (targetRoom == null) {
                    System.out.println("You cannot go into that direction!");
                    return;
                }
                // Change le lieu actuel
                currentRoom = targetRoom;
                return;
            }
        }

        // Cherche parmi toutes les commandes existantes, à laquelle correspond la saisie de l'utilisateur
        for (Command command : commands) {
            // Charge la commande de déterminer si la saisie utilisateur lui correspond
            // et récupère le nom de l'élément à laquelle la commande s'applique le cas échéant
            String itemName = command.match(userInput);
            // Si la commande correspond à la saisie utilisateur
            if (itemName != null) {
                // Charge le lieu de déterminer si un élément avec le nom spécifié dans la commande existe
                Item item = currentRoom.findItemByName(itemName);
                // Si le nom d'objet entré par l'utilisateur ne correspond à aucun objet présent dans le lieu
                if (item == null) {
                    System.out.println("There is no such object here!");
                    return;
                }
                // Sinon, charge l'élément de déclencher les effets associés à la commande saisie                
                item.triggerEffects(command);
                return;
            }
        }

        // Si aucune correspondance n'a été trouvée, c'est donc que la commande est invalide
        System.out.println("Invalid command!");
    }

    /**
     * Asses whether the game is currently running
     * @return true if game is running, false if game was terminated
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Terminate game
     */
    public void terminate()
    {
        isRunning = false;
        scanner.close();
    }

    /**
     * Get current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
}
