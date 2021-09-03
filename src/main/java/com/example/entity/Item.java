package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.example.entity.effect.AbstractEffect;
import com.example.entity.state.State;
import com.example.interfaces.Effect;

/**
 * Represents an interactive item in the universe
 */
@Entity
@Table(name = "items")
public class Item extends AbstractEntity
{
    /**
     * Item name
     */
    @Column(name = "name")
    private String name;
    /**
     * The room in which the item can be found
     */
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    /**
     * List of all effects associated with the item
     */
    @OneToMany
    @JoinColumn(name = "item_id")
    private List<AbstractEffect> effects;
    /**
     * List of all states applying to the item
     */
    @Transient
    private List<State<?>> states;

    public Item()
    {

    }
    
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

        effects = new ArrayList<>();
        states = new ArrayList<>();
    }

    /**
     * Trigger the list of effects associated with the given command
     * @param command The desired command
     */
    public void triggerEffects(Command command)
    {
        // List<Effect> effects = getEffects(command);
        effects = new ArrayList<>();
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
