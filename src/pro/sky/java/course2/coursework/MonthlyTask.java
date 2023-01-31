package pro.sky.java.course2.coursework;

import java.time.LocalDateTime;

public class MonthlyTask extends Task{

    public MonthlyTask(String tittle, String description, TaskType type, LocalDateTime taskTime) throws IncorrectArgumentException {
        super(tittle, description, type, taskTime);
    }

    @Override
    public LocalDateTime getNextTaskDate(LocalDateTime dateTime) {
        return dateTime.plusMonths(1);
    }
}
