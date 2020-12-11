/**
 *
 */
package GameOfLife;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 20x20 grid.
 *
 * @author Xinlei Zhang
 *
 */
public class GameOfLife {

    BooleanProperty[][] grid;
    public static final int dimension = 20;

    public GameOfLife() {
        this.grid = new BooleanProperty[dimension][dimension];

        for (int i = 0; i < dimension; i++ ) {

            for (int j = 0; j < dimension; j++) {

                grid[i][j] = new SimpleBooleanProperty(false);

            }

        }
    }

    public void ensureAlive(int x, int y) {
        this.grid[x][y].setValue(true);
    }

    public void ensureDead(int x, int y) {
        this.grid[x][y].setValue(false);
    }

    public boolean isAlive(int x, int y) {
        return this.grid[x][y].getValue();
    }

    public void tick() {
        
        boolean[][] future = new boolean[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            
            for (int j = 0; j < dimension; j++) {
                
                int AliveNeighbours = 0;

                AliveNeighbours = checkNeighboursAlive(i, j);

                if (isAlive(i, j) == true) {
                    // Any live cell with two or three live neighbours lives on to the next generation
                    if (AliveNeighbours == 2 || AliveNeighbours == 3) {
                        future[i][j] = true;
                    // Any live cell with more than three live neighbours dies
                    // Any live cell with fewer than two live neighbours dies
                    } else {
                        future[i][j] = false;
                    }
                } else {
                    // Any dead cell with exactly three live neighbours becomes a live cell
                    if (AliveNeighbours == 3) {
                        future[i][j] = true;
                    }
                }
            }
        }

        // update the current view
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.grid[i][j].setValue(future[i][j]);
            }
        }

    }

    public int checkNeighboursAlive(int x, int y) {
        
        int neighbours = 0;

        // top-left
        if (isAlive((x - 1 + dimension) % dimension, (y - 1 + dimension) % dimension)) neighbours ++;
        // bottom-right
        if (isAlive((x + 1 + dimension) % dimension, (y + 1 + dimension) % dimension)) neighbours ++;
        // bottom-left
        if (isAlive((x + 1 + dimension) % dimension, (y - 1 + dimension) % dimension)) neighbours ++;
        // top-right
        if (isAlive((x - 1 + dimension) % dimension, (y + 1 + dimension) % dimension)) neighbours ++;
        // left
        if (isAlive((x + dimension) % dimension, (y - 1 + dimension) % dimension)) neighbours ++;
        // right
        if (isAlive((x + dimension) % dimension, (y + 1 + dimension) % dimension)) neighbours ++;
        // bottom
        if (isAlive((x + 1 + dimension) % dimension, (y + dimension) % dimension)) neighbours ++;
        // top
        if (isAlive((x - 1 + dimension) % dimension, (y + dimension) % dimension)) neighbours ++;

        return neighbours;

    }

    public BooleanProperty cellProperty(int x, int y) {
        return this.grid[x][y];
    }
}
