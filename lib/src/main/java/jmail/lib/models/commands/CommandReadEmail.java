package jmail.lib.models.commands;

import jmail.lib.constants.CommandActions;
import lombok.Getter;
import lombok.Setter;

public class CommandReadEmail extends Command {

    private CommandReadEmailParameter parameter;

    public CommandReadEmail(CommandReadEmailParameter parameter) {
        super(CommandActions.READ);
        this.parameter = parameter;
    }

    @Getter @Setter
    public static class CommandReadEmailParameter extends CommandParameters {
        private String id;
        private Boolean setRead;
    }

}