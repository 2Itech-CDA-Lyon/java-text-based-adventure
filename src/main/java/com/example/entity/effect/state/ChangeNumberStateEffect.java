package com.example.entity.effect.state;

import javax.persistence.*;

/**
 * Represents an effect that produces a change in a number state
 */
@Entity
@DiscriminatorValue("ChangeNumberState")
public class ChangeNumberStateEffect extends ChangeStateEffect<Double>
{
    

    final static public int OPERATOR_NUMBER_SET = 0;
    final static public int OPERATOR_NUMBER_PLUS = 1;
    final static public int OPERATOR_NUMBER_MINUS = 2;
    final static public int OPERATOR_NUMBER_MULTIPLY = 3;
    final static public int OPERATOR_NUMBER_DIVIDE = 4;
    final static public int OPERATOR_NUMBER_MODULO = 5;

    /**
     * The operand to apply
     */
    @Column(name = "number_value")
    private Double value;

    public ChangeNumberStateEffect()
    {
        
    }

    @Override    
        public void trigger()
        {
            switch(operator)
            {
                // Set
                case OPERATOR_NUMBER_SET: 
                    break;
                // Plus
                case OPERATOR_NUMBER_PLUS:
                    break;
                // Minus
                case OPERATOR_NUMBER_MINUS:
                    break;
                // Multiply
                case OPERATOR_NUMBER_MULTIPLY:
                    break;
            }
        }

    @Override
    public Double getValue()
    {
        return value;
    };

    @Override
    protected void setValue(Double value)
    {
        this.value = value;
    }

}
