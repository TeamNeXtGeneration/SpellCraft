package de.castelbuilder123.libNBT.tags;

import java.util.List;

/**
 * Created by Jona on 09.08.14.
 */
public class ListTag extends Tag {
    private String name;
    private List<Object> value;
    private int ContentType;
    public ListTag(String name, List<Object> value, int ContentType)
    {
        this.name = name;
        this.value = value;
        this.ContentType = ContentType;
    }

    @Override
    public List<Object> getValue()
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
        return 9;
    }

    public int getContentType()
    {
        return ContentType;
    }
}
