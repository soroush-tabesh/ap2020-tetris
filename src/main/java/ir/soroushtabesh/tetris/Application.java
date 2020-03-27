package ir.soroushtabesh.tetris;

import ir.soroushtabesh.tetris.controllers.GameLoader;

public class Application {
    public static void main(String[] args) {
        GameLoader loader = GameLoader.getInstance();
        loader.start();
    }
}
