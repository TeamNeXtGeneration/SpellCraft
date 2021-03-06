package de.castelbuilder123.libNBT.tags;

import java.util.Map;

/**
 * Created by Jona on 09.08.14.
 */
public class CompoundTag extends Tag {
    private final Map<String, Tag> value;
    private final String name;

    public CompoundTag(String name, Map<String, Tag> value)
    {
        this.name = name;
        this.value = value;
    }

    @Override
    public Map<String, Tag> getValue()
    {
        return value;
    }

    @Override
    public String getName()
    {
        return name;
    }
    
    public String toString()
    {
    	return "TAG_Compound(\""+ name + "\"): "+value.size()+" entries";
    }

    @Override
    public int getType()
    {
        return 10;
    }
}
