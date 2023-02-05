package pro.sky.java.course2.coursework;

public class TaskNotFoundException extends Exception{

    public TaskNotFoundException(Integer taskID) {
        super("Задача с id " + taskID + " не найдена");
    }
}
