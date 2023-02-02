package pro.sky.java.course2.coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static final TaskService TASK_SERVICE = new TaskService();
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            TasksByDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (IncorrectArgumentException e) {
            e.getMessage();
        }
    }

    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        scanner.useDelimiter("\n");

        String title = enterTitle(scanner);
        String description = enterDescription(scanner);
        TaskType taskType = enterType(scanner);
        LocalDateTime taskTime = enterTime(scanner);
        int repeatable = enterRepeatable(scanner);

        System.out.println("Введите повторяемость:\n" +
                "1 - однократно;\n " +
                "2 - ежедневно;\n " +
                "3 - еженедельно;\n " +
                "4 - ежемесячно;\n " +
                "5 - ежегодно.");

        Task task = null;

        switch (repeatable) {
            case 1:
                task = new OneTimeTask(title, description, taskType, taskTime);
                break;
            case 2:
                task = new DailyTask(title, description, taskType, taskTime);
                break;
            case 3:
                task = new WeeklyTask(title, description, taskType, taskTime);
                break;
            case 4:
                task = new MonthlyTask(title, description, taskType, taskTime);
                break;
            case 5:
                task = new YearlyTask(title, description, taskType, taskTime);
                break;
            default:
                System.out.println("Повторяемость выбрана некорректно.");
        }

        TASK_SERVICE.createTask(task);
        System.out.println("Задача создана");
    }

    private static String enterTitle (Scanner scanner) {
        System.out.println("Введите название задачи: ");
        String title = scanner.next();

        if (title.isBlank()) {
            System.out.println("Название не может быть пустым");
        }
        return title;
    }

    private static String enterDescription(Scanner scanner) {
        System.out.println("Введите описание задачи: ");
        String description = scanner.next();

        if (description.isBlank()) {
            System.out.println("Описание не может быть пустым");
        }

        return description;
    }

    private static TaskType enterType(Scanner scanner) {

        System.out.println("Введите тип задачи (1 - личная; 2 - рабочая): ");

        TaskType taskType = null;
        int typeNumber = scanner.nextInt();

        switch (typeNumber) {
            case 1:
                taskType = TaskType.PERSONAL;
                break;
            case 2:
                taskType = TaskType.WORK;
                break;
            default:
                System.out.println("Некорректные данные");
        }

        return taskType;
    }

    private static LocalDateTime enterTime(Scanner scanner) {

        System.out.println("Введите дату и время в формате dd.MM.yyyy HH:mm:");

        LocalDateTime taskDateTime = null;
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            taskDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время в формате dd.MM.yyyy HH:mm:");
        }

        return taskDateTime;
    }

    private static int enterRepeatable(Scanner scanner) {

        int repeatable = 0;

        System.out.println("Введите повторяемость:\n" +
                "1 - однократно;\n " +
                "2 - ежедневно;\n " +
                "3 - еженедельно;\n " +
                "4 - ежемесячно;\n " +
                "5 - ежегодно.");

        if (scanner.hasNextInt()) {
            repeatable = scanner.nextInt();
        }else {
            System.out.println("Повторяемость выбрана некорректно.");
        }

        return repeatable;
    }

    private static void deleteTask(Scanner scanner) {

        System.out.println("Введите ID задачи");

        int id = scanner.nextInt();

        try {
            TASK_SERVICE.removeTask(id);
            System.out.println("Задача " + id + " удалена");
            System.out.println();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void TasksByDate(Scanner scanner) {

        System.out.println("Введите дату в формате dd.MM.yyyy");

        LocalDate taskDate = null;
        if (scanner.hasNext(DATE_PATTERN)) {
            String date = scanner.next(DATE_PATTERN);
            taskDate = LocalDate.parse(date, DATE_FORMATTER);
            TASK_SERVICE.getTasksByDate(taskDate);
        } else {
            System.out.println("Введите дату и время в формате dd.MM.yyyy HH:mm:");
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "0. Выход");
    }
}