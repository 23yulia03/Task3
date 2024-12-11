package com.example.task3;

import java.util.Queue;
import java.util.ArrayDeque;

public class CareTaker {
    // Очередь для хранения объектов Memento (состояний фигур)
    private final Queue<Memento> mementoList = new ArrayDeque<>();

    // Добавить состояние фигуры в очередь
    public void push(Memento state) {
        mementoList.add(state);
    }

    // Извлечь последнее сохраненное состояние фигуры из очереди
    public Memento poll() {
        return mementoList.poll();
    }
}
