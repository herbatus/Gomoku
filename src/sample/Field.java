package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Tomasz on 15.05.2017.
 */
public class Field extends Rectangle {

    private Piece piece;
    private boolean isVisible;

    private int x, y;

    public boolean hasPiece()
    {
        return isVisible;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getPosX()
    {
        return x;
    }

    public int getPosY()
    {
        return y;
    }

    public Field(int x, int y)
    {
        this.x = x;
        this.y = y;
        isVisible = false;
        setWidth(Main.TILE_SIZE);
        setHeight(Main.TILE_SIZE);

        relocate(x * Main.TILE_SIZE, y * Main.TILE_SIZE);

        setFill(Color.TRANSPARENT);

        setOnMouseClicked(e -> {
            if(getPiece() != null && !isVisible)
            {
                if(Main.whiteTurn)
                {
                    getPiece().color(PieceType.WHITE);
                    Main.whiteTurn = false;
                    Main.move(PieceType.WHITE, x, y);
                    Main.lastMove = this;
                }
                else if(!Main.whiteTurn)
                {
                    getPiece().color(PieceType.BLACK);
                    Main.whiteTurn = true;
                    Main.move(PieceType.BLACK, x, y);
                    Main.lastMove = this;
                }
                isVisible = true;
//                System.out.println(Main.hasHorizontalWin(PieceType.BLACK));
//                System.out.println(Main.hasHorizontalWin(PieceType.WHITE));
//                System.out.println(Main.hasVerticalWin(PieceType.BLACK));
//                System.out.println(Main.hasVerticalWin(PieceType.WHITE));
                System.out.println(Main.checkWinner(this));
                //System.out.println(Main.getAvaliableMoves());
                //Main.minMax(2, Main.whiteTurn);
            }
        });

        setOnMouseMoved(e -> {
            setStroke(Color.RED);
            setStrokeWidth(5);
        });

        setOnMouseExited(e -> {
            setStrokeWidth(0);
        });
    }
}
