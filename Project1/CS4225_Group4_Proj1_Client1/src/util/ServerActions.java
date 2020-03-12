package util;

public enum ServerActions {
	MESSAGE("MESSAGE"),
	PLAYER("PLAYER"),
	USERNAMEERROR("USERNAMEERROR"),
	PRINTUSERS("PRINTUSERS"),
	WORD("WORD"),
	TURN("TURN");
	
    private String action;

    
    ServerActions(String action) {
        this.action = action;
    }

    public String toString() {
        return this.action;
    }
}
