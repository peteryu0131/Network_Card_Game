
import javax.swing.*;
import java.awt.*;

/**
 * the clas extend JComponent
 * the entity for this class is the to add into the JFrame
 * this is the area where the user will choose their card
 * so this class will have the cards array
 * the goal for this class is to override the paintComponent method
 * the method will draw the card out
 */
public class Desk extends JComponent
{

    Card[] cards; // the card on the desk

    //---------------------------------------------------

    /**
     * this is the constructor method
     * this method will initial the cards array
     * the cards array represent teh card the need to draw
     * @param cards the card array
     */

    public Desk(Card[] cards)
    {
        this.cards = cards;
    }

    //-------------------------------------------------

    /**
     * this method override the method in the JComponent class
     * this method will draw the card on the desk
     * @param g the Graphic pen represent the drawing pen
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        paintCards(cards, g2);
    }

    /////////////////////////////////////////////

    /**
     * this method will calculate the size that the card image need thn draw it out
     * intotal there will be 5 riw can 5 colon so devide the height of this component by 4
     * and devide the width of the component bu 5 we can get teh area for each cell
     * in each cell we will have theimage of the card and also the will be margin
     * the margin width is 1 / 8 of the cell width
     * the margin height is 1 / 8 of the cell height
     * by knowing this we can get the position of each card where is need to display
     * @param cards the card array which need to draw
     * @param g2 the Graphic2D which is the draing pen
     */
    private void paintCards(Card[] cards, Graphics2D g2)
    {
        //every time run thismethod will calculate the cell width and height
        int cellWidth = getWidth() / 5;
        int cellHeight = getHeight() >> 2;
        int marginWidth = cellWidth >> 3;
        int marginHeight = cellHeight >> 3;

        // the first card position
        int cardX =  marginWidth;
        int cardY =  marginHeight;

        // for each card
        for(Card card: cards)
        {
            if(cardX >= getWidth()) // if the next card need to paint outside of the desk
            {
                cardX = marginWidth;
                cardY += cellHeight;
            }

            card.setWidth(cellWidth - 2 * marginWidth);
            card.setHeight(cellHeight- 2 * marginHeight);
            card.setX(cardX);
            card.setY(cardY);
            card.setBounds(cardX, cardY, card.getWidth(),card.getHeight());
            card.repaint();

            // find the next position
            cardX = cardX + cellWidth;
        }// end for
    }// end paintCard method

} // end class
