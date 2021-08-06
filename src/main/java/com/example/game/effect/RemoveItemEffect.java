package com.example.game.effect;

import com.example.game.Item;

/**
 * Represents an effect that produces the removal of an item from its room
 */
public class RemoveItemEffect implements Effect
{
    /**
     * The item to remove
     */
    private Item item;

    /**
     * Create new item remove effect
     * @param item
     */
    public RemoveItemEffect(Item item)
    {
        this.item = item;
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
        item.setRoom(null);
    }
}
