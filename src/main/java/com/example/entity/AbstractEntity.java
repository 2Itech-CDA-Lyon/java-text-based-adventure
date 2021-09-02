package com.example.entity;

import javax.persistence.*;

/**
 * Assembles all properties and methods common to all entities
 */
@MappedSuperclass
public abstract class AbstractEntity
{
    /**
     * Database identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public Integer getId()
    {
        return id;
    }
}
