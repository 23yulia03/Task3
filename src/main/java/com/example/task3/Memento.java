package com.example.task3;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Memento {
    private final Shape shape; // Фигура, состояние которой мы сохраняем
    private final double strokeWidth; // Ширина обводки
    private final Color strokeColor; // Цвет обводки

    // Конструктор для создания снимка (Memento) состояния фигуры
    public Memento(Shape state) {
        this.shape = state; // принимает объект shape
        this.strokeWidth = state.getStrokeWidth(); // сохраняет текущее состояние фигуры
        this.strokeColor = (Color) state.getStroke();
    }

    // Восстановить первоначальные свойства фигуры
    public void getState() {
        shape.setStrokeWidth(strokeWidth); // устанавливает сохраненные значения
        shape.setStroke(strokeColor);
    }

    // Вернуть сохраненную фигуру из объекта Memento
    public Shape getShape() {
        return shape;
    }
}
