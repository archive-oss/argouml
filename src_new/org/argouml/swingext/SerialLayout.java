/*
 * SerialLayout.java
 */
package org.argouml.swingext;

import java.awt.*;
import java.util.*;

import org.apache.log4j.Category;

/**
 * Lays out components in a single row or column starting from any side and aligning  components
 * to eachother.<br />
 * Components can be set to start draw from, LEFTTORIGHT, TOPTOBOTTOM, RIGHTTOLEFT or BOTTOMTOTOP.<br />
 * Components will line up with eachother by edge or follow a common central line.<br />
 * The gap to leave before the first component and the following gaps between each component can
 * be set.
 *
 * @author Bob Tarling
 */
public class SerialLayout extends LineLayout {
    
    protected static Category cat = 
        Category.getInstance(SerialLayout.class);
        
    public final static int LEFTTORIGHT = 10;
    public final static int TOPTOBOTTOM = 10;
    public final static int RIGHTTOLEFT = 11;
    public final static int BOTTOMTOTOP = 11;

    public final static String NORTH = "North";
    public final static String SOUTH = "South";
    public final static String EAST = "East";
    public final static String WEST = "West";
    public final static String NORTHEAST = "NorthEast";
    public final static String NORTHWEST = "NorthWest";
    public final static String SOUTHEAST = "SouthEast";
    public final static String SOUTHWEST = "SouthWest";

    public final static int LEFT = 20;
    public final static int RIGHT = 21;
    public final static int TOP = 20;
    public final static int BOTTOM = 21;
    public final static int CENTER = 22;
    public final static int FILL = 23;

    String position = WEST;
    int direction = LEFTTORIGHT;
    int alignment = TOP;

    public SerialLayout() {
        this(Horizontal.getInstance(), WEST, LEFTTORIGHT, TOP);
    }
    public SerialLayout(Orientation orientation) {
        this(orientation, WEST, LEFTTORIGHT, TOP);
    }
    public SerialLayout(Orientation orientation, String position) {
        this(orientation, position, LEFTTORIGHT, TOP);
    }
    public SerialLayout(Orientation orientation, String position, int direction) {
        this(orientation, position, direction, TOP);
    }
    
    public SerialLayout(Orientation orientation, String position, int direction, int alignment) {
        super(orientation);
        this.position = position;
        this.direction = direction;
        this.alignment = alignment;
    }

    public SerialLayout(Orientation orientation, String position, int direction, int alignment, int gap) {
        super(orientation, gap);
        this.position = position;
        this.direction = direction;
        this.alignment = alignment;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        Point loc;
        int preferredBreadth = _orientation.getBreadth(parent.getPreferredSize());
        if (direction == LEFTTORIGHT) {
            if (position.equals(EAST)) {
                loc = new Point(parent.getWidth() - (insets.right + preferredLayoutSize(parent).width), insets.top);
            } else {
                loc = new Point(insets.left, insets.top);
            }
            int nComps = parent.getComponentCount();
            for (int i = 0 ; i < nComps ; i++) {
                Component comp = parent.getComponent(i);
                if (comp != null && comp.isVisible()) {
                    Dimension size = comp.getPreferredSize();
                    if (alignment == FILL) {
                        _orientation.setBreadth(size, preferredBreadth);
                    }
                    comp.setSize(size);
                    comp.setLocation(loc);
                    loc = _orientation.addToPosition(loc, comp);
                    loc = _orientation.addToPosition(loc, _gap);
                }
            }
        }
        else {
            int lastUsablePosition = _orientation.getLastUsablePosition(parent);
            int firstUsableOffset = _orientation.getFirstUsableOffset(parent);
            loc = _orientation.newPoint(lastUsablePosition, firstUsableOffset);

            int nComps = parent.getComponentCount();
            for (int i = 0 ; i < nComps ; i++) {
                Component comp = parent.getComponent(i);
                if (comp != null && comp.isVisible()) {
                    loc = _orientation.subtractFromPosition(loc, comp);
                    Dimension size = comp.getPreferredSize();
                    if (alignment == FILL) {
                        _orientation.setBreadth(size, preferredBreadth);
                    }
                    comp.setSize(size);
                    comp.setLocation(loc);
                    loc = _orientation.subtractFromPosition(loc, _gap);
                }
            }
        }
    }
}
