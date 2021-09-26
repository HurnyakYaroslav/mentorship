package com.epam.mentorship.multithreading.task1;

import java.util.*;
import java.util.stream.Collectors;

public class CustomSynchronizedThreadSafeMap<K, V> implements Map<K, V> {

    private final Set<Map.Entry<K, V>> entrySet = new HashSet<>();

    @Override
    public synchronized int size() {
        Arrays.asList();
        List<String> immutableList = List.of("");
        return entrySet.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return entrySet.isEmpty();
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return entrySet.stream()
                .map(Map.Entry::getKey)
                .anyMatch(setKey -> setKey.equals(key));
    }

    @Override
    public synchronized boolean containsValue(Object value) {
        return entrySet.stream()
                .map(Map.Entry::getValue)
                .anyMatch(setValue -> setValue.equals(value));
    }

    @Override
    public synchronized V get(Object key) {
        Optional<Map.Entry<K,V>> entry = entrySet.stream()
                .filter(setEntry -> setEntry.getKey().equals(key))
                .findFirst();
        return entry.map(Map.Entry::getValue).orElse(null);
    }

    @Override
    public synchronized V put(K key, V value) {
        entrySet.add(new Entry<>(key, value));
        return value;
    }

    @Override
    public synchronized V remove(Object key) {
        entrySet.remove(key);
        return null;
    }

    @Override
    public synchronized void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized Set<K> keySet() {
        return entrySet.stream().map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    @Override
    public synchronized Collection<V> values() {
        return entrySet.stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public synchronized Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final V setValue(V newValue) {
            this.value = newValue;
            return newValue;
        }
    }
}
