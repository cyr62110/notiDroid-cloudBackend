package fr.cvlaminck.remapper.impl.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CISISCObjectConverterSelectionStrategyTest {

    @Test
    public void testGetConverterFromSelectClassFirst() throws Exception {
        ObjectConverter arrayListConverter = mock(ObjectConverter.class);
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        ObjectConverter listConverter = mock(ObjectConverter.class);
        ObjectConverter collectionConverter = mock(ObjectConverter.class);
        ObjectConverter objectConverter = mock(ObjectConverter.class);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverterFor(ArrayList.class, Object.class)).thenReturn(arrayListConverter);
        when(container.getConverterFor(List.class, Object.class)).thenReturn(listConverter);
        when(container.getConverterFor(Collection.class, Object.class)).thenReturn(collectionConverter);
        when(container.getConverterFor(AbstractList.class, Object.class)).thenReturn(abstractListConverter);
        when(container.getConverterFor(Object.class, Object.class)).thenReturn(objectConverter);

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(arrayListConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromSelectInterfaceSecond() throws Exception {
        ObjectConverter listConverter = mock(ObjectConverter.class);
        ObjectConverter collectionConverter = mock(ObjectConverter.class);
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        ObjectConverter objectConverter = mock(ObjectConverter.class);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverterFor(List.class, Object.class)).thenReturn(listConverter);
        when(container.getConverterFor(Collection.class, Object.class)).thenReturn(collectionConverter);
        when(container.getConverterFor(AbstractList.class, Object.class)).thenReturn(abstractListConverter);
        when(container.getConverterFor(Object.class, Object.class)).thenReturn(objectConverter);

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(listConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromSelectSuperInterfaceThird() throws Exception {
        ObjectConverter collectionConverter = mock(ObjectConverter.class);
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        ObjectConverter objectConverter = mock(ObjectConverter.class);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverterFor(Collection.class, Object.class)).thenReturn(collectionConverter);
        when(container.getConverterFor(AbstractList.class, Object.class)).thenReturn(abstractListConverter);
        when(container.getConverterFor(Object.class, Object.class)).thenReturn(objectConverter);

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(collectionConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromSelectSuperClassFinally() throws Exception {
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        ObjectConverter objectConverter = mock(ObjectConverter.class);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverterFor(AbstractList.class, Object.class)).thenReturn(abstractListConverter);
        when(container.getConverterFor(Object.class, Object.class)).thenReturn(objectConverter);

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(abstractListConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromReturnsNullOtherwise() throws Exception {
        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertNull(selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

}