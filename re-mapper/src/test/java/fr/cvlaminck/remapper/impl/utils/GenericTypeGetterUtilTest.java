package fr.cvlaminck.remapper.impl.utils;

import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GenericTypeGetterUtilTest {

    public ArrayList<String> arrayListOfString = new ArrayList<>();

    @Test
    public void testGetGenericTypeParameter() throws Exception {
        Field f = getClass().getDeclaredField("arrayListOfString");
        //assertEquals("", f.getGenericType());
    }
}