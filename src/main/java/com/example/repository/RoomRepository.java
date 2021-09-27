package com.example.repository;

import javax.persistence.*;

import com.example.entity.Direction;
import com.example.entity.Room;

/**
 * Service responsible for fetching rooms from database
 */
public class RoomRepository extends Repository<Room>
{
    /**
     * Create new repository
     */
    public RoomRepository() {
        super(Room.class);
    }

    /**
     * Find the room connected to another room when following a given direction
     * @param startingRoom The room from which the desired room is connected
     * @param direction The direction to follow
     * @return
     */
    public Room findByStartingRoomAndDirection(Room startingRoom, Direction direction)
    {
        try {
            Room room = entityManager.createQuery("SELECT room FROM Room room INNER JOIN room.connectionsTo AS connection WHERE connection.fromRoom = :startingRoom AND connection.direction = :direction", Room.class)
                .setParameter("startingRoom", startingRoom)
                .setParameter("direction", direction)
                .getSingleResult();

            return room;
        }
        catch (NoResultException exception) {
            return null;
        }
    }
}
