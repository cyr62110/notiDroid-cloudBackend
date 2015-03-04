package fr.cvlaminck.remapper.impl.utils;

/**
 *
 */
public class GenericTypeGetterUtil<T> {

    private T instanceOfType;

    public GenericTypeGetterUtil(T instanceOfType) {
        this.instanceOfType = instanceOfType;
    }

    public Class<?> getGenericTypeParameter() {
        return null; //TODO
    }

}
