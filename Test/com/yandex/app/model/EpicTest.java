package com.yandex.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    private Epic epic1;
    private Epic epic2;

    @BeforeEach
    void setUp() {
        epic1 = new Epic("Эпик 1", "Описание 1");
        epic2 = new Epic("Эпик 2", "Описание 2");
    }

    @Test
    void testEquals() {
        epic1.setId(1);
        epic2.setId(1);
        assertEquals(epic1, epic2);
    }

    @Test
    void testNotEquals() {
        epic1.setId(1);
        epic2.setId(2);
        assertNotEquals(epic1, epic2);
    }

    @Test
    void testAddSubtaskToSelf() {
        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic1);
        assertThrows(IllegalArgumentException.class, () -> {
            epic1.addSubtask(subtask);
        });
    }

    @Test
    void testRemoveSubtaskUpdatesEpic() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        Subtask subtask = new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic);
        epic.addSubtask(subtask);

        subtask.setId(1); // Устанавливаем ID для подзадачи
        epic.removeSubtask(subtask.getId()); // Удаляем подзадачу по ее ID

        // Проверяем, что в списке подзадач эпика больше нет ID удаленной подзадачи
        assertFalse(epic.getSubtasks().contains(subtask.getId()),
                "Epic should not contain the removed subtask ID.");
    }

}
