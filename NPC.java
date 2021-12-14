/**
 * The NPC class generates a non-player character that
 * can be interacted with if they are added to a room.
 *
 * @author Spencer Gunning
 * @version 2021.12.13
 */
public class NPC
{
    private String description;
    private String clue;
    
    /**
     * Constructor. Creates an NPC object with a description and a clue.
     * @param npcDesc A short description of the NPC.
     * @param npcClue The clue given by the NPC.
     */
    public NPC(String npcDesc, String npcClue) {
        description = npcDesc;
        clue = npcClue;
    }
    
    /**
     * Returns the description of the NPC.
     * @return the description of the NPC.
     */
    public String getDesc() {
        return description;
    }

    /**
     * Returns the clue tied to the NPC.
     * @return the clue tied to the NPC.
     */
    public String getClue() {
        return clue;
    }
}