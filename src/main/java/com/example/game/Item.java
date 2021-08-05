package com.example.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.game.command.Command;
import com.example.game.effect.Effect;

/**
 * Represents an interactive item in the universe
 */
public class Item
{
    /**
     * Item name
     */
    private String name;
    /**
     * The room in which the item can be found
     */
    private Room room;
    /**
     * List of all effects associated with the required command
     */
    private Map<Command, List<Effect>> effects;
    
    /**
     * Create new item
     * @param room Item name
     * @param name The room in which the item can be found
     */
    public Item(Room room, String name)
    {
        this.room = room;
        this.name = name;

        room.addItem(this);

        effects = new HashMap<>();
    }

    /**
     * Get effects mapped to given command
     * @param command
     * @return
     */
    public List<Effect> getEffects(Command command)
    {
        return effects.get(command);
    }

    /**
     * Map an effect to a command
     * @param command
     * @param message
     */
    public void addEffects(Command command, List<Effect> effects)
    {
        this.effects.put(command, effects);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }
}
