package sample;

import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Tomasz on 15.05.2017.
 */
public class Tile extends Rectangle{

    public Tile(int x, int y)
    {
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);

        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        setFill(Color.valueOf("#FBCB70"));
        setStroke(Color.BLACK);

    }
    public Tile(int x, int y, int margain)
    {
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);

        relocate(x * Main.TILE_SIZE + margain, y * Main.TILE_SIZE + margain);

        setFill(Color.valueOf("#FBCB70"));
        setStroke(Color.BLACK);

    }
}
