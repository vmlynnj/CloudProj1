package utility;
/**
 * The actions the client can take
 * @author Victoria Jenkins, Justing Smith, Aaron Merrel
 *
 */
public enum ClientActions {
	QUIT("QUIT"),
	GUESS("GUESS"),
	LOGIN("LOGIN");
	
    private String action;

    
    /**
     * Creates a client action
     * @param action the action
     */
    ClientActions(String action) {
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
