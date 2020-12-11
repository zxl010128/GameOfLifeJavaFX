package GameOfLife;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

class GameOfLifeTest {

    @Test
    void testTick() {
        GameOfLife gol = new GameOfLife();

        // Create a glider toward the bottom-right corner
        gol.ensureAlive(19, 18);
        gol.ensureAlive(18, 18);
        gol.ensureAlive(17, 18);
        gol.ensureAlive(19, 17);
        gol.ensureAlive(18, 16);

        gol.tick();
        assertTrue(gol.isAlive(19, 18));
        assertTrue(gol.isAlive(18, 18));
        assertTrue(gol.isAlive(18, 19));
        assertTrue(gol.isAlive(17, 17));
        assertTrue(gol.isAlive(19, 17));

        assertFalse(gol.isAlive(17, 18));
        assertFalse(gol.isAlive(18, 16));

        gol.tick();
        assertTrue(gol.isAlive(19, 18));
        assertTrue(gol.isAlive(18, 19));
        assertTrue(gol.isAlive(19, 19));
        assertTrue(gol.isAlive(17, 18));
        assertTrue(gol.isAlive(19, 17));

        //Check to see it wraps around horizontally
        gol.tick();
        assertTrue(gol.isAlive(0, 18));

        //Check to see it wraps around vertically
        gol.tick();
        gol.tick();
        assertTrue(gol.isAlive(19, 0));
    }

    @Test
    void testObservation() {
        GameOfLife gol = new GameOfLife();

        gol.cellProperty(0, 0).addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                alive = newValue;
            }

        });


        // In the next generation after this seed the cell at (0,0) should be
        // alive
        gol.ensureAlive(1, 0);
        gol.ensureAlive(1, 1);
        gol.ensureAlive(0, 1);

        gol.tick();

        assertTrue(alive);
    }

    // Used in the above test.
    private boolean alive = false;

}
