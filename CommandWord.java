/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Spencer Gunning
 * @version 2021.12.13
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    LOOK("look"), GO("go"), BACK("back"), BACKMANY("backmany"),
    EAT("eat"), TALK("talk"), QUIT("quit"), HELP("help"), UNKNOWN("?");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
