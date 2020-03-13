package utility;

/**
 * Lists all the actions a server can take
 * @author Victoria Jenkins, Justing Smith, Aaron Merrel
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
     * @param action
     */
    ServerActions(String action) {
        this.action = action;
    }

    /**
     * Returns the action as a string
     * @return the action as a string
     */
    public String toString() {
        return this.action;
    }
}
