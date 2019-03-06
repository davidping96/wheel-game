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

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return String.format("Position: %d, Color: %s, Number: %d", this.position, this.color, this.number);
    }

    @Override
    public boolean equals(Slot slot) {
        return (this.number == slot.getNumber() && this.color == slot.getColor());
    }

    @Override
    public boolean equals(Object slot) {
        return (slot instanceof SlotImpl && this.equals((SlotImpl)slot));
    }

    /**
     * The uniqueness of a slot is determined by the number and the color,
     * (as seen above in the equals(Slot slot) method).
     *
     * To handle the scenario where a slot has the same number but a
     * different color we must also take the color into account when
     * generating the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.color);
    }
}
