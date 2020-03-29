package ir.soroushtabesh.tetris.models;

public class BlockShape {
    public static final BlockShape NULL = new BlockShape();
    public static final BlockShape LEG_LEFT = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(-1, 0)
            , new Vector2D(1, 0)
            , new Vector2D(-1, -1));
    public static final BlockShape LEG_RIGHT = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(-1, 0)
            , new Vector2D(1, 0)
            , new Vector2D(1, -1));
    public static final BlockShape STICK = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(-1, 0)
            , new Vector2D(1, 0)
            , new Vector2D(2, 0));
    public static final BlockShape WINDOW = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(0, -1)
            , new Vector2D(1, 0)
            , new Vector2D(1, -1));
    public static final BlockShape PEAK = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(0, -1)
            , new Vector2D(1, 0)
            , new Vector2D(-1, 0));
    public static final BlockShape DUCK_LEFT = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(0, -1)
            , new Vector2D(1, 0)
            , new Vector2D(-1, -1));
    public static final BlockShape DUCK_RIGHT = new BlockShape(
            new Vector2D(0, 0)
            , new Vector2D(0, -1)
            , new Vector2D(-1, 0)
            , new Vector2D(1, -1));
    public static final BlockShape[] BLOCK_SHAPES =
            new BlockShape[]{NULL, LEG_LEFT, LEG_RIGHT, STICK, WINDOW, PEAK, DUCK_LEFT, DUCK_RIGHT};
    private final Vector2D[] graph;

    public BlockShape(Vector2D... graph) {
        this.graph = graph;
    }

    public Vector2D[] getGraph() {
        return graph;
    }

}
