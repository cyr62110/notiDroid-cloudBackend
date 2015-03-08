package fr.cvlaminck.remapper.objects.mapper;

import org.junit.Assert;

import java.util.ArrayList;

public class SimpleObject {

    public byte simpleByte;

    public char simpleChar;

    public short simpleShort;

    public int simpleInt;

    public long simpleLong;

    public float simpleFloat;

    public double simpleDouble;

    public Byte simpleBoxedByte;

    public Character simpleBoxedChar;

    public Short simpleBoxedShort;

    public Integer simpleBoxedInteger;

    public Long simpleBoxedLong;

    public Float simpleBoxedFloat;

    public Double simpleBoxedDouble;

    public String simpleString;

    public ArrayList<String> simpleArrayList;

    public static SimpleObject build() {
        SimpleObject o1 = new SimpleObject();
        o1.simpleByte = 1;
        o1.simpleChar = 2;
        o1.simpleShort = 3;
        o1.simpleInt = 4;
        o1.simpleLong = 5l;
        o1.simpleFloat = 6f;
        o1.simpleDouble = 7d;
        o1.simpleBoxedByte = 11;
        o1.simpleBoxedChar = 12;
        o1.simpleBoxedShort = 13;
        o1.simpleBoxedInteger = 14;
        o1.simpleBoxedLong = 15l;
        o1.simpleBoxedFloat = 16f;
        o1.simpleBoxedDouble = 17d;
        o1.simpleString  = "string";

        o1.simpleArrayList = new ArrayList<String>();
        o1.simpleArrayList.add("string");
        return o1;
    }

    public static void assertEquals(SimpleObject o1, SimpleObject o2) {
        Assert.assertEquals(o1.simpleByte, o2.simpleByte);
        Assert.assertEquals(o1.simpleChar, o2.simpleChar);
        Assert.assertEquals(o1.simpleShort, o2.simpleShort);
        Assert.assertEquals(o1.simpleInt, o2.simpleInt);
        Assert.assertEquals(o1.simpleLong, o2.simpleLong);
        Assert.assertEquals(o1.simpleFloat, o2.simpleFloat, 0f);
        Assert.assertEquals(o1.simpleDouble, o2.simpleDouble, 0d);

        Assert.assertEquals(o1.simpleBoxedByte, o2.simpleBoxedByte);
        Assert.assertEquals(o1.simpleBoxedChar, o2.simpleBoxedChar);
        Assert.assertEquals(o1.simpleBoxedShort, o2.simpleBoxedShort);
        Assert.assertEquals(o1.simpleBoxedInteger, o2.simpleBoxedInteger);
        Assert.assertEquals(o1.simpleBoxedLong, o2.simpleBoxedLong);
        Assert.assertEquals(o1.simpleBoxedFloat, o2.simpleBoxedFloat);
        Assert.assertEquals(o1.simpleBoxedDouble, o2.simpleBoxedDouble);

        Assert.assertEquals(o1.simpleString, o2.simpleString);

        Assert.assertNotNull(o2.simpleArrayList);
        Assert.assertEquals(o1.simpleArrayList.size(), o2.simpleArrayList.size());
        Assert.assertEquals(o1.simpleArrayList.get(0), o2.simpleArrayList.get(0));
    }
}
