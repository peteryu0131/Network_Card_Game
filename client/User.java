

/**
 * the class the the user class
 * represent the user in the game
 */
public class User
{
    private boolean loose;
    private boolean thisTerm;
    private byte count;  // count the number of selected card one user should the max number is 2

    //-------------------------------------------------------------------------

    /**
     * tell the user is loose or not
     * @return true for loose
     */
    public boolean isLoose()
    {
        return loose;
    }

    /**
     * tell is the user term or not
     * @return true for is this term false for not this term
     */
    public boolean isThisTerm()
    {
        return thisTerm;
    }

    /**
     * set is the user playing or not
     * @param thisTerm true for playing false for not playing
     */
    public void setThisTerm(boolean thisTerm)
    {
        this.thisTerm = thisTerm;
    }

    /**
     * get how many time the user select the card
     * @return the number of card user selected
     */
    public byte getCount()
    {
        return count;
    }

    /**
     * set the number of card that usre select
     * @param count the number of card select
     */
    public void setCount(byte count)
    {
        this.count = count;
    }
}
