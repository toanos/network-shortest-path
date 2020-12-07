import java.util.ArrayList;

/**
 * This class represents the gore with floating pads that the Hobbits need to
 * hop on to go to the other side.
 *
 * @author Tony Ha
 */
public class TheGorge {

    /** Default constructor */
    public TheGorge() {
        g = new ArrayList<FloatingPad>();
    }

    public void addFloatingPads(FloatingPad pad) {
        g.add(pad);
    }

    public void removeFloatingPad(FloatingPad pad) {
        g.remove(pad);
    }

    private ArrayList<FloatingPad> g;
}
