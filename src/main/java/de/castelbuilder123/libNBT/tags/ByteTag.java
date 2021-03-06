package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class ByteTag extends Tag {
    private String name;
    private Byte value;
    public ByteTag(String name, Byte value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public Byte getValue()
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
        return 1;
    }
}
