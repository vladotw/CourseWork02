package pro.sky.java.course2.coursework;

import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String tittle, String description, TaskType type, LocalDateTime taskTime) throws IncorrectArgumentException {
        super(tittle, description, type, taskTime);
    }

    @Override
    public LocalDateTime getNextTaskDate(LocalDateTime dateTime) {
        return dateTime.plusWeeks(1);
    }
}