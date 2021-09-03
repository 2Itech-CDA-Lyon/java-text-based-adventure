package com.example.repository;

import javax.persistence.*;

import com.example.entity.Direction;

public class DirectionRepository extends Repository<Direction>
{
    public DirectionRepository()
    {
        super(Direction.class);
    }

    /**
     * Find a command based on its command
     * @param command The command to be looked up
     * @return
     */
    public Direction findByCommand(String command)
    {
        try {
            return entityManager.createQuery("SELECT direction FROM Direction direction WHERE direction.command = :command", Direction.class)
                .setParameter("command", command)
                .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
