package com.example.repository;

import javax.persistence.*;

import com.example.entity.Command;

public class CommandRepository extends Repository<Command> {
    
    public CommandRepository()
    {
        super(Command.class);
    }

    /**
     * Find a command based on its command
     * @param command The command to be looked up
     * @return
     */
    public Command findByCommand(String command)
    {
        try {
            return entityManager.createQuery("SELECT command FROM Command command WHERE :command LIKE command.command", Command.class)
                .setParameter("command", command+"%")
                .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
