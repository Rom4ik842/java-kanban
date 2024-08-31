// Класс Subtask наследует базовые свойства и методы от класса Task
// и представляет собой подзадачу, связанную с определенным Epic.
public class Subtask extends Task {
    private Epic epic;

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
}
