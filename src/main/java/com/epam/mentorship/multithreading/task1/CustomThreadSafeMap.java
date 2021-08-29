package com.epam.mentorship.multithreading.task1;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CustomThreadSafeMap<K, V> implements Map<K, V> {

    private final Set<Map.Entry<K, V>> entrySet = new HashSet<>();
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public int size() {
        lock.lock();
        int size = entrySet.size();
        lock.unlock();
        return size;
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        boolean empty = entrySet.isEmpty();
        lock.unlock();
        return empty;
    }

    @Override
    public boolean containsKey(Object key) {
        lock.lock();
        boolean anyMatch = entrySet.stream()
                .map(Map.Entry::getKey)
                .anyMatch(setKey -> setKey.equals(key));
        lock.unlock();
        return anyMatch;
    }

    @Override
    public boolean containsValue(Object value) {
        lock.lock();
        boolean anyMatch = entrySet.stream()
                .map(Map.Entry::getValue)
                .anyMatch(setValue -> setValue.equals(value));
        lock.unlock();
        return anyMatch;
    }

    @Override
    public V get(Object key) {
        lock.lock();
        Optional<Map.Entry<K, V>> entry = entrySet.stream()
                .filter(setEntry -> setEntry.getKey().equals(key))
                .findFirst();
        lock.unlock();
        return entry.map(Map.Entry::getValue).orElse(null);

    }

    @Override
    public V put(K key, V value) {
        lock.lock();
        entrySet.add(new Entry<>(key, value));
        lock.unlock();
        return value;
    }

    @Override
    public V remove(Object key) {
        lock.lock();
        entrySet.remove(key);
        lock.unlock();
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
    public Set<K> keySet() {
        lock.lock();
        Set<K> keySet = entrySet.stream().map(Map.Entry::getKey).collect(Collectors.toSet());
        lock.unlock();
        return keySet;
    }

    @Override
    public  Collection<V> values() {
        lock.lock();
        List<V> valueList = entrySet.stream().map(Map.Entry::getValue).collect(Collectors.toList());
        lock.unlock();
        return valueList;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
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
