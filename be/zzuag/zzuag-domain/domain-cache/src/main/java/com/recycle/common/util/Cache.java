package com.recycle.common.util;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Cache<K, V> {
    private final Map<K, CacheData<V>> storage;
    private final int capacity;
    private final Deque<K> orderQueue;
    private static final long EXPIRATION_TIME = 1000 * 60 * 5;
    private static class CacheData<V>{
        private final V value;
        private final long createdAt;

        CacheData(V value) {
            this.value = value;
            this.createdAt = System.currentTimeMillis();
        }

        boolean isExpired() {
            return (System.currentTimeMillis() - createdAt) > EXPIRATION_TIME;
        }

        V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof CacheData<?>)) return false;

            return value.equals(((CacheData<?>) obj).getValue());
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }

    public Cache(int capacity) {
        this.capacity = capacity;
        this.storage = new ConcurrentHashMap<K,CacheData<V>>();
        this.orderQueue = new ConcurrentLinkedDeque<K>();
    }

    public synchronized void put(K key, V value) {
        if(storage.containsKey(key)) {
            orderQueue.remove(key);
        }

        storage.put(key, new CacheData<>(value));
        orderQueue.addLast(key);


        if(storage.size() > capacity) {
            K oldKey = orderQueue.pollFirst();
            if(oldKey != null) {
                storage.remove(oldKey);
            }
        }
    }

    public V get(K key) {
        synchronized (this) {
            CacheData<V> cacheData = storage.get(key);
            if (storage.containsKey(key) && !cacheData.isExpired()) {
                orderQueue.remove(key);
                orderQueue.addLast(key);
                return cacheData.value;
            }

            storage.remove(key);
            orderQueue.remove(key);
            return null;
        }
    }

    public void remove(K key) {
        synchronized (this) {
            storage.remove(key);
            orderQueue.remove(key);
        }
    }

    public void clear() {
        synchronized (this) {
            storage.clear();
            orderQueue.clear();
        }
    }

    public boolean containsKey(K key) {
        return storage.containsKey(key);
    }

    public boolean containsValue(V value) {
        return storage.containsValue(value);
    }
}

