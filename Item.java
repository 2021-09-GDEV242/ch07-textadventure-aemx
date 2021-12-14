/**
 * The Item class stores basic information on items that are
 * placed throughout rooms in the game.
 *
 * @author Spencer Gunning
 * @version 2021.12.13
 */
public class Item
{
    private String description;
    private int weight;
    
    /**
     * Constructor. Creates an Item object with a description and a weight value.
     * @param itemDesc A short description of the item.
     * @param itemWeight The weight of the item.
     */
    public Item(String itemDesc, int itemWeight) {
        description = itemDesc;
        weight = itemWeight;
    }
    
    /**
     * Returns the description of the item.
     * @return the description of the item.
     */
    public String getDesc() {
        return description;
    }

    /**
     * Returns the weight of the item.
     * @return the weight of the item.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns a string combining the details about the item.
     * @return a string combining the details about the item.
     */
    public String getAbout() {
        return "It's a " + description + ". Seems to weigh about " + weight + " pound(s).";
    }
}