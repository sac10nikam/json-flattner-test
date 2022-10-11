package com.json.evaluatepath.jsonflattener;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import com.github.wnameless.json.flattener.JsonFlattener;

public class JsonFlattenerImpl {

    public void apply(String mockJson, String resourcePath) {

        //flatten json
        Map<String, Object> flattenJson = JsonFlattener.flattenAsMap(mockJson);
        flattenJson.values().removeIf(Objects::isNull);
        ConcurrentMap<String, Object> concurrentMap = new ConcurrentHashMap<>(flattenJson);

        List<String> matchingKeys = concurrentMap.keySet().stream()
                .filter(e -> e.contains(resourcePath)).collect(Collectors.toList());

        matchingKeys.stream().forEach(key -> {
            System.out.println("resourcePath - key=" + key + ", value=" + concurrentMap.get(key));
        });
    }
}
