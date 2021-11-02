import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Key {
    private final byte[] data;

    public Key(byte[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        return Arrays.equals(data, key.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}

//Еще нужно предложить реализацию без наследования
public abstract class Digest {
    private final Map<Key, byte[]> cache = new ConcurrentHashMap<>();

    public final byte[] digest(byte[] input) {
        Key key = new Key(input);
        byte[] result = cache.get(key);
        if (result == null) {
            result = calcDigest(input);
            cache.put(key, result);
        }
        return result;
    }

    abstract protected byte[] calcDigest(byte[] input);
}

//Изначальный вариант
public abstract class Digest {
    private final Map<byte[], byte[]> cache = new HashMap<>();

    public byte[] digest(byte[] input) {
        byte[] result = cache.get(input);
        if (result == null) {
            synchronized (cache) {
                result = cache.get(input);
                if (result == null) {
                    result = calcDigest(input);
                    cache.put(input, result);
                }
            }
        }
        return result;
    }

    abstract protected byte[] calcDigest(byte[] input);
}
