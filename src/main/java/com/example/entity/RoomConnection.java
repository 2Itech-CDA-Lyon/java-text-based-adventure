package com.example.entity;

import javax.persistence.*;

/**
 * Represents a connection between two rooms
 */
@Entity
@Table(name = "room_connections")
public class RoomConnection extends AbstractEntity
{
    /**
     * The starting room
     */
    @ManyToOne
    @JoinColumn(name = "from_room_id")
    private Room fromRoom;
    /**
     * The target room
     */
    @ManyToOne
    @JoinColumn(name = "to_room_id")
    private Room toRoom;
    /**
     * The direction to use
     */
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

    public RoomConnection()
    {
        
    }

    public Room getFromRoom() {
        return fromRoom;
    }

    public void setFromRoom(Room fromRoom) {
        this.fromRoom = fromRoom;
    }

    public Room getToRoom() {
        return toRoom;
    }

    public void setToRoom(Room toRoom) {
        this.toRoom = toRoom;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
