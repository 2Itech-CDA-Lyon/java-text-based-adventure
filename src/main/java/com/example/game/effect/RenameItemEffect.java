package com.example.game.effect;

import com.example.game.Item;

/**
 * Represents an effect that produces the name changing of an item
 */
public class RenameItemEffect implements Effect
{
    /**
     * The item whose name to change
     */
    private Item item;
    /**
     * The new name to give the item
     */
    private String newName;

    /**
     * Create new item change name effect
     * @param item
     */
    public RenameItemEffect(Item item, String newName)
    {
        this.item = item;
        this.newName = newName;
    }

    /**
     * Get item to remove
     * @return
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * Trigger effect
     */
    public void trigger()
    {
        item.setName(newName);
    }
}
