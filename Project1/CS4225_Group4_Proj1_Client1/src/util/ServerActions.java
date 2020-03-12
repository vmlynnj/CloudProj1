package util;

public enum ServerActions {
	MESSAGE("MESSAGE"),
	PLAYER("PLAYER"),
	USERNAMEERROR("USERNAMEERROR"),
	PRINTUSERS("PRINTUSERS"),
	WORD("WORD"),
	START("START"),
	TURN("TURN"),
	INCORRECTGUESS("INCORRECT"),
	CORRECTGUESS("CORRECT"),
	REMOVELETTEROPTION("REMOVEOPTION");
	
    private String action;

    
    ServerActions(String action) {
        this.action = action;
    }

    public String toString() {
        return this.action;
    }
}
