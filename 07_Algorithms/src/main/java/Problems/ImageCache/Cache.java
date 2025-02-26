package Problems.ImageCache;

import java.util.*;

public class Cache {
    private final long MAX_SIZE;

    private final Map<String, List<Byte>> map = new LinkedHashMap<>();
    private final Stack<String> stack = new Stack<>();
    private final Queue<String> queue = new LinkedList<>();

    public Cache(long maxSize) {
        this.MAX_SIZE = maxSize;
    }

    public void save(String key, List<Byte> value) {
        if ((key == null || key.isBlank()) ||
                (value == null || value.isEmpty())) return;

        if (map.size() >= MAX_SIZE) {
            // Pop LRU
            //var lruKey = stack.get(0);
            var lruKey = queue.poll();

            map.remove(lruKey);
            //stack.remove(lruKey);
        }

        map.put(key, value);
        //stack.push(key);
        queue.add(key);
    }

    public List<Byte> fetch(String key) {

        var value = map.get(key);

        if (value != null) {
            // Update LRU
            //stack.push(key);
            queue.add(key);
        } else {
            return List.of(new Byte[]{});
        }

        return value;
    }


}
