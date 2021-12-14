import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author Spencer Gunning
 * @version 2021.12.13
 */

public class Room 
{
    private String description;
    // stores exits of this room
    private HashMap<String, Room> exits;
    // store an item
    private Item item;
    // store a NPC
    private NPC npc;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        item = null;
        npc = null;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen. [There is <someone/an item> here!]
     *     Exits: north west
     *     [Special string content]
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String specialString = item != null ? " There is an item here!\n" + item.getAbout() + "\n" : "\n" ;
        specialString = npc != null ? " There is someone here!\nThey are a " + npc.getDesc() + ".\n" : "\n" ;
        return "You are " + description + "." + specialString + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Adds an item to the room.
     * @param itemDesc A short description of the item.
     * @param itemWeight The weight of the item.
     */
    public void addItem(String itemDesc, int itemWeight) {
        item = new Item(itemDesc, itemWeight);
    }

    /**
     * Adds an NPC to the room.
     * @param npcDesc A short description of the NPC.
     * @param npcClue The clue given by the NPC.
     */
    public void addNPC(String npcDesc, String npcClue) {
        npc = new NPC(npcDesc, npcClue);
    }

    /**
     * Returns NPC data for a given room.
     * @return NPC data for a given room.
     */
    public NPC getNPC() {
        return npc;
    }
}