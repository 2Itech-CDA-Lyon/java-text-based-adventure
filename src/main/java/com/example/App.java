package com.example;

import com.example.game.Game;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        // Crée une nouvelle partie
        Game game = new Game();
        // Initialise la partie
        game.setup();
        // Tant que la partie est en cours
        while (game.isRunning()) {
            // Réalise un cycle d'exécution de la partie
            game.update();
        }
    }
}
