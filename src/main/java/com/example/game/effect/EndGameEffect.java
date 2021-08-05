package com.example.game.effect;

import com.example.game.Game;

/**
 * Represents an effect that produces the end of the current game
 */
public class EndGameEffect implements Effect
{
    /**
     * The current game
     */
    private Game game;

    /**
     * Create new game end effect
     * @param game The current game
     */
    public EndGameEffect(Game game)
    {
        this.game = game;
    }

    /**
     * Trigger the effect
     */
    public void trigger()
    {
        game.terminate();
    }
}
