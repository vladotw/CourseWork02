package pro.sky.java.course2.coursework;

import java.time.LocalDateTime;

public class DailyTask extends Task{

    public DailyTask(String tittle, String description, TaskType type, LocalDateTime taskTime) throws IncorrectArgumentException {
        super(tittle, description, type, taskTime);
    }

    @Override
    public LocalDateTime getNextTaskDate(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }
}
