package com.example.game;

import java.util.HashMap;
import java.util.Map;

import com.example.game.command.Command;

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
    private Map<Command, String> effects;
    
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
     * Get effect mapped to given command
     * @param command
     * @return
     */
    public String getEffect(Command command)
    {
        return effects.get(command);
    }

    /**
     * Map an effect to a command
     * @param command
     * @param message
     */
    public void addEffect(Command command, String message)
    {
        effects.put(command, message);
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
