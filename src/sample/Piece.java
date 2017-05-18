package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import static sample.Main.TILE_SIZE;

/**
 * Created by Tomasz on 15.05.2017.
 */
public class Piece extends StackPane {

    private static final double SIZE = 0.45;

    private PieceType type;
    private Ellipse ellipse;

    private double mouseX, mouseY;

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public Piece(int x, int y)
    {

        this.type = null;
        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        ellipse = new Ellipse(TILE_SIZE * SIZE, TILE_SIZE * SIZE);
        ellipse.setFill(Color.TRANSPARENT);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * SIZE * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * SIZE * 2) / 2);

        getChildren().add(ellipse);
    }

    public Piece(PieceType type, int x, int y)
    {
        this.type = type;
        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        ellipse = new Ellipse(TILE_SIZE * SIZE, TILE_SIZE * SIZE);
        ellipse.setFill(Color.BLACK);

        ellipse.setTranslateX((TILE_SIZE - TILE_SIZE * SIZE * 2) / 2);
        ellipse.setTranslateY((TILE_SIZE - TILE_SIZE * SIZE * 2) / 2);

        getChildren().add(ellipse);
    }

    public void color()
    {
        ellipse.setFill(Color.BLACK);
    }

    public void color(PieceType type)
    {
        if(type == PieceType.BLACK)
        {
            ellipse.setFill(Color.BLACK);
            setType(type);
        }
        else if(type == PieceType.WHITE)
        {
            ellipse.setFill(Color.WHITE);
            setType(type);
        }
    }
}
