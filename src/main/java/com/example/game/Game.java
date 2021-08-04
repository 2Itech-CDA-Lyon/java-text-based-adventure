package com.example.game;

import java.util.Scanner;

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
        // Crée les éléments de l'univers
        Direction east = new Direction("east", "East");
        Direction south = new Direction("south", "South");
        Direction west = new Direction("west", "West");
        Direction north = new Direction("north", "North");
        directions = new Direction[] { east, south, west, north };

        Room bedroom = new Room("bedroom");
        Room bathroom = new Room("bathroom");
        Room corridor = new Room("corridor");
        bedroom.setRoomInDirection(west, bathroom);
        bathroom.setRoomInDirection(east, bedroom);
        bedroom.setRoomInDirection(north, corridor);
        corridor.setRoomInDirection(south, bedroom);
        // Choisit le lieu de départ
        currentRoom = bedroom;

        isRunning = true;
    }

    /**
     * Describes the game's execution cycle
     */
    public void update()
    {
        // Décrire le lieu
        System.out.println("You are in the " + currentRoom.getName() + ".");
        // Attendre une saisie de l'utilisateur
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
        // Si aucune correspondance avec une direction n'a été trouvée, c'est donc que la commande est invalide
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
    private void terminate()
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
