package com.example.rebrain.util;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalUserIdHolder {
    private static final ThreadLocal<Integer> USER_ID = ThreadLocal.withInitial(() -> 1);

    public static Integer get() {
        return USER_ID.get();
    }

    public static void set(Integer value) {
        USER_ID.set(value);
    }

    public static void remove() {
        USER_ID.remove();
    }
}

