import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Subtask> subtasks = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
        this.status = Status.NEW;  // По умолчанию статус эпика - "NEW"
    }

    public List<Subtask> getSubtasks() {
        return subtasks; // Возвращаем оригинальный список
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
        boolean hasInProgress = false;
        boolean hasNew = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == Status.IN_PROGRESS) {
                hasInProgress = true;
            }
            if (subtask.getStatus() == Status.NEW) {
                hasNew = true;
            }
            if (subtask.getStatus() != Status.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            this.status = Status.DONE;
        } else if (hasInProgress || (hasNew && !allDone)) {
            this.status = Status.IN_PROGRESS;
        } else {
            this.status = Status.NEW;
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