package com.yandex.app.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Subtask> subtasks;

    public Epic(String name, String description) {
        super(name, description, Status.NEW);
        this.subtasks = new ArrayList<>();
    }

    public void addSubtask(Subtask subtask) {
        // Устанавливаем текущий эпик для подзадачи, если это не тот же самый эпик
        if (subtask.getEpic() == null) {
            subtask.setEpic(this); // Устанавливаем текущий эпик для подзадачи
            subtasks.add(subtask);
            updateStatus(); // Обновляем статус эпика после добавления подзадачи
        } else if (subtask.getEpic() != this) {
            throw new IllegalArgumentException("Подзадача не может быть добавлена сама по себе.");
        }
    }

    public void removeSubtask(int subtaskId) {
        // Удаляем подзадачу по ее ID
        subtasks.removeIf(subtask -> subtask.getId() == subtaskId);
        updateStatus(); // Обновляем статус эпика после удаления подзадачи
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks); // Возвращаем копию списка подзадач
    }

    public void updateStatus() {
        // Обновление статуса эпика в зависимости от статусов подзадач
        if (subtasks.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() != Status.DONE) {
                allDone = false;
            }
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                anyInProgress = true;
            }
        }

        if (allDone) {
            setStatus(Status.DONE);
        } else if (anyInProgress) {
            setStatus(Status.IN_PROGRESS);
        } else {
            setStatus(Status.NEW);
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtasks=" + subtasks +
                '}';
    }
}
