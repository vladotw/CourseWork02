package pro.sky.java.course2.coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {

    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void createTask(Task task) {
        this.taskMap.put(task.getId(), task);
    }

    public void removeTask(Integer taskId) throws TaskNotFoundException {
        if (this.taskMap.containsKey(taskId)) {
            this.taskMap.remove(taskId);
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public void getTasksByDate(LocalDate date) {

        for (Task task : taskMap.values()) {
            LocalDateTime taskDate = task.getTaskTime();
            LocalDate taskNextTime = task.getNextTaskDate(taskDate.toLocalDate());


            if (taskDate.toLocalDate().equals(date)) {
                System.out.println(task);
                if (taskNextTime != null) {
                    System.out.println("Следующая дата задачи: " + taskNextTime);
                } else {
                    System.out.println("Задача не повторяется");
                }
            }else {
                System.out.println("Нет задачи на эту дату");
            }
        }
    }
}
