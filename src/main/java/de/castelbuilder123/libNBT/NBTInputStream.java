package de.castelbuilder123.libNBT;

import de.castelbuilder123.libNBT.tags.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by Jona on 09.08.14.
 */
public class NBTInputStream {
    DataInputStream is;

    public NBTInputStream(InputStream inputStream, boolean tryGZIP)
    {
        if (tryGZIP)
        {
            try {
                is = new DataInputStream(new GZIPInputStream(inputStream));
                return;
            }
            catch (Exception e) {}
        }
        is = new DataInputStream(inputStream);
    }

    public CompoundTag readRootTag() throws IOException
    {
        CompoundTag tag = null;
        if (is.readByte() == 10)
        {
            tag = readCompoundTag(false, true);
        }
        else
            throw new IOException("File doesn't start with a TAG_COMPOUND");
        return tag;
    }

    public CompoundTag readCompoundTag(boolean named, boolean root) throws IOException
    {
        byte type = 10;
        if (!root)
            type = is.readByte();
        if (type == 10)
        {
        	String title = "";
        	if (named)
        		title = readStringTag(false, false).getValue();
            Map<String, Tag> tagMap = new HashMap<String, Tag>();
            while (true)
            {
                byte tmpType = is.readByte(); // Get Type
                // We cant do a switch here, because of the break we are in a while true loop
                if (tmpType == 0)
                    break;
                else if (tmpType == 1)
                {
                    ByteTag tag = readByteTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 2)
                {
                    ShortTag tag = readShortTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 3)
                {
                    IntTag tag = readIntTag(true, true); // i will add those read methodes later
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 4)
                {
                    LongTag tag = readLongTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 5)
                {
                    FloatTag tag = readFloatTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 6)
                {
                    DoubleTag tag = readDoubleTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 7)
                {
                	ByteArrayTag tag = readByteArrayTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 8)
                {
                    StringTag tag = readStringTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 9)
                {
                	ListTag tag = readListTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
                else if (tmpType == 10)
                {
                    CompoundTag tag = readCompoundTag(true, true);
                    String name = tag.getName();
                    tagMap.put(name, tag);
                }
            }
            return new CompoundTag(title, tagMap);
        }
        else
            throw new IOException("Compound Tag should be read, but it was no TAG_COMPOUND");
    }
    
    public IntTag readIntTag(boolean named, boolean typeDetected) throws IOException
    {
    	byte type = 3;
        if (!typeDetected)
            type = is.readByte();
        if (type == 3)
        {
        	String name = "";
        	if (named)
        		name = readStringTag(false, false).getValue();
        	int value = is.readInt();
        	return new IntTag(name, value);
        }
        else
            throw new IOException("Int Tag should be read, but it was no TAG_INT");
    }
    
    public FloatTag readFloatTag(boolean named, boolean typeDetected) throws IOException
    {
    	byte type = 5;
        if (!typeDetected)
            type = is.readByte();
        if (type == 5)
        {
        	String name = "";
        	if (named)
        		name = readStringTag(false, false).getValue();
        	float value = is.readFloat();
        	return new FloatTag(name,value );
        }
        else
            throw new IOException("Float Tag should be read, but it was no TAG_FLOAT");
    }
    
    public void close() throws IOException
    {
    	is.close();
    }

    public DoubleTag readDoubleTag(boolean named, boolean typeDetected) throws IOException
    {
    	byte type = 6;
        if (!typeDetected)
            type = is.readByte();
        if (type == 6)
        {
        	String name = "";
        	if (named)
        		name = readStringTag(false, false).getValue();
        	double value = is.readDouble();
        	return new DoubleTag(name,value );
        }
        else
            throw new IOException("Double Tag should be read, but it was no TAG_DOUBLE");
    }
    
    public LongTag readLongTag(boolean named, boolean typeDetected) throws IOException
    {
    	byte type = 4;
        if (!typeDetected)
            type = is.readByte();
        if (type == 4)
        {
        	String name = "";
        	if (named)
        		name = readStringTag(false, false).getValue();
        	long value = is.readLong();
        	return new LongTag(name,value);
        }
        else
            throw new IOException("Long Tag should be read, but it was no TAG_LONG");
    }
    
    public ListTag readListTag(boolean named, boolean typeDetected) throws IOException
    {
    	byte type = 5;
        if (!typeDetected)
            type = is.readByte();
        if (type == 5)
        {
        	String name = "";
        	if (named)
        		name = readStringTag(false, false).getValue();
        	byte contentType = readByteTag(false, false).getValue();
        	int lenght = readIntTag(false, false).getValue();
        	switch(contentType)
        	{
        	case 1:
        		List<Object> value1 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value1.add(readByteTag(false, false));
        		}
        		return new ListTag(name, value1,1);
        	case 2:
        		List<Object> value2 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value2.add(readShortTag(false, false));
        		}
        		return new ListTag(name, value2,2);
        	case 3:
        		List<Object> value3 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value3.add(readIntTag(false, false));
        		}
        		return new ListTag(name, value3,3);
        	case 4:
        		List<Object> value4 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value4.add(readLongTag(false, false));
        		}
        		return new ListTag(name, value4,4);
        	case 5:
        		List<Object> value5 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value5.add(readFloatTag(false, false));
        		}
        		return new ListTag(name, value5,5);
        	case 6:
        		List<Object> value6 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value6.add(readDoubleTag(false, false));
        		}
        		return new ListTag(name, value6,6);
        	case 7:
        		List<Object> value7 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value7.add(readByteArrayTag(false, false));
        		}
        		return new ListTag(name, value7, 7);
        	case 8:
        		List<Object> value8 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value8.add(readStringTag(false, false));
        		}
        		return new ListTag(name, value8,8);
        	case 9:
        		List<Object> value9 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value9.add(readListTag(false, false));
        		}
        		return new ListTag(name, value9,9);
        	case 10:
        		List<Object> value10 = new ArrayList<Object>();
        		for (int i = 0; i<lenght;i++)
        		{
        			value10.add(readCompoundTag(false, false));
        		}
        		return new ListTag(name, value10,10);
        		default:
        			throw new IOException("Invalid ContentType in List");
        	}
        	
        }
        else
            throw new IOException("List Tag should be read, but it was no TAG_LIST");
    }
    
    public ByteArrayTag readByteArrayTag(boolean named, boolean typeDetected) throws IOException
    {
    	byte type = 7;
    	if (!typeDetected)
    		type = is.readByte();
    	if (type == 7)
    	{
    		String name = "";
    		if (named)
    			name = readStringTag(false, false).getValue();
    		int lenght = readIntTag(false, false).getValue();
    		List<Byte> value = new ArrayList<Byte>(); // Because we will add stuff and things to it
    		// For loop time
    		for (int i = 0; i<lenght;i++)
    		{
    			value.add(is.readByte());
    		}
    		return new ByteArrayTag(name, value.toArray(new Byte[lenght]));
    	}
    	else
    		throw new IOException("Byte Array Tag should be read, but it was no TAG_BYTEARRAY");
    }
    
    public ByteTag readByteTag(boolean named, boolean typeDetected) throws IOException
    {
        byte type = 1;
        if (!typeDetected)
            type = is.readByte();
        if (type == 1)
        {
            String name = "";
            if (named)
                name = readStringTag(false, false).getValue();
            byte value = is.readByte();
            return new ByteTag(name, value); 
        }
        else
            throw new IOException("Byte Tag should be read, but it was no TAG_BYTE");
    }


    public StringTag readStringTag(boolean named, boolean typeDetected) throws IOException
    {
        byte type = 8;
        if (!typeDetected)
            type = is.readByte();
        if (type == 8)
        {
            String name = "";
            if (named)
                name = readStringTag(false, false).getValue();
            Short lenght = readShortTag(false, false).getValue();
            byte[] bValue = new byte[lenght];
            for (int i = 1; i < lenght; i++)
            {
                bValue[i-1] = is.readByte();
            }
            String value = new String(bValue, StandardCharsets.UTF_8);
            return new StringTag(name, value);
        }
        else
            throw new IOException("String Tag should be read, but it was no TAG_STRING");
    }

    public ShortTag readShortTag(boolean named, boolean typeDetected) throws IOException
    {
        byte type = 2;
        if (!typeDetected)
            type = is.readByte();
        if (type == 2)
        {
            String name = "";
            if (named)
                name = readStringTag(false, false).getValue();
            Short value = is.readShort();
            return new ShortTag(name, value);
        }
        else
            throw new IOException("Short Tag should be read, but it was no TAG_SHORT");
    }
}
