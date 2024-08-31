public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание задач
        Task task1 = new Task("Переезд", "Собрать коробки, упаковать кошку, сказать слова прощания");
        manager.addTask(task1);

        // Создание эпика и подзадач
        Epic epic1 = new Epic("Важный эпик 2", "Описание эпика 2");
        manager.addEpic(epic1);

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

        // Изменение статусов задач
        subtask1.setStatus(Status.IN_PROGRESS);
        subtask2.setStatus(Status.DONE);

        // Обновление задач в менеджере
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateEpic(epic1);

        // Вывод обновленных данных
        System.out.println("Обновленные задачи и эпики:");
        System.out.println(subtask1);
        System.out.println(subtask2);
        System.out.println(manager.getEpicById(epic1.getId()));
    }
}