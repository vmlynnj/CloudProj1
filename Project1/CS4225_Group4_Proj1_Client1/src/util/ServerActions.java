package util;

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
	FULL_ROOM("FULL");
	
    private String action;

    
    ServerActions(String action) {
        this.action = action;
    }

    public String toString() {
        return this.action;
    }
}
