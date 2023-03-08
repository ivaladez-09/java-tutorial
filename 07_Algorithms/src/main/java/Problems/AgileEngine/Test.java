package Problems.AgileEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {

    }

    public static Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> map = new HashMap<>();

        for (Map<String, UserStats> visit: visits) {
            visit.entrySet().stream()
                    .filter(entry -> {
                        try {
                            Long.valueOf(entry.getKey());
                            return true;
                        }
                        catch (Exception e) {
                            return false;
                        }
                    })
                    .filter(entry -> entry.getValue() != null)
                    .filter(entry -> entry.getValue().getVisitCount().isPresent())
                    .map(entry -> {
                        Long userId = Long.valueOf(entry.getKey());
                        Long count = entry.getValue().getVisitCount().get();
                        map.put(userId, count);
                        return 0;
                    });
        }

        return map;
    }
}

class UserStats {
    public UserStats(Long count) {
        this.visitCount = Optional.ofNullable(count);
    }

    public UserStats() {
        this.visitCount = Optional.empty();
    }

    private final Optional<Long> visitCount;

    public Optional<Long> getVisitCount() {
        return visitCount;
    }
}
