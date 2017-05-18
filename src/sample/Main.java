package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application {

    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 14;
    public static final int HEIGHT = 14;
    public static final int MARGAIN = 25;

    public static PieceType[][] board = new PieceType[WIDTH+1][HEIGHT+1];
    public static boolean whiteTurn;
    public static Field lastMove;
    public static Coords computerMove;

    private Group tileGroup = new Group();
    private Group fieldGroup = new Group();
    private Group pieceGroup = new Group();

    public static void move(PieceType type, int x, int y)
    {
        board[x][y] = type;
        //System.out.println(type);
    }

    public static boolean hasHorizontalWin(PieceType type)
    {
        int count = 0;
        boolean isWinner = false;
        for(int y = 0; y <= HEIGHT; y++)
        {
            for(int x = 0; x <= WIDTH; x++)
            {
                if(board[x][y] == type && count < 5)
                {
                    count++;
                }
                else if(count == 5)
                {
                    isWinner = true;
                    break;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
            if(isWinner)
                break;
        }
        return isWinner;
    }

    public static boolean hasVerticalWin(PieceType type)
    {
        int count = 0;
        boolean isWinner = false;
        for(int x = 0; x <= HEIGHT; x++)
        {
            for(int y = 0; y <= WIDTH; y++)
            {
                if(board[x][y] == type && count < 5)
                {
                    count++;
                }
                else if(count == 5)
                {
                    isWinner = true;
                    break;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
            if(isWinner)
                break;
        }
        return isWinner;
    }

    public static boolean checkWinner(Field field)
    {
        int x0 = field.getPosX();
        int y0 = field.getPosY();
        PieceType type = field.getPiece().getType();
        int count = 0;
        boolean isWinner = false;
        int smaller = Math.min(x0, y0);

        //sprawdzamy w lewo
            count = 0;
            for(int x = x0; x >= 0; x--)
            {
                if(board[x][y0] == type && count < 5)
                    count++;
                else if(count == 5)
                {
                    isWinner = true;
                    return isWinner;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
            if(isWinner)
                return isWinner;
        //sprawdzamy w prawo
            count = 0;
            for(int x = x0; x <= HEIGHT; x++)
            {
                if(board[x][y0] == type && count < 5)
                    count++;
                else if(count == 5)
                {
                    isWinner = true;
                    return isWinner;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
            if(isWinner)
                return isWinner;

        //sprawdzamy w gore
            count = 0;
            for(int y = y0; y >= 0; y--)
            {
                if(board[x0][y] == type && count < 5)
                    count++;
                else if(count == 5)
                {
                    isWinner = true;
                    return isWinner;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
            if(isWinner)
                return isWinner;

        //sprawdzamy w dol
            count = 0;
            for(int y = y0; y <= WIDTH; y++)
            {
                if(board[x0][y] == type && count < 5)
                    count++;
                else if(count == 5)
                {
                    isWinner = true;
                    return isWinner;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
            if(isWinner)
                return isWinner;

        //sprawdzamy lewo-gora
        count = 0;
        for(int i = 0; i <= smaller; i++)
        {
            if(board[x0-i][y0-i] == type && count < 5)
                    count++;
                else if(count == 5)
                {
                    isWinner = true;
                    return isWinner;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
        }
        //sprawdzamy prawo-gora
        if(x0 <= HEIGHT - 4 && y0 >= 4)
        {
            smaller = Math.min(HEIGHT - x0, y0);
            count = 0;
            for(int i = 0; i <= smaller; i++)
            {
                if(board[x0+i][y0-i] == type && count < 5)
                    count++;
                else if(count == 5)
                {
                    isWinner = true;
                    return isWinner;
                }
                else
                {
                    isWinner = false;
                    count = 0;
                }
            }
        }

        //sprawdzamy lewo-dol
        smaller = Math.min(x0, WIDTH - y0);
        count = 0;
        for(int i = 0; i <= smaller; i++)
        {
            if(board[x0-i][y0+i] == type && count < 5)
                count++;
            else if(count == 5)
            {
                isWinner = true;
                return isWinner;
            }
            else
            {
                isWinner = false;
                count = 0;
            }
        }
        //sprawdzamy prawo-dol
        smaller = Math.min(HEIGHT - x0, WIDTH - y0);
        count = 0;
        for(int i = 0; i <= smaller; i++)
        {
            if(board[x0+i][y0+i] == type && count < 5)
                count++;
            else if(count == 5)
            {
                isWinner = true;
                return isWinner;
            }
            else
            {
                isWinner = false;
                count = 0;
            }
        }
        return isWinner;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent()
    {
        whiteTurn = true;
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #FBCB70;");
        root.setPrefSize(WIDTH * TILE_SIZE + MARGAIN * 2, HEIGHT * TILE_SIZE + MARGAIN * 2);
        root.getChildren().addAll(tileGroup, pieceGroup, fieldGroup);

        for(int y = 0; y <= HEIGHT; y++)
        {
            for(int x = 0; x <= WIDTH; x++)
            {
                if(y != HEIGHT && x != WIDTH)
                {
                    Tile tile = new Tile(x, y, MARGAIN);

                    tileGroup.getChildren().add(tile);
                }

                Field field = new Field(x, y);
                Piece piece = new Piece(x, y);

                field.setPiece(piece);
                fieldGroup.getChildren().add(field);

                pieceGroup.getChildren().add(piece);
            }
        }

        return root;
    }

    public static ArrayList<Coords> getAvaliableMoves()
    {
        ArrayList<Coords> avaliableMoves = new ArrayList<>();

        for(int x = 0; x <= HEIGHT; x++)
        {
            for(int y = 0; y <= WIDTH; y++)
            {
                if(board[x][y] == null)
                    avaliableMoves.add(new Coords(x, y));
            }
        }
        return avaliableMoves;
    }

    public static int minMax(int depth, boolean whiteTurn)
    {
        if(checkWinner(lastMove) && whiteTurn)
            return 1;
        if(checkWinner(lastMove) && !whiteTurn)
            return -1;

        ArrayList<Coords> avaliableMoves = getAvaliableMoves();

        if(avaliableMoves.isEmpty())
            return 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(Coords coord : avaliableMoves)
        {
            PieceType type;
            if(!whiteTurn)
            {
                type = PieceType.BLACK;
                move(type, coord.getX(), coord.getY());
                whiteTurn = !whiteTurn;
                int currentScore = minMax(depth + 1, whiteTurn);
                max = Math.max(currentScore, max);

                if(depth == 0)
                    System.out.println("Computer score for position "+coord+ " "+currentScore);

                if(currentScore >= 0)
                    if(depth == 0)
                        computerMove = coord;

                if(currentScore == 1)
                {
                    board[coord.getX()][coord.getY()] = null;
                    break;
                }
                if(coord == avaliableMoves.get(avaliableMoves.size() - 1) && max < 0)
                    if(depth == 0)
                        computerMove = coord;

            }
            else if(whiteTurn)
            {
                type = PieceType.WHITE;
                move(type, coord.getX(), coord.getY());
                whiteTurn = !whiteTurn;
                int currentScore = minMax(depth + 1, !whiteTurn);
                min = Math.min(currentScore, min);

                if(min == -1)
                {
                    board[coord.getX()][coord.getY()] = null;
                    break;
                }
            }
            board[coord.getX()][coord.getY()] = null;

        }
        return whiteTurn ? min : max;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Gomoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
