package pro.sky.java.course2.coursework;

import java.time.LocalDate;

public interface Repeatable {
    LocalDate getNextTaskDate(LocalDate date);
}
