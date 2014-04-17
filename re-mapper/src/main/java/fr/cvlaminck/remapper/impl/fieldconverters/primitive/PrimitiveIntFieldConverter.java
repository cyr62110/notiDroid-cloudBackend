package fr.cvlaminck.remapper.impl.fieldconverters.primitive;

import fr.cvlaminck.remapper.impl.fieldconverters.BasicFieldConverter;

public class PrimitiveIntFieldConverter
    extends BasicFieldConverter {

    public PrimitiveIntFieldConverter() {
        super(int.class);
    }

    @Override
    protected Object deepCopy(Object srcFieldValue) {
        return srcFieldValue;
    }

}
