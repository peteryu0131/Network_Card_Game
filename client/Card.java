

import java.awt.*;
import javax.swing.*;


/**
 * the class extends JPanel
 * which is a component to contain in the desk
 * the class represent the card in the game
 * the card have their position relate to the desk
 * have their width and height for the image
 * the card can be a loose card or a selected card
 * each entity of the class represent one card object in the game
 */
public class Card extends JPanel
{
    static final int DEFAULT_HEIGHT = 20;  // default height of the card
    static final int DEFAULT_WIDTH = 10;   // default width of the card
    int x,y;    // the position of the card
    private int width, height;   // the height and width of the card
    private Image img;
    private boolean isLoose;   // is  the card is is the loose card
    private boolean selected;  // is this card is already selected

    //----------------------------------------------------------------------------------------

    /**
     * set the card to default setting
     * width and height will set to default
     * the image of the card will set to unselected
     * this is not a loose card and the card is not selected;
     */
    public Card()
    {
       width = DEFAULT_WIDTH;
       height = DEFAULT_HEIGHT;
       ImageIcon imageIcon = new ImageIcon("unSelect.jpg");
       img = imageIcon.getImage();
       isLoose = false;
       selected = false;
    }



    //----------------------------------------------------------------------------------------

    /**
     * the method override the method in JComponent
     * it will draw the card's image to its 0 but the set position on the desk
     * also draw with the size that is set
     * @param g the Graphics which is the drawing pen
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, 0, 0, width, height, null);
    }



    //-----------------------------------------------------------------------------------------
     // getter and setter

    /**
     * set the x position for the card relative the component contain the card
     * @param x the x position
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * get the y position relative to the component contain the card
     * @return the y position
     */
    public int getY()
    {
        return y;
    }

    /**
     * get the x position relative to the component contain the card
     * @return the x position
     */
    public int getX()
    {
        return x;
    }

    /**
     * set the x position for the card relative the component contain the card
     * @param y the y position
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * get the width of the card
     * @return the width of the card
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * set the width of the card
     * @param width the width of the card
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * get the height of the card
     * @return the height of the card
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * set the height of the card
     * @param height the height of the card
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * get the image of the card
     * @return the image of the card
     */
    public Image getImg()
    {
        return img;
    }

    /**
     * set the image of the card
     * @param img the image of the card
     */
    public void setImg(Image img)
    {
        this.img = img;
    }

    /**
     * determine the card is a loose card or not
     * @return boolean true for is a loose false for normal card
     */
    public boolean isLoose()
    {
        return isLoose;
    }

    /**
     * set the card is a loose card or not
     * @param loose boolean true for is a loose false for normal card
     */
    public void setLoose(boolean loose)
    {
        isLoose = loose;
    }

    /**
     * determine the card is selected or not
     * @return boolean true for the card is selected false for the card is not selected
     */
    public boolean isSelected()
    {
        return selected;
    }

    /**
     * set the card is selected or not
     * @param selected boolean true for the card is selected false for the card is not selected
     */
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }


    //--------------------------------------------------------------------------------------
    //  end getter and setter
}
