package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class FloatTag extends Tag {
    private String name;
    private Float value;
    public FloatTag(String name, Float value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public Float getValue()
    {
        return value;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getType()
    {
        return 5;
    }
}
