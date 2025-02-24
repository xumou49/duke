package commands;

import entity.CommandParser;
import entity.Deadline;
import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private final String inputs;
    public DeadlineCommand(String inputs) {
        this.inputs = inputs;
    }

    public void execute() {
        if (CommandParser.countCommandParts(inputs) < 2)
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

        String inputBody = CommandParser.getCommandBody(inputs);
        String[] deadlineDesc = CommandParser.getDeadlineDetails(inputBody);
        try {
            Deadline deadline = new Deadline(deadlineDesc[0],
                    LocalDateTime.parse(deadlineDesc[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            instance.addTask(deadline);
        } catch (DukeException | DateTimeParseException err) {
            throw new DukeException("☹ OOPS!!! Invalid date time format, please use yyyy-MM-dd hh:mm.");
        }
    }
}
