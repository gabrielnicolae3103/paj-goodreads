package com.paj.goodreads.Notification;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

public class NotificationSystem implements Runnable {
    private Consumer<Void> consumer;

    public NotificationSystem(Consumer<Void> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        consumer.accept(null);
    }
}
