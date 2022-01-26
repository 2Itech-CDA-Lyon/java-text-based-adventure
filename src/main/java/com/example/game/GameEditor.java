package com.example.game;

import java.util.Scanner;

import com.example.game.mode.*;

public class GameEditor
{
    /**
     * Interface used to read user input
     */
    private Scanner scanner;
    /**
     * Whether the game is running or not
     */
    private boolean isRunning;

    private EditorMode currentMode;

    /**
     * Create new game
     */
    public GameEditor()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Initialize game
     */
    public void setup()
    {
        // Choisit le mode de départ
        currentMode = new RoomListMode(this);

        isRunning = true;
    }

    /**
     * Describes the editor's execution cycle
     */
    public void update()
    {
        String userInput;
        
        // Affichage du menu
        // En fonction du menu dans lequel on se trouve actuellement
        System.out.println("");
        currentMode.display();

        // Attend une saisie utilisateur
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();

        // Traite la saisie de l'utilisateur
        // En fonction du menu dans lequel on se trouve actuellement
        currentMode.processInput(userInput);
    }

    public void setMode(EditorMode mode)
    {
        this.currentMode = mode;
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
}
