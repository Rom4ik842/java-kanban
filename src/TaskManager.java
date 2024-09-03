import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Класс TaskManager управляет задачами, эпиками и подзадачами.
// Он предоставляет методы для создания, получения, обновления и удаления этих объектов.
public class TaskManager {
    private int currentId = 0;
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    // Создание задачи
    public Task addTask(Task task) {
        task.setId(++currentId);
        tasks.put(task.getId(), task);
        return task;
    }

    // Создание эпика
    public Epic addEpic(Epic epic) {
        epic.setId(++currentId);
        epics.put(epic.getId(), epic);
        return epic;
    }

    // Создание подзадачи
    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(++currentId);
        subtasks.put(subtask.getId(), subtask);
        subtask.getEpic().addSubtask(subtask);
        return subtask;
    }

    // Получение всех задач
    public List<Task> getAllTasks() {
        return List.copyOf(tasks.values());
    }

    // Получение всех эпиков
    public List<Epic> getAllEpics() {
        return List.copyOf(epics.values());
    }

    // Получение всех подзадач
    public List<Subtask> getAllSubtasks() {
        return List.copyOf(subtasks.values());
    }

    // Получение задачи по ID
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    // Получение эпика по ID
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    // Получение подзадачи по ID
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    // Удаление задачи по ID
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    // Удаление эпика по ID (вместе с подзадачами)
    public void deleteEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                subtasks.remove(subtask.getId());
            }
        }
    }

    // Удаление подзадачи по ID
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            subtask.getEpic().removeSubtask(subtask);
        }
    }

    // Удаление всех задач
    public void deleteAllTasks() {
        tasks.clear();
    }

    // Удаление всех подзадач
    public void deleteAllSubtasks() {
        subtasks.clear();
    }

    // Удаление всех эпиков
    public void deleteAllEpics() {
        epics.clear();
    }

    // Обновление задачи
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        } else {
            throw new IllegalArgumentException("Задача с ID " + task.getId() + " не существует.");
        }
    }

    // Обновление эпика
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic); // Заменяем старый эпик на новый
        } else {
            throw new IllegalArgumentException("Эпик с ID " + epic.getId() + " не существует.");
        }
    }

    // Обновление подзадачи
    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
            subtask.getEpic().updateStatus(); // Пересчитываем статус эпика при обновлении подзадачи
        } else {
            throw new IllegalArgumentException("Подзадача с ID " + subtask.getId() + " не существует.");
        }
    }

    // Получение списка подзадач для эпика по ID
    public List<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = getEpicById(epicId);
        if (epic != null) {
            return epic.getSubtasks(); // Возвращаем оригинальный список подзадач
        } else {
            throw new IllegalArgumentException("Эпик с ID " + epicId + " не существует.");
        }
    }
}