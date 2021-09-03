package com.example.game;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.example.entity.Command;
import com.example.entity.Direction;
import com.example.entity.Item;
import com.example.entity.Room;
import com.example.entity.RoomConnection;
import com.example.entity.effect.AbstractEffect;
import com.example.interfaces.Effect;
import com.example.repository.CommandRepository;
import com.example.repository.DirectionRepository;
import com.example.repository.EffectRepository;
import com.example.repository.ItemRepository;
import com.example.repository.RoomRepository;

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
        // Choisit le lieu de départ
        RoomRepository roomRepository = new RoomRepository();
        currentRoom = roomRepository.findById(1);

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
        for (RoomConnection connection : currentRoom.getConnectionsFrom()) {
            System.out.println(
                String.format("%s is the %s.", connection.getDirection().getName(), connection.getToRoom().getName())
            );
        }
        // Affiche la liste des objets interactifs diposnibles
        List<Item> itemsInRoom = currentRoom.getItems();
        if (itemsInRoom.isEmpty()) {
            System.out.println("No visible items.");
        } else {
            System.out.println("Visible items:");
            for (Item item : itemsInRoom) {
                System.out.println("* " + item.getName());
            }
        }

        // Attend une saisie de l'utilisateur
        System.out.print("> ");
        String userInput = scanner.nextLine();
        // Vérifie la saisie de l'utilisateur
        if ("exit".equals(userInput)) {
            terminate();
            return;
        }

        // Récupère la direction correspondant à la saisie de l'utilisateur en base de données
        DirectionRepository directionRepository = new DirectionRepository();
        Direction direction = directionRepository.findByCommand(userInput);

        // Si la saisie de l'utilisateur correspondait bien à une direction
        if (direction != null) {
            RoomRepository roomRepository = new RoomRepository();

            // Récupère le lieu vers lequel la direction choisie doit nous emmener
            Room targetRoom = roomRepository.findByStartingRoomAndDirection(currentRoom, direction);
            // Si le lieu n'existe pas, c'est donc qu'il n'est pas possible d'aller dans cette direction
            if (targetRoom == null) {
                System.out.println("You cannot go into that direction!");
                return;
            }
            // Change le lieu actuel
            currentRoom = targetRoom;
            return;
        }

        // Récupère la commande correspondant à la saisie de l'utilisateur en base de données
        CommandRepository commandRepository = new CommandRepository();
        Command command = commandRepository.findByCommand(userInput);

        // Si la saisie de l'utilisateur correspondait bien à une commande
        if (command != null) {
            // Récupère le nom de l'objet dans la commande entrée par l'utilisateur
            String itemName = command.match(userInput);
            // Récupère l'objet correspondant dans le lieu actuel en base de données
            ItemRepository itemRepository = new ItemRepository();
            Item item = itemRepository.findByNameAndByRoom(itemName, currentRoom);

            // Si le nom d'objet entré par l'utilisateur ne correspond à aucun objet présent dans le lieu actuel
            if (item == null) {
                System.out.println("There is no such object here!");
                return;
            }

            // Récupère l'ensemble des effets prévus lorsque la commande entrée par l'utilisateur est utilisée avec l'objet
            EffectRepository effectRepository = new EffectRepository();
            List<AbstractEffect> effects = effectRepository.findByItemAndCommand(item, command);

            // Si aucun effet n'a été prévu lorsqu'on utilsie cette commande sur cet objet, affiche le message par défaut de la commande
            if (effects.isEmpty()) {
                System.out.println(command.getDefaultMessage());
                return;
            }

            // Sinon, déclenche tous les effets dans l'ordre
            for (AbstractEffect effect : effects) {
                effect.trigger();
            }
            
            return;
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
