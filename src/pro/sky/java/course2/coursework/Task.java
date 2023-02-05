package pro.sky.java.course2.coursework;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {
    private static int idGenerator = 1;

    private String title;
    private String description;
    private TaskType type;
    private LocalDateTime taskTime;
    private int id;

    public Task(String title, String description, TaskType type, LocalDateTime taskTime) throws IncorrectArgumentException {
        setTitle(title);
        setDescription(description);
        setType(type);
        setTaskTime(taskTime);
        this.id = idGenerator++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IncorrectArgumentException {
        if (title != null && !title.isBlank()) {
            this.title = title;
        } else {
            throw new IncorrectArgumentException("Заголовок");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description != null && !description.isBlank()) {
            this.description = description;
        } else {
            throw new IncorrectArgumentException("Описание");
        }
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) throws IncorrectArgumentException {
        if (description != null) {
            this.type = type;
        } else {
            throw new IncorrectArgumentException("Тип");
        }
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) throws IncorrectArgumentException {
        if (taskTime != null) {
            this.taskTime = taskTime;
        } else {
            throw new IncorrectArgumentException("Дата и время");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(getTitle(), task.getTitle()) && Objects.equals(getDescription(),
                task.getDescription()) && getType() == task.getType() && Objects.equals(getTaskTime(), task.getTaskTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getType(), getTaskTime(), id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "tittle='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", taskTime=" + taskTime +
                ", id=" + id +
                '}';
    }
}
