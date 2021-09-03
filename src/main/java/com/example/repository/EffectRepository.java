package com.example.repository;

import java.util.List;

import javax.persistence.*;

import com.example.entity.Command;
import com.example.entity.Item;
import com.example.entity.effect.AbstractEffect;

/**
 * Service responsible for fetching effects from database
 */
public class EffectRepository extends Repository<AbstractEffect>
{
    /**
     * Create new repository
     */
    public EffectRepository()
    {
        super(AbstractEffect.class);
    }

    /**
     * Find all effects that should be executed when a command is used on an item
     * @param item The item that triggers the effects
     * @param command The command that triggers the effects
     * @return
     */
    public List<AbstractEffect> findByItemAndCommand(Item item, Command command)
    {
        try{
            return entityManager.createQuery("SELECT effect FROM AbstractEffect effect WHERE item = :item AND command = :command ORDER BY order ASC", AbstractEffect.class)
                .setParameter("item", item)
                .setParameter("command", command)
                .getResultList();
        }
        catch (NoResultException e){
            return null;
        }
    }
}
