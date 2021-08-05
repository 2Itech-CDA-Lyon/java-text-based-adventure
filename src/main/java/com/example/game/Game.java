package com.example.game;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.game.command.Command;
import com.example.game.effect.ChangeStateEffect;
import com.example.game.effect.Effect;
import com.example.game.effect.EndGameEffect;
import com.example.game.effect.MessageEffect;
import com.example.game.effect.RemoveItemEffect;
import com.example.game.effect.RenameItemEffect;
import com.example.game.state.State;

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

        Item bed = new Item(bedroom, "bed");
        Item desk = new Item(bedroom, "desk");
        Item pen = new Item(bedroom, "pen");
        Item window = new Item(bedroom, "window");
        Item cookie = new Item(bathroom, "cookie");
        Item plug = new Item(bathroom, "plug");

        State<Boolean> windowOpen = new State<Boolean>(window, "open", false);

        Command close = new Command("close", "This does not seem to close.");
        Command open = new Command("open", "This does not seem to open.");
        Command pickUp = new Command("pick up", "You don't want to carry this.");
        Command use = new Command("use", "You have no idea how to use this.");
        Command eat = new Command("eat", "This does not seem edible...");
        Command touch = new Command("touch", "It has no special feeling to it.");
        commands = new Command[] { close, eat, open, pickUp, use, touch };

        bed.addEffects(use, Arrays.<Effect>asList(
            new MessageEffect("You take a quick nap. You feel refreshed!")
        ));
        desk.addEffects(open, Arrays.<Effect>asList(
            new MessageEffect("The desk's drawers are desperately empty."),
            new RenameItemEffect(desk, "empty desk")
        ));
        pen.addEffects(pickUp, Arrays.<Effect>asList(
            new RemoveItemEffect(pen),
            new MessageEffect("You picked up the pen.")
        ));
        window.addEffects(open, Arrays.<Effect>asList(
            new ChangeStateEffect<Boolean>(windowOpen, true),
            new MessageEffect("The window is now open.")
        ));
        window.addEffects(close, Arrays.<Effect>asList(
            new ChangeStateEffect<Boolean>(windowOpen, false),
            new MessageEffect("The window is now closed.")
        ));
        cookie.addEffects(eat, Arrays.<Effect>asList(
            new RemoveItemEffect(cookie),
            new MessageEffect("You ate the cookie. Delicious!")
        ));
        plug.addEffects(touch, Arrays.<Effect>asList(
            new MessageEffect("You electrocuted yourself!"),
            new EndGameEffect(this)
        ));

        // Choisit le lieu de départ
        currentRoom = bedroom;

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
            // Cherche une correspondance entre la commande et la saisie de l'utilisateur
            Pattern pattern = Pattern.compile("^" + command.getCommand() + "\\s(.+)$");
            Matcher matcher = pattern.matcher(userInput);
            if (matcher.find()) {
                // Cherche une correspondance avec les objets présents dans le lieu
                String itemName = matcher.group(1);
                for (Item item : currentRoom.getItems()) {
                    if (itemName.equals(item.getName())) {
                        // Si une correspondance a été trouvée, récupère les effets associés à la commande désirée dans l'objet
                        List<Effect> effects = item.getEffects(command);
                        // Si aucun effet n'a été prévu, affiche le message par défaut de la commande
                        if (effects == null) {
                            System.out.println(command.getDefaultMessage());
                        // Sinon, déclenche les effets les uns après les autres
                        } else {
                            for (Effect effect : effects) {
                                effect.trigger();
                            }
                        }
                        return;
                    }
                }
                // Si le nom d'objet entré par l'utilisateur ne correspond à aucun objet présent dans le lieu
                System.out.println("There is no such object here!");
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
