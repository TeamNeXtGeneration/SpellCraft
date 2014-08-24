package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class DoubleTag extends Tag {
    private String name;
    private Double value;
    public DoubleTag(String name, Double value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public Double getValue()
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
        return 6;
    }
}
