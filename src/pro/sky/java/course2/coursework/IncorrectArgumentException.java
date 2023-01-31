package pro.sky.java.course2.coursework;

public class IncorrectArgumentException extends Exception{

    private final String argument;

    public IncorrectArgumentException(String argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return "Параметр " + argument + " некорректен";
    }
}
