package com.example.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a place the player can visit
 */
public class Room
{
    /**
     * Room name
     */
    private String name;
    /**
     * List of all connected rooms associated with the direction from this room
     */
    private Map<Direction, Room> connectedRooms;
    /**
     * List of all items present in the room
     */
    private List<Item> items;

    /**
     * Create new room
     * @param name Room name
     */
    public Room(String name)
    {
        connectedRooms = new HashMap<Direction, Room>();
        items = new ArrayList<Item>();

        this.name = name;
    }

    /**
     * Get room connected to this room in a given direction
     * @param direction The direction from this room
     * @return
     */
    public Room getRoomInDirection(Direction direction)
    {
        return connectedRooms.get(direction);
    }

    /**
     * Set room connected to this room in a given direction
     * @param direction The direction from this room
     * @param targetRoom The target room
     */
    public void setRoomInDirection(Direction direction, Room targetRoom)
    {
        connectedRooms.put(direction, targetRoom);
    }

    /**
     * Find item with the current name inside the room
     * @param itemName The name of the item to be searched
     * @return The item if it was found, null otherwise
     */
    public Item findItemByName(String itemName)
    {
        for (Item item : items) {
            if (itemName.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Get room name
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get list of all items present in the room
     * @return
     */
    public List<Item> getItems()
    {
        return items;
    }

    /**
     * Get all connected rooms with their associated directions
     * @return
     */
    public Map<Direction, Room> getConnectedRooms()
    {
        return connectedRooms;
    }

    /**
     * Add item to room
     * @param item
     */
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * Remove item from room
     * @param item
     */
    public void removeItem(Item item)
    {
        items.remove(item);
    }
}
