// Класс Subtask наследует базовые свойства и методы от класса Task
// и представляет собой подзадачу, связанную с определенным Epic.
public class Subtask extends Task {
    private Epic epic; // Исправлено: вместо идентификатора эпика будет объект Epic

    public Subtask(String title, String description, Epic epic) {
        super(title, description);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", epic=" + epic.getTitle() + // Пример использования
                '}';
    }
}