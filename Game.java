/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * The main function.
     * Will allow the game to run outside of BlueJ.
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room library, clubroom, roof, office, closet,
        kitchen, foodCourt, lounge, campusCenter, classroom,
        lab, quad, outside, walkway, parkingLot;
      
        // create the rooms
        library         = new Room("inside the university's library");
        clubroom        = new Room("inside the clubroom in the Campus Center");
        roof            = new Room("on the roof of the main building");
        office          = new Room("inside the main office for the CS department");
        closet          = new Room("inside a shared closet for the lab and office rooms");
        kitchen         = new Room("inside the food court's kitchen");
        foodCourt       = new Room("at the main food court");
        lounge          = new Room("inside the student lounge");
        campusCenter    = new Room("inside the university's Campus Center");
        classroom       = new Room("in a lecture room");
        lab             = new Room("in a computing lab");
        quad            = new Room("on the main quad at the university");
        outside         = new Room("outside the main entrance of the university");
        walkway         = new Room("on a walkway between the entrance and parking lot");
        parkingLot      = new Room("at the parking lot for the university");

        // initialise room exits

        library.setExit("south", foodCourt);
        clubroom.setExit("south", lounge);
        clubroom.setExit("east", roof);
        roof.setExit("west", clubroom);
        roof.setExit("southwest", campusCenter);
        roof.setExit("southeast", classroom);
        office.setExit("south", lab);
        office.setExit("east", closet);
        closet.setExit("northwest", office);
        closet.setExit("southwest", lab);
        kitchen.setExit("east", foodCourt);
        foodCourt.setExit("west", kitchen);
        foodCourt.setExit("north", library);
        foodCourt.setExit("east", lounge);
        lounge.setExit("west", foodCourt);
        lounge.setExit("north", clubroom);
        lounge.setExit("east", campusCenter);
        campusCenter.setExit("west", lounge);
        campusCenter.setExit("north", roof);
        campusCenter.setExit("east", classroom);
        campusCenter.setExit("south", outside);
        classroom.setExit("west", campusCenter);
        classroom.setExit("north", roof);
        classroom.setExit("east", lab);
        lab.setExit("west", classroom);
        lab.setExit("north", office);
        lab.setExit("east", closet);
        quad.setExit("east", outside);
        outside.setExit("west", quad);
        outside.setExit("east", walkway);
        walkway.setExit("west", outside);
        walkway.setExit("east", parkingLot);
        parkingLot.setExit("west", walkway);

        // start game in the parking lot
        currentRoom = parkingLot;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
