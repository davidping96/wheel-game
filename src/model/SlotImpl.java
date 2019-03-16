package model;

import model.enumeration.Color;
import model.interfaces.Slot;

import java.util.Objects;

public class SlotImpl implements Slot {

    private int position;

    private Color color;

    private int number;

    public SlotImpl(int position, Color color, int number) {
        this.position = position;
        this.color = color;
        this.number = number;
    }

    /**
     * @return - the position of this slot (clockwise starting from position 0 for Slot GREEN00)
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * @return - the numeric value of this slot as displayed on the gaming wheel
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * @return - the color of this slot based on {@link Color}
     */
    @Override
    public Color getColor() {
        return this.color;
    }

    /**
     * @return <pre> A human readable String that lists the values of this WheelSlot instance (see OutputTrace.txt)
     *
     * <b>NOTE:</b> Must use "proper naming" case i.e. First letter capitalised
     * e.g. "Position: 0, Color: Green00, Number: 0" for "00" slot at top of wheel</pre>
     */
    @Override
    public String toString() {
        return String.format("Position: %d, Color: %s, Number: %d", this.position, this.color, this.number);
    }

    /**
     * @param slot - another Slot to compare with
     * @return - true if the color and number fields are equal
     */
    @Override
    public boolean equals(Slot slot) {
        return (this.number == slot.getNumber() && this.color == slot.getColor());
    }

    /**
     * <pre> <b>NOTE:</b> this is the java.lang.Object @Override
     *
     * its implementation should cast and call through to the type checked method above</pre>
     *
     * @param slot - another Slot to compare with
     * @return - true if the slot values are equal according to above equals method
     */
    @Override
    public boolean equals(Object slot) {
        return (slot instanceof SlotImpl && this.equals((SlotImpl) slot));
    }

    /**
     * <b>NOTE:</b> if equals() is true then generated hashCode should also be equal
     *
     * @return - generated hash code (used by various JCF Collections)
     * <p>
     * The uniqueness of a slot is determined by the number and the color,
     * (as seen above in the equals(Slot slot) method).
     * <p>
     * To handle the scenario where a slot has the same number but a
     * different color we must also take the color into account when
     * generating the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.color);
    }
}
