package util;
/**
 * The actions the client can take
 * @author Victoria Jenkins, Justing Smith, Aaron Merrel
 *
 */
public enum ClientActions {
	QUIT("QUIT"),
	GUESS("GUESS"),
	RETRY("RETRY"),
	TURN_END("TURNEND"),
	LOGIN("LOGIN");
	
    private String action;
    
    /**
     * Creates a client action
     * 
     * @precondition none
     * @postcondition none
     * 
     * @param action the action
     */
    ClientActions(String action) {
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
