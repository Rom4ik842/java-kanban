public class Subtask extends Task {
    private Epic epic; // Связь с эпиком

    // Конструктор по умолчанию с статусом NEW
    public Subtask(String title, String description, Epic epic) {
        super(title, description, Status.NEW);
        this.epic = epic;
    }

    // Новый конструктор, позволяющий задать статус
    public Subtask(String title, String description, Status status, Epic epic) {
        super(title, description, status);
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
                ", epic=" + getEpic().getTitle() +
                '}';
    }
}