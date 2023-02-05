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

    public List<Task> getTasksByDate(LocalDate date) {

        List<Task> taskByDayList = new ArrayList<>();

        for (Task task : taskMap.values()) {

            LocalDateTime taskDate = task.getTaskTime();
            LocalDate taskNextTime = task.getNextTaskDate(taskDate.toLocalDate());

            if (taskNextTime == null || taskDate.toLocalDate().equals(date)) {
                taskByDayList.add(task);
                continue;
            }

            do {
                if (taskNextTime != null && taskNextTime.equals(date)) {
                    taskByDayList.add(task);
                    break;
                }
                taskNextTime = task.getNextTaskDate(taskNextTime);
            } while (taskNextTime.isBefore(date));
        }

        return taskByDayList;
    }
}
