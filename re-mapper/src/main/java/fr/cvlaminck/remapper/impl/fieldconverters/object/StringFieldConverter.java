package fr.cvlaminck.remapper.impl.fieldconverters.object;

import fr.cvlaminck.remapper.impl.fieldconverters.BasicFieldConverter;

public class StringFieldConverter
    extends BasicFieldConverter {

    public StringFieldConverter() {
        super(String.class);
    }

    @Override
    protected Object deepCopy(Object srcFieldValue) {
        return new String((String) srcFieldValue);
    }
}
