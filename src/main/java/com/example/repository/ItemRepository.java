package com.example.repository;

import javax.persistence.*;

import com.example.entity.Item;
import com.example.entity.Room;

/**
 * Service responsible for fetching items from database
 */
public class ItemRepository extends Repository<Item>
{
    /**
     * Create new repository
     */
    public ItemRepository()
    {
        super(Item.class);
    }
    
    /**
     * Find an item based on its name and the room it is found in
     * @param name The name of the desired item
     * @param room The room to search for the item
     * @return
     */
    public Item findByNameAndByRoom(String name, Room room)
    {
        try{
            return entityManager.createQuery("SELECT item FROM Item item WHERE item.name = :name AND item.room = :room", Item.class)
                .setParameter("name", name)
                .setParameter("room", room)
                .getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
