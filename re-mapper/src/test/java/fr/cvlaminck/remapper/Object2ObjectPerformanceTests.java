package fr.cvlaminck.remapper;

import fr.cvlaminck.remapper.api.Object2ObjectMapper;
import fr.cvlaminck.remapper.impl.DefaultObject2ObjectMapper;
import fr.cvlaminck.remapper.impl.mappings.caches.KeepAllInMemoryObject2ObjectMappingCache;
import fr.cvlaminck.remapper.objects.mapper.SimpleObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotSame;

public class Object2ObjectPerformanceTests {

    private static final long NUMBER_OF_CONVERSION = 1000000L;

    private SimpleObject handWrittenConvert(SimpleObject o1) {
        SimpleObject o2 = new SimpleObject();
        o2.simpleByte = o1.simpleByte;
        o2.simpleChar = o1.simpleChar;
        o2.simpleShort = o1.simpleShort;
        o2.simpleInt = o1.simpleInt;
        o2.simpleLong = o1.simpleLong;
        o2.simpleFloat = o1.simpleFloat;
        o2.simpleDouble = o1.simpleDouble;

        o2.simpleBoxedByte = Byte.valueOf(o1.simpleBoxedByte);
        o2.simpleBoxedChar = Character.valueOf(o1.simpleBoxedChar);
        o2.simpleBoxedShort = Short.valueOf(o1.simpleBoxedShort);
        o2.simpleBoxedInteger = Integer.valueOf(o1.simpleBoxedInteger);
        o2.simpleBoxedLong = Long.valueOf(o1.simpleBoxedLong);
        o2.simpleBoxedFloat = Float.valueOf(o1.simpleBoxedFloat);
        o2.simpleBoxedDouble = Double.valueOf(o1.simpleBoxedDouble);

        o2.simpleString = new String(o1.simpleString);

        return o2;
    }

    public void testHandWrittenConvert() {
        SimpleObject o1 = SimpleObject.build();

        SimpleObject o2 = handWrittenConvert(o1);

        assertNotSame(o1, o2);
        SimpleObject.assertEquals(o1, o2);
    }

    public void testConvertPerformanceHandWrittenCode() {
        SimpleObject o1 = SimpleObject.build();
        List<Long> conversionTimes = new ArrayList<Long>();
        long startTime, stopTime;

        for(long i = 0; i < NUMBER_OF_CONVERSION; i++) {
            startTime = System.nanoTime();
            SimpleObject o2 = handWrittenConvert(o1);
            stopTime = System.nanoTime();
            conversionTimes.add(stopTime - startTime);
        }

        long total = 0L;
        for(Long l : conversionTimes) {
            total += l;
        }
        long avgConversionTime = total / NUMBER_OF_CONVERSION;

        System.out.println("Handwritten : Average conversion time : " + avgConversionTime + "ns over " + NUMBER_OF_CONVERSION + " convertions");
    }

    public void testConvertPerformanceO2OWithoutCache() {
        Object2ObjectMapper mapper = new DefaultObject2ObjectMapper();
        SimpleObject o1 = SimpleObject.build();
        List<Long> conversionTimes = new ArrayList<Long>();
        long startTime, stopTime;

        for(long i = 0; i < NUMBER_OF_CONVERSION; i++) {
            startTime = System.nanoTime();
            SimpleObject o2 = mapper.convert(o1, SimpleObject.class, SimpleObject.class);
            stopTime = System.nanoTime();
            conversionTimes.add(stopTime - startTime);
        }

        long total = 0L;
        for(Long l : conversionTimes) {
            total += l;
        }
        long avgConversionTime = total / NUMBER_OF_CONVERSION;

        System.out.println("O2O no cache : Average conversion time : " + avgConversionTime + "ns over " + NUMBER_OF_CONVERSION + " convertions");
    }

    public void testConvertPerformanceO2OWithCache() {
        Object2ObjectMapper mapper = new DefaultObject2ObjectMapper();
        mapper.setObject2ObjectMappingCache(new KeepAllInMemoryObject2ObjectMappingCache());
        SimpleObject o1 = SimpleObject.build();
        List<Long> conversionTimes = new ArrayList<Long>();
        long startTime, stopTime;

        for(long i = 0; i < NUMBER_OF_CONVERSION; i++) {
            startTime = System.nanoTime();
            SimpleObject o2 = mapper.convert(o1, SimpleObject.class, SimpleObject.class);
            stopTime = System.nanoTime();
            conversionTimes.add(stopTime - startTime);
        }

        long total = 0L;
        for(Long l : conversionTimes) {
            total += l;
        }
        long avgConversionTime = total / NUMBER_OF_CONVERSION;

        System.out.println("O2O with cache : Average conversion time : " + avgConversionTime + "ns over " + NUMBER_OF_CONVERSION + " convertions");
    }

}
