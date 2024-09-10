package com.yandex.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void testEquals() {
        Epic epic1 = new Epic("Эпик 1", "Описание 1");
        Epic epic2 = new Epic("Эпик 2", "Описание 2");
        epic1.setId(1);
        epic2.setId(1);

        assertEquals(epic1, epic2);
    }

    @Test
    void testNotEquals() {
        Epic epic1 = new Epic("Эпик 1", "Описание 1");
        Epic epic2 = new Epic("Эпик 2", "Описание 2");
        epic1.setId(1);
        epic2.setId(2);

        assertNotEquals(epic1, epic2);
    }

    @Test
    void testAddSubtaskToSelf() {
        Epic epic = new Epic("Эпик 1", "Описание 1");
        assertThrows(IllegalArgumentException.class, () -> epic.addSubtask(new Subtask("Подзадача 1", "Описание 1", Status.NEW, epic)));
    }
}