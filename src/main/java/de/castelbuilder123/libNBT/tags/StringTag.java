package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class StringTag extends Tag {
    private String name;
    private String value;
    public StringTag(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getValue()
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
        return 8;
    }
}
