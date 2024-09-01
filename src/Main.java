public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание задач
        Task task1 = new Task("Переезд", "Собрать коробки, упаковать кошку, сказать слова прощания");
        manager.addTask(task1);

        // Создание эпика и подзадач
        Epic epic1 = new Epic("Важный эпик 2", "Описание эпика 2");
        manager.addEpic(epic1);

        // Передаем объект Epic, а не его ID
        Subtask subtask1 = new Subtask("Задача 1", "Описание задачи 1", epic1);
        Subtask subtask2 = new Subtask("Задача 2", "Описание задачи 2", epic1);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        // Получение и вывод всех задач, эпиков и подзадач
        System.out.println("Список всех задач:");
        System.out.println(manager.getAllTasks());

        System.out.println("Список всех эпиков:");
        System.out.println(manager.getAllEpics());

        System.out.println("Список всех подзадач:");
        System.out.println(manager.getAllSubtasks());

        // Изменение заголовков, описаний и статусов задач
        subtask1.setStatus(Status.IN_PROGRESS);
        subtask1.setTitle("Обновленная задача 1");
        subtask1.setDescription("Обновленное описание задачи 1");

        subtask2.setStatus(Status.DONE);
        subtask2.setTitle("Обновленная задача 2");
        subtask2.setDescription("Обновленное описание задачи 2");

        // Обновление задач в менеджере
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);

        // Вывод обновленных данных
        System.out.println("Обновленные задачи и эпики:");
        System.out.println(subtask1);
        System.out.println(subtask2);
        System.out.println(manager.getEpicById(epic1.getId()));

        // Получение списка подзадач эпика
        System.out.println("Список подзадач эпика с ID " + epic1.getId() + ":");
        System.out.println(manager.getSubtasksOfEpic(epic1.getId()));

        // Удаление подзадачи, задачи и эпика по ID
        manager.deleteSubtaskById(subtask1.getId());
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic1.getId());

        // Проверка удаления
        System.out.println("После удаления:");
        System.out.println("Список всех задач:");
        System.out.println(manager.getAllTasks());

        System.out.println("Список всех эпиков:");
        System.out.println(manager.getAllEpics());

        System.out.println("Список всех подзадач:");
        System.out.println(manager.getAllSubtasks());

        // Удаление всех задач
        manager.deleteAllTasks();
        manager.deleteAllEpics();
        manager.deleteAllSubtasks();

        // Проверка удаления всех задач
        System.out.println("После удаления всех задач:");
        System.out.println("Список всех задач:");
        System.out.println(manager.getAllTasks());

        System.out.println("Список всех эпиков:");
        System.out.println(manager.getAllEpics());

        System.out.println("Список всех подзадач:");
        System.out.println(manager.getAllSubtasks());
    }
}