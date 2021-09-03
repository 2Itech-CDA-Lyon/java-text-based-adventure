package com.example.entity.state;

import javax.persistence.*;

import com.example.entity.Item;

/**
 * Represents a state
 */
@Entity
@Table(name = "states")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="state_type", discriminatorType = DiscriminatorType.STRING)
public abstract class State<T>
{
    /**
     * Database identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;
    /**
     * The state's value
     */
    @Transient
    protected T value;
    /**
     * The state's name
     */
    @Column(name = "name")
    protected String name;
    /**
     * The item the state applies to
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    protected Item item;

    public State()
    {

    }

    /**
     * Initialize objet properties after being populated with data from database
     */
    @PostLoad
    protected void initialize()
    {
        value = getDefaultValue();
    }

    /**
     * Create new boolean state
     * @param item The item the state applies to
     * @param name The state's name
     * @param defaultValue The state's default value
     */
    public State(Item item, String name, T defaultValue)
    {
        this.item = item;
        this.name = name;
        setDefaultValue(defaultValue);
        resetValue();

        item.addState(this);
    }

    /**
     * Reset state's value to its default value
     */
    public void resetValue()
    {
        value = getDefaultValue();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }

    abstract public T getDefaultValue();

    abstract protected void setDefaultValue(T defaultValue);
}
