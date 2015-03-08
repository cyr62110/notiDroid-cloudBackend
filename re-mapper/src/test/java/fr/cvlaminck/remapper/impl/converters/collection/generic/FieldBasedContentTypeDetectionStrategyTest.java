package fr.cvlaminck.remapper.impl.converters.collection.generic;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

public class FieldBasedContentTypeDetectionStrategyTest {

    private ArrayList<String> arrayListOfString = new ArrayList<String>();
    private CustomArrayList customArrayList = new CustomArrayList();
    private SuperclassCustomArrayList superclassCustomArrayList = new SuperclassCustomArrayList();
    private CollectionOfStringImplementation collectionOfStringImplementation = new CollectionOfStringImplementation();
    private CustomCollectionImplementation customCollectionImplementation = new CustomCollectionImplementation();

    private FieldBasedContentTypeDetectionStrategy contentTypeDetectionStrategy = null;

    @Before
    public void setUp() throws Exception {
        contentTypeDetectionStrategy = new FieldBasedContentTypeDetectionStrategy();
    }

    @Test
    public void testGetContentTypeWithTypeParametrizedOnField() throws Exception {
        Field field = getClass().getDeclaredField("arrayListOfString");
        assertEquals(String.class, contentTypeDetectionStrategy.getContentType(field));
    }

    @Test
    public void testGetContentTypeWithTypeParametrizedOnClass() throws Exception {
        Field field = getClass().getDeclaredField("customArrayList");
        assertEquals(String.class, contentTypeDetectionStrategy.getContentType(field));
    }

    @Test
    public void testGetContentTypeWithTypeParametrizedOnInheritedClass() throws Exception {
        Field field = getClass().getDeclaredField("superclassCustomArrayList");
        assertEquals(String.class, contentTypeDetectionStrategy.getContentType(field));
    }

    @Test
    public void testGetContentTypeWithTypeParametrizedOnInterface() throws Exception {
        Field field = getClass().getDeclaredField("collectionOfStringImplementation");
        assertEquals(String.class, contentTypeDetectionStrategy.getContentType(field));
    }

    @Test
    public void testGetContentTypeWithTypeParametrizedOnSuperInterface() throws Exception {
        Field field = getClass().getDeclaredField("customCollectionImplementation");
        assertEquals(String.class, contentTypeDetectionStrategy.getContentType(field));
    }

    private static class CustomArrayList extends ArrayList<String> {
    }

    private static class SuperclassCustomArrayList extends CustomArrayList {
    }

    private static class CollectionOfStringImplementation implements Collection<String> {
        public int size() { throw new UnsupportedOperationException(); }
        public boolean isEmpty() { throw new UnsupportedOperationException(); }
        public boolean contains(Object o) { throw new UnsupportedOperationException(); }
        public Iterator<String> iterator() { throw new UnsupportedOperationException(); }
        public Object[] toArray() { throw new UnsupportedOperationException(); }
        public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }
        public boolean add(String s) { throw new UnsupportedOperationException(); }
        public boolean remove(Object o) { throw new UnsupportedOperationException(); }
        public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        public boolean addAll(Collection<? extends String> c) { throw new UnsupportedOperationException(); }
        public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        public void clear() { throw new UnsupportedOperationException(); }
    }

    public interface CustomCollection extends Collection<String> {
    }

    private static class CustomCollectionImplementation implements CustomCollection {
        public int size() { throw new UnsupportedOperationException(); }
        public boolean isEmpty() { throw new UnsupportedOperationException(); }
        public boolean contains(Object o) { throw new UnsupportedOperationException(); }
        public Iterator<String> iterator() { throw new UnsupportedOperationException(); }
        public Object[] toArray() { throw new UnsupportedOperationException(); }
        public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }
        public boolean add(String s) { throw new UnsupportedOperationException(); }
        public boolean remove(Object o) { throw new UnsupportedOperationException(); }
        public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        public boolean addAll(Collection<? extends String> c) { throw new UnsupportedOperationException(); }
        public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
        public void clear() { throw new UnsupportedOperationException(); }
    }
}