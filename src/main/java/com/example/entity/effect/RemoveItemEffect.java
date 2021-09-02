package com.example.entity.effect;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.entity.Item;

/**
 * Represents an effect that produces the removal of an targetItem from its room
 */
@Entity
@DiscriminatorValue("RemoveItem")
public class RemoveItemEffect extends AbstractEffect
{
    /**
     * The item to remove
     */
    @ManyToOne
    @JoinColumn(name = "target_item_id")
    private Item targetItem;

    public RemoveItemEffect()
    {

    }

    /**
     * Create new item remove effect
     * @param targetItem
     */
    public RemoveItemEffect(Item targetItem)
    {
        this.targetItem = targetItem;
    }

    /**
     * Get targetItem to remove
     * @return
     */
    public Item getItem()
    {
        return targetItem;
    }

    /**
     * Trigger effect
     */
    @Override
    public void trigger()
    {
        targetItem.setRoom(null);
    }
}
