package pro.sky.java.course2.coursework;

import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime getNextTaskDate(LocalDateTime dateTime);
}
