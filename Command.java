/*
 * Class Command
 * 
 * Contains Command object properties and methods for creating a priority queue.
 * 
 */

public class Command {
    
    // Object properties.
    private String command = null;
    private String key = null;
    private String data = null;
    public boolean error = false;
    
    /*
     *==========================================================================
     */
    
    // Constructors for different kinds of commands.
    public Command(boolean error) {
        this.error = error;
    }

    public Command(String command) {
        this.command = command;
    }

    public Command(String command, String key, String data) {
        this.command = command;
        this.key = key;
        this.data = data;
    }
    
    /*
     *==========================================================================
     */
    
    // Getters and setters.
    public String getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public String getData() {
        return data;
    }
    
    public boolean isError() {
        return error;
    }
    
}
