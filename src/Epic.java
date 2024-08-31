import java.util.ArrayList;
import java.util.List;

// Класс Epic наследует базовые свойства и методы от класса Task
// и представляет собой крупную задачу, которая может содержать несколько подзадач (Subtask).
public class Epic extends Task {
    private final List<Subtask> subtasks = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
        this.status = Status.NEW;  // По умолчанию статус эпика - "NEW"
    }

    public List<Subtask> getSubtasks() {
        return List.copyOf(subtasks);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
        updateStatus();  // Обновляем статус эпика
    }

    public void removeSubtask(Subtask subtask) {
        subtasks.remove(subtask);
        updateStatus();
    }

    public void updateStatus() {
        if (subtasks.isEmpty()) {
            this.status = Status.NEW;
            return;
        }

        boolean allDone = true;
        boolean inProgress = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                inProgress = true;
            }
            if (subtask.getStatus() != Status.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            this.status = Status.DONE;
        } else if (inProgress) {
            this.status = Status.IN_PROGRESS;
        } else {
            this.status = Status.NEW;
        }
    }
}

