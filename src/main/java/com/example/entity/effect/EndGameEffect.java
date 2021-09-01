package com.example.entity.effect;

import javax.persistence.*;

import com.example.game.Game;

/**
 * Represents an effect that produces the end of the current game
 */
@Entity
@DiscriminatorValue("EndGame")
public class EndGameEffect extends AbstractEffect
{
    /**
     * The current game
     */
    @Transient
    private Game game;

    public EndGameEffect()
    {

    }

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
