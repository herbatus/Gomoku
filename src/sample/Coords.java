package sample;

/**
 * Created by Tomasz on 17.05.2017.
 */
public class Coords {
    private int x, y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coords(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public String toString()
    {
        return this.x+" "+this.y;
    }
}
