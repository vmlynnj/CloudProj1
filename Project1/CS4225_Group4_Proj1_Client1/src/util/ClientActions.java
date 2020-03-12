package util;

public enum ClientActions {
	QUIT("QUIT"),
	GUESS("GUESS"),
	LOGIN("LOGIN");
	
    private String action;

    
    ClientActions(String action) {
        this.action = action;
    }

    public String toString() {
        return this.action;
    }
}
