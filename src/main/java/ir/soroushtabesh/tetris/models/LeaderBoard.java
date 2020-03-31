package ir.soroushtabesh.tetris.models;

import java.io.*;
import java.util.Arrays;

public class LeaderBoard implements Serializable {
    private static final long serialVersionUID = 332455961775469444L;
    private Integer[] list = new Integer[11];

    public LeaderBoard() {
        Arrays.fill(list, 0);
    }

    public static LeaderBoard loadFile() {
        File file = new File("leader_board.dat");
        if (!file.exists()) {
            LeaderBoard leaderBoard = new LeaderBoard();
            leaderBoard.saveList();
            return leaderBoard;
        }
        LeaderBoard leaderBoard = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream iis = new ObjectInputStream(fis);
            leaderBoard = (LeaderBoard) iis.readObject();
            iis.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return leaderBoard;
    }

    public Integer[] getList() {
        return list;
    }

    public void addToList(int num) {
        list[10] = num;
        Arrays.sort(list, (o1, o2) -> -Integer.compare(o1, o2));
        saveList();
    }

    public void saveList() {
        try {
            File file = new File("leader_board.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            fos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "LeaderBoard{" +
                "list=" + Arrays.toString(list) +
                '}';
    }
}
