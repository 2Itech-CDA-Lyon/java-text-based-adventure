package com.example.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.game.command.Command;
import com.example.game.effect.Effect;
import com.example.game.state.State;

/**
 * Represents an interactive item in the universe
 */
public class Item
{
    /**
     * Item name
     */
    private String name;
    /**
     * The room in which the item can be found
     */
    private Room room;
    /**
     * List of all effects associated with the required command
     */
    private Map<Command, List<Effect>> effects;
    /**
     * List of all states applying to the item
     */
    private List<State<?>> states;
    
    /**
     * Create new item
     * @param room Item name
     * @param name The room in which the item can be found
     */
    public Item(Room room, String name)
    {
        this.room = room;
        this.name = name;

        room.addItem(this);

        effects = new HashMap<>();
        states = new ArrayList<>();
    }

    /**
     * Get effects mapped to given command
     * @param command
     * @return
     */
    public List<Effect> getEffects(Command command)
    {
        return effects.get(command);
    }

    /**
     * Map an effect to a command
     * @param command
     * @param message
     */
    public void addEffects(Command command, List<Effect> effects)
    {
        this.effects.put(command, effects);
    }

    /**
     * Trigger the list of effects associated with the given command
     * @param command The desired command
     */
    public void triggerEffects(Command command)
    {
        List<Effect> effects = getEffects(command);
        // Si aucun effet n'a été prévu, affiche le message par défaut de la commande
        if (effects == null) {
            System.out.println(command.getDefaultMessage());
        // Sinon, déclenche les effets les uns après les autres
        } else {
            for (Effect effect : effects) {
                effect.trigger();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room newRoom)
    {
        if (room != null) {
            room.removeItem(this);
        }
        if (newRoom != null) {
            newRoom.addItem(this);
        }
        room = newRoom;
    }

    public List<State<?>> getStates()
    {
        return states;
    }

    public void addState(State<?> state)
    {
        states.add(state);
    }

    public void removeState(State<?> state)
    {
        states.remove(state);
    }
}
