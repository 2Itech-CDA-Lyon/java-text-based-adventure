package com.example.entity.effect;

import javax.persistence.*;

import com.example.entity.*;
import com.example.interfaces.Effect;

/**
 * Assembles all common properties to all concrete effect types
 */
@Entity
@Table(name = "effects")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "effect_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractEffect extends AbstractEntity implements Effect
{
    /**
     * The order in which the effect should be executed
     */
    @Column(name = "_order")
    protected Integer order;
    /**
     * The item that triggers the effect
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    protected Item item;
    /**
     * The command that triggers the effect
     */
    @ManyToOne
    @JoinColumn(name = "command_id")
    protected Command command;
}
