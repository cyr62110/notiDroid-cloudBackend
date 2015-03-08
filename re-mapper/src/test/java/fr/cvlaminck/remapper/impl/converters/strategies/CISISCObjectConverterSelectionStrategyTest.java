package fr.cvlaminck.remapper.impl.converters.strategies;

import fr.cvlaminck.remapper.api.converters.ObjectConverter;
import fr.cvlaminck.remapper.api.converters.containers.ObjectConvertersContainer;
import fr.cvlaminck.remapper.api.converters.strategies.ObjectConverterSelectionStrategy;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CISISCObjectConverterSelectionStrategyTest {

    @Test
    public void testGetConverterFromSelectClassFirst() throws Exception {
        ObjectConverter arrayListConverter = mock(ObjectConverter.class);
        when(arrayListConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter listConverter = mock(ObjectConverter.class);
        when(listConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter collectionConverter = mock(ObjectConverter.class);
        when(collectionConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        when(abstractListConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter objectConverter = mock(ObjectConverter.class);
        when(objectConverter.supports(ArrayList.class, Object.class)).thenReturn(true);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverters(ArrayList.class)).thenReturn(Collections.singletonList(arrayListConverter));
        when(container.getConverters(List.class)).thenReturn(Collections.singletonList(listConverter));
        when(container.getConverters(Collection.class)).thenReturn(Collections.singletonList(collectionConverter));
        when(container.getConverters(AbstractList.class)).thenReturn(Collections.singletonList(abstractListConverter));
        when(container.getConverters(Object.class)).thenReturn(Collections.singletonList(objectConverter));

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(arrayListConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromSelectInterfaceSecond() throws Exception {
        ObjectConverter listConverter = mock(ObjectConverter.class);
        when(listConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter collectionConverter = mock(ObjectConverter.class);
        when(collectionConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        when(abstractListConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter objectConverter = mock(ObjectConverter.class);
        when(objectConverter.supports(ArrayList.class, Object.class)).thenReturn(true);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverters(List.class)).thenReturn(Collections.singletonList(listConverter));
        when(container.getConverters(Collection.class)).thenReturn(Collections.singletonList(collectionConverter));
        when(container.getConverters(AbstractList.class)).thenReturn(Collections.singletonList(abstractListConverter));
        when(container.getConverters(Object.class)).thenReturn(Collections.singletonList(objectConverter));

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(listConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromSelectSuperInterfaceThird() throws Exception {
        ObjectConverter collectionConverter = mock(ObjectConverter.class);
        when(collectionConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        when(abstractListConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter objectConverter = mock(ObjectConverter.class);
        when(objectConverter.supports(ArrayList.class, Object.class)).thenReturn(true);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverters(Collection.class)).thenReturn(Collections.singletonList(collectionConverter));
        when(container.getConverters(AbstractList.class)).thenReturn(Collections.singletonList(abstractListConverter));
        when(container.getConverters(Object.class)).thenReturn(Collections.singletonList(objectConverter));

        ObjectConverterSelectionStrategy selectionStrategy = new CISISCObjectConverterSelectionStrategy();

        assertSame(collectionConverter, selectionStrategy.getConverterFrom(container, ArrayList.class, Object.class));
    }

    @Test
    public void testGetConverterFromSelectSuperClassFinally() throws Exception {
        ObjectConverter abstractListConverter = mock(ObjectConverter.class);
        when(abstractListConverter.supports(ArrayList.class, Object.class)).thenReturn(true);
        ObjectConverter objectConverter = mock(ObjectConverter.class);
        when(objectConverter.supports(ArrayList.class, Object.class)).thenReturn(true);

        ObjectConvertersContainer container = mock(ObjectConvertersContainer.class);
        when(container.getConverters(AbstractList.class)).thenReturn(Collections.singletonList(abstractListConverter));
        when(container.getConverters(Object.class)).thenReturn(Collections.singletonList(objectConverter));

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