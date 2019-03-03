package com.jpereirax.queue.manager;

import com.jpereirax.queue.model.Queue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QueueManager extends Manager<Queue> {

    @Override
    public <K> Queue getByKey(K k) {
        return elements.stream()
                .filter(it -> it.getPlayer().equals(k))
                .findFirst()
                .orElse(null);
    }

    @Override
    public <V> List<Queue> getByValue(V v) {
        return elements.stream()
                .filter(it -> it.getServer().equals(v))
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
