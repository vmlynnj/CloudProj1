package utility;

public enum ServerActions {
	MESSAGE("MESSAGE"),
	PLAYER("PLAYER"),
	USERNAMEERROR("USERNAMEERROR"),
	PRINTUSERS("PRINTUSERS"),
	WORD("WORD"),
	START("WORD"),
	TURN("TURN"),
	VALID_PLAYER("VALID"),
	GAME_STARTED("STARTED"),
	INCORRECTGUESS("INCORRECT"),
	CORRECTGUESS("CORRECT"),
	REMOVELETTEROPTION("REMOVEOPTION"),
	WIN("WIN"),
	LOSE("LOSE"),
	FULL_ROOM("FULL");
	
    private String action;

    
    ServerActions(String action) {
        this.action = action;
    }

    public String toString() {
        return this.action;
    }
}
