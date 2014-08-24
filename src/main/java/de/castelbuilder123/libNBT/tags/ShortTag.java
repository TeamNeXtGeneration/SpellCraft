package de.castelbuilder123.libNBT.tags;

/**
 * Created by Jona on 09.08.14.
 */
public class ShortTag extends Tag {
    private String name;
    private Short value;
    public ShortTag(String name, Short value)
    {
        this.name = name; // ok? yep long,float, double.. right
        this.value = value;
    }
    
    @Override
    public Short getValue()
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
        return 2;
    }
}
