package ir.soroushtabesh.tetris.models;

import java.io.Serializable;

public final class Vector2D implements Serializable {
    public static final Vector2D UP = new Vector2D(0, -1);
    public static final Vector2D DOWN = new Vector2D(0, 1);
    public static final Vector2D LEFT = new Vector2D(-1, 0);
    public static final Vector2D RIGHT = new Vector2D(1, 0);
    public static final Vector2D ZERO = new Vector2D(0, 0);
    private static final long serialVersionUID = -8674439820510616899L;
    private final int x;
    private final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2D add(Vector2D rhs) {
        return new Vector2D(getX() + rhs.getX(), getY() + rhs.getY());
    }

    public Vector2D negate() {
        return new Vector2D(-getX(), -getY());
    }

    public Vector2D rotateCCW() {
        return new Vector2D(getY(), -getX());
    }

    public Vector2D rotateCW() {
        return new Vector2D(-getY(), getX());
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
