package com.example.game;

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
     * Create new item
     * @param room Item name
     * @param name The room in which the item can be found
     */
    public Item(Room room, String name)
    {
        this.room = room;
        this.name = name;

        room.addItem(this);
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
