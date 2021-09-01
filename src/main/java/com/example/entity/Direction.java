package com.example.entity;

import javax.persistence.*;

/**
 * Represents a direction that connects two rooms
 */
@Entity
@Table(name = "directions")
public class Direction
{
    /**
     * Database identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * Command that triggers the direction
     */
    @Column(name = "command")
    private String command;
    /**
     * Name of the direction
     */
    @Column(name = "name")
    private String name;

    public Direction()
    {
        
    }

    /**
     * Create new direction
     * @param command
     * @param name
     */
    public Direction(String command, String name)
    {
        this.command = command;
        this.name = name;
    }

    /**
     * Get direction name
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get direction command
     * @return
     */
    public String getCommand()
    {
        return command;
    }
}
