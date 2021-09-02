package com.example.repository;

import java.util.List;

import javax.persistence.*;

import com.example.entity.AbstractEntity;

/**
 * Assembles all properties and methods common to services responsible for fetching data from database
 */
public abstract class Repository<T extends AbstractEntity>
{
    /**
     * The entity the repository should manage
     */
    private Class<T> entityType;
    /**
     * The entity manager used to query the database
     */
    protected EntityManager entityManager;
    
    /**
     * Create new repository
     * @param entityType The entity the repository should manage
     */
    public Repository(Class<T> entityType)
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TextBasedAdventure");
        entityManager = factory.createEntityManager();
        this.entityType = entityType;
    }

    /**
     * Find all existing entities in database
     * @return
     */
    public List<T> findAll()
    {   
        return entityManager.createQuery(String.format("SELECT entity FROM %s entity", entityType.getName()), entityType).getResultList();
    }

    /**
     * Find an existing entity based on its database identifier
     * @param id The desired entity's database identifier
     * @return
     */
    public T findById(int id)
    {
        return entityManager.createQuery(String.format("SELECT entity FROM %s entity WHERE entity.id = :id", entityType.getName()), entityType)
            .setParameter("id", id)
            .getSingleResult();
    }
}
