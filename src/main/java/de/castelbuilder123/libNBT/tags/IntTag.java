package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class IntTag extends Tag {
    private String name;
    private Integer value;
    public IntTag(String name, Integer value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public Integer getValue()
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
        return 3;
    }
}
