package com.yandex.app.service;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Status;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = Managers.getDefault();
    }

    @Test
    void testAddAndGetTasks() {
        Task task1 = new Task("Задача 1", "Описание 1", Status.NEW);
        Task task2 = new Task("Задача 2", "Описание 2", Status.NEW);

        manager.addTask(task1);
        manager.addTask(task2);

        List<Task> tasks = manager.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    void testAddAndGetEpics() {
        Epic epic1 = new Epic("Эпик 1", "Описание 1");
        Epic epic2 = new Epic("Эпик 2", "Описание 2");

        manager.addEpic(epic1);
        manager.addEpic(epic2);

        List<Epic> epics = manager.getAllEpics();
        assertEquals(2, epics.size());
        assertTrue(epics.contains(epic1));
        assertTrue(epics.contains(epic2));
    }

    @Test
    void testAddAndGetSubtasks() {
        Epic epic1 = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", Status.NEW, epic1);

        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        List<Subtask> subtasks = manager.getAllSubtasks();
        assertEquals(2, subtasks.size());
        assertTrue(subtasks.contains(subtask1));
        assertTrue(subtasks.contains(subtask2));
    }

    @Test
    void testRemoveSubtaskUpdatesEpic() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);
        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic);
        manager.addSubtask(subtask);

        manager.removeSubtask(subtask.getId());

        List<Subtask> subtasks = manager.getAllSubtasks();
        assertEquals(0, subtasks.size());

        List<Epic> epics = manager.getAllEpics();
        assertEquals(Status.NEW, epic.getStatus());
    }

    @Test
    void testEpicStatusUpdate() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", Status.DONE, epic);

        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        assertEquals(Status.IN_PROGRESS, epic.getStatus());

        manager.removeSubtask(subtask1.getId());
        assertEquals(Status.DONE, epic.getStatus());
    }

    @Test
    void testEpicWithoutSubtasksIsNew() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);

        assertEquals(Status.NEW, epic.getStatus());
    }

    @Test
    void testGetEpicById() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);

        Epic fetchedEpic = manager.getEpicById(epic.getId());
        assertEquals(epic, fetchedEpic);
    }

    @Test
    void testSubtaskDoesNotRetainOldIdAfterRemoval() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic);
        manager.addSubtask(subtask);

        int removedId = subtask.getId();
        manager.removeSubtask(removedId);

        // Проверяем, что подзадача не хранит старый ID
        assertThrows(IllegalArgumentException.class, () -> manager.getSubtaskById(removedId));
    }

    @Test
    void testEpicDoesNotRetainOldSubtaskIds() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic);
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание 2", Status.NEW, epic);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        manager.removeSubtask(subtask1.getId());

        // Проверяем, что эпик не содержит ID удалённой подзадачи
        assertFalse(epic.getSubtasks().contains(subtask1));
    }

    @Test
    void testTaskFieldUpdateAffectsManagerData() {
        Task task = new Task("Задача 1", "Описание 1", Status.NEW);
        manager.addTask(task);

        // Изменяем название задачи
        task.setTitle("Новая Задача");

        Task updatedTask = manager.getTaskById(task.getId());
        assertEquals("Новая Задача", updatedTask.getTitle());
    }

    @Test
    void testSubtaskFieldUpdateAffectsEpic() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        manager.addEpic(epic);

        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic);
        manager.addSubtask(subtask);

        // Изменяем статус подзадачи
        subtask.setStatus(Status.DONE);

        manager.removeSubtask(subtask.getId());

        // Проверяем, что статус эпика обновился
        assertEquals(Status.NEW, epic.getStatus());
    }
}
