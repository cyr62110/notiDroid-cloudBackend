package fr.cvlaminck.remapper.impl.mappings.caches;

import fr.cvlaminck.remapper.api.mappings.Object2ObjectMapping;
import fr.cvlaminck.remapper.api.mappings.caches.Object2ObjectMappingCache;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class KeepAllInMemoryObject2ObjectMappingCache
        implements Object2ObjectMappingCache {

    private Map<Entry, Object2ObjectMapping> entries = null;

    public KeepAllInMemoryObject2ObjectMappingCache() {
        this.entries = new HashMap<>();
    }

    @Override
    public Object2ObjectMapping getMapping(Class<?> srcType, Class<?> dstType) {
        return entries.get(new Entry(srcType, dstType));
    }

    @Override
    public void store(Object2ObjectMapping mapping) {
        Entry entry = new Entry(mapping);
        entries.put(entry, mapping);
    }

    private static class Entry {
        private Class<?> srcType;
        private Class<?> dstType;

        private Entry(Object2ObjectMapping mapping) {
            this.srcType = mapping.getSourceType();
            this.dstType = mapping.getDestinationType();
        }

        private Entry(Class<?> srcType, Class<?> dstType) {
            this.srcType = srcType;
            this.dstType = dstType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry entry = (Entry) o;

            if (!dstType.equals(entry.dstType)) return false;
            if (!srcType.equals(entry.srcType)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = srcType.hashCode();
            result = 31 * result + dstType.hashCode();
            return result;
        }
    }

}
