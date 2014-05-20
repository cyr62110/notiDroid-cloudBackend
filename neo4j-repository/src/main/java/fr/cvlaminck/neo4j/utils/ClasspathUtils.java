package fr.cvlaminck.neo4j.utils;

/**
 * Define a way to write path between class to avoid some overhead while
 * storing subclasses in the neo4j database.
 */
public class ClasspathUtils {

    /**
     * 'Path' between two classes.
     * <p/>
     * b.A - b.A => .
     * a.B - a.C => .C
     * a.b.c.D - a.b.c.e.F => .e.F
     * a.b.c.d.E - a.b.C => ...C
     *
     * @param baseClass Class that should be used as root of the path.
     * @param implClass Class that we want its path.
     * @param dot       Character that should be used instead of dot in the resulting path
     * @return A path to the implClass using the baseClass as root.
     */
    public static String pathTo(Class<?> baseClass, Class<?> implClass, Character dot) {
        //If we have the same class the path is quite simple
        if (baseClass == implClass)
            return dot.toString();
        final String[] splitBaseClassName = baseClass.getCanonicalName().split("\\.");
        final String[] splitImplClassName = implClass.getCanonicalName().split("\\.");
        final StringBuilder path = new StringBuilder();
        //We must find where the path are branching
        int numberOfSimilarParts = 0;
        while (splitBaseClassName[numberOfSimilarParts].equals(splitImplClassName[numberOfSimilarParts]))
            numberOfSimilarParts++;
        //Then we add all missing part in the impl class name
        for (int i = numberOfSimilarParts; i < splitImplClassName.length; i++) {
            path.append(dot);
            path.append(splitImplClassName[i]);
        }
        return path.toString();
    }

    /**
     * Return the canonical name of the class encoded as path.
     *
     * @param baseClass Base class where is starting this path.
     * @param path      Path of this class using the base class as root.
     * @param dot       Character that has been used instead of dot in the path
     * @return
     */
    public static String canonicalName(Class<?> baseClass, String path, Character dot) {
        //If the path indicate the base class, nothing more to do than returning the base class canonical name.
        if (path == dot.toString())
            return baseClass.getCanonicalName();
        final StringBuilder implCanonicalName = new StringBuilder();
        //Otherwise, we must remove one part at the end of the base class canonical name per dot in the path
        String[] splitBaseClassName = baseClass.getCanonicalName().split("\\.");
        int numberOfParts = splitBaseClassName.length;
        int numberOfTailingDot = 0;
        //We calculate the number of trailing dot
        while (path.charAt(numberOfTailingDot) == dot) numberOfTailingDot++;
        //We copy the number of required parts
        for (int i = 0; i < numberOfParts - numberOfTailingDot; i++) {
            implCanonicalName.append(splitBaseClassName[i]);
            implCanonicalName.append(dot);
        }
        //Finally we append the rest of the path
        implCanonicalName.append(path.substring(numberOfTailingDot));
        return implCanonicalName.toString();
    }

}
