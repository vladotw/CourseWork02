package pro.sky.java.course2.coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String tittle, String description, TaskType type, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(tittle, description, type, dateTime);
    }

    @Override
    public LocalDate getNextTaskDate(LocalDate date) {
        return date.plusYears(1);
    }
}
