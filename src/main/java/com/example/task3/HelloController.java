package com.example.task3;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.layout.Pane;

import java.util.Arrays;

public class HelloController {
    @FXML
    private Pane panel; // Панель для рисования

    private Memento temp; // Временное состояние фигуры
    private double initialX; // Начальная координата X
    private double initialY; // Начальная координата Y

    @FXML
    public void initialize() {
        // Инициализация фигур с корректными координатами
        Rectangle rectangle1 = new Rectangle(100, 100, 40, 70);
        rectangle1.setFill(Color.BLUE);

        Rectangle rectangle2 = new Rectangle(300, 150, 100, 50);
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setArcWidth(10);

        Circle circle1 = new Circle(200, 250, 40);
        circle1.setFill(Color.ORANGE);

        Circle circle2 = new Circle(300, 300, 40, Color.YELLOW);
        circle2.setStroke(Color.BLACK);
        circle2.setStrokeWidth(2.0);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(50.0, 0.0, 0.0, 50.0, 100.0, 50.0);
        triangle.setFill(Color.GREEN);
        triangle.setStroke(Color.RED);
        triangle.setLayoutX(50);
        triangle.setLayoutY(350);

        // Добавление фигур на панель
        panel.getChildren().addAll(rectangle1, rectangle2, circle1, circle2, triangle);

        // Добавляем обработчики событий для каждой фигуры
        for (Shape shape : Arrays.asList(rectangle1, rectangle2, circle1, circle2, triangle)) {
            // Обработчик для захвата фигуры
            shape.addEventHandler(MouseEvent.MOUSE_PRESSED, this::onBegin);

            // Обработчики для перетаскивания
            shape.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onDrag);
            shape.addEventHandler(MouseEvent.MOUSE_RELEASED, this::onEnd);
        }
    }

    // Метод для визуального выделения фигуры (изменение свойств)
    public void onBegin(MouseEvent mouseEvent) {
        // Захват фигуры, по которой кликнули
        Shape selectedShape = (Shape) mouseEvent.getSource();

        // Сохраняем начальные свойства фигуры
        temp = new Memento(selectedShape);

        // Сохраняем начальные координаты
        initialX = mouseEvent.getX();
        initialY = mouseEvent.getY();

        // Перемещаем фигуру на передний план
        selectedShape.toFront();

        // Визуальное выделение фигуры (изменение цвета и толщины обводки)
        initialize(selectedShape);
    }

    // Метод для перетаскивания фигуры
    public void onDrag(MouseEvent mouseEvent) {
        if (temp == null) return;

        // Получаем фигуру из Memento
        Shape selectedShape = temp.getShape();

        // Вычисляем смещение
        double offsetX = mouseEvent.getX() - initialX;
        double offsetY = mouseEvent.getY() - initialY;

        // Перемещаем фигуру по новым координатам
        selectedShape.setLayoutX(selectedShape.getLayoutX() + offsetX);
        selectedShape.setLayoutY(selectedShape.getLayoutY() + offsetY);

        // Обновляем начальные координаты для следующего перемещения
        initialX = mouseEvent.getX();
        initialY = mouseEvent.getY();
    }

    // Метод для завершения перетаскивания
    public void onEnd(MouseEvent mouseEvent) {
        if (temp == null) return;

        // Восстанавливаем исходные параметры фигуры из Memento
        temp.getState();

        // Сбрасываем временное состояние
        temp = null;
    }

    // Метод для визуального выделения фигуры (изменение свойств)
    private void initialize(Shape shape) {
        shape.setStrokeWidth(2); // Увеличиваем толщину обводки
        shape.setStroke(Color.RED); // Меняем цвет обводки на красный
    }
}


