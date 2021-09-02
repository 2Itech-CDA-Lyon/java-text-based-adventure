package com.example.entity.effect;

import javax.persistence.*;

import com.example.entity.Item;

/**
 * Represents an effect that produces the name changing of an targetItem
 */
@Entity
@DiscriminatorValue("RenameItem")
public class RenameItemEffect extends AbstractEffect
{
    /**
     * The item whose name to change
     */
    @ManyToOne()
    @JoinColumn(name = "target_item_id")
    private Item targetItem;
    /**
     * The new name to give the targetItem
     */
    @Column(name = "new_name")
    private String newName;

    public RenameItemEffect()
    {

    }

    /**
     * Create new item change name effect
     * @param targetItem
     */
    public RenameItemEffect(Item targetItem, String newName)
    {
        this.targetItem = targetItem;
        this.newName = newName;
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
        targetItem.setName(newName);
    }
}
