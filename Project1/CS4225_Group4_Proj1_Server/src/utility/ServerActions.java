package utility;

public enum ServerActions {
	MESSAGE("MESSAGE"),
	PLAYER("PLAYER"),
	USERNAMEERROR("USERNAMEERROR"),
	PRINTUSERS("PRINTUSERS");
	
    private String action;

    
    ServerActions(String action) {
        this.action = action;
    }

    public String toString() {
        return this.action;
    }
}
