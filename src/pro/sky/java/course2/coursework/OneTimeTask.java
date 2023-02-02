package pro.sky.java.course2.coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task{

    public OneTimeTask(String tittle, String description, TaskType type, LocalDateTime taskTime) throws IncorrectArgumentException {
        super(tittle, description, type, taskTime);
    }

    @Override
    public LocalDate getNextTaskDate(LocalDate date) {
        return null;
    }
}
