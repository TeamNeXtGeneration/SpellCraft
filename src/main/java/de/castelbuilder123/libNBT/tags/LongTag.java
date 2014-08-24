package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class LongTag extends Tag {
    private String name;
    private Long value;
    public LongTag(String name, Long value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public Long getValue()
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
        return 4;
    }
}
