package utility;

/**
 * Lists all the actions a server can take
 * @author Victoria Jenkins, Justing Smith, Aaron Merrell
 *
 */
public enum ServerActions {
	MESSAGE("MESSAGE"),
	PLAYER("PLAYER"),
	VALID_PLAYER("VALID"),
	GAME_STARTED("STARTED"),
	USERNAMEERROR("USERNAMEERROR"),
	PRINTUSERS("PRINTUSERS"),
	WORD("WORD"),
	START("START"),
	TURN("TURN"),
	INCORRECTGUESS("INCORRECT"),
	CORRECTGUESS("CORRECT"),
	REMOVELETTEROPTION("REMOVEOPTION"),
	WIN("WIN"),
	LOSE("LOSE"),
	LISTPLAYERS("LIST"),
	FULL_ROOM("FULL"),
	WRONG("WRONG");
	
    private String action;

    /**
     * Defines a server action
     * 
     * @precondition none
     * @postcondition none
     * 
     * @param action
     */
    ServerActions(String action) {
        this.action = action;
    }

    /**
     * Returns the action as a string
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the action as a string
     */
    public String toString() {
        return this.action;
    }
}
