package com.example.repository;

import javax.persistence.NoResultException;

import com.example.entity.Direction;
import com.example.entity.Room;
import com.example.entity.RoomConnection;

public class RoomConnectionRepository extends Repository<RoomConnection>
{
    public RoomConnectionRepository()
    {
        super(RoomConnection.class);
    }

    public RoomConnection findByStartingRoomAndDirection(Room startingRoom, Direction direction) {
        try {
            return entityManager.createQuery("SELECT roomConnection FROM RoomConnection roomConnection WHERE fromRoom = :fromRoom AND direction = :direction", RoomConnection.class)
                .setParameter("fromRoom", startingRoom)
                .setParameter("direction", direction)
                .getSingleResult();
        }
        catch (NoResultException exception) {
            return null;
        }
    }
}
