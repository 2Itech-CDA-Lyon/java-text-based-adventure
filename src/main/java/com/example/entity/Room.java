package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Represents a place the player can visit
 */
@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity
{
    /**
     * Room name
     */
    @Column(name = "name")
    private String name;
    /**
     * List of all room connections starting from this room
     */
    @OneToMany
    @JoinColumn(name = "from_room_id")
    private List<RoomConnection> connectionsFrom;
    /**
     * List of all room connections targeting this room
     */
    @OneToMany
    @JoinColumn(name = "to_room_id")
    private List<RoomConnection> connectionsTo;
    /**
     * List of all items present in the room
     */
    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Item> items;

    public Room()
    {

    }

    /**
     * Create new room
     * @param name Room name
     */
    public Room(String name)
    {
        // connectedRooms = new HashMap<Direction, Room>();
        items = new ArrayList<Item>();

        this.name = name;
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

    public List<RoomConnection> getConnectionsFrom()
    {
        return connectionsFrom;
    }

    public List<RoomConnection> getConnectionsTo()
    {
        return connectionsTo;
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
