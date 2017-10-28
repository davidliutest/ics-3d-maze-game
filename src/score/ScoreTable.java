package score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Class that contains and manages all player scores
// The scores can be sorted and returned for the player to see the high score list
public class ScoreTable {

    private List<Score> scoreTable;

    public ScoreTable() {
        scoreTable = new ArrayList<Score>();
    }

    public void addScore(Score s) {
        scoreTable.add(s);
    }

    public String toString() {
        String temp = "";
        for(Score s : scoreTable)
            temp += s.toString() + "\n";
        return temp;
    }

    // Should use binary insertion, but assignment must use sorting
    public void sortScoreTable() {
        Collections.sort(scoreTable);
    }

    public List getScoreTable() {
        return scoreTable;
    }

}
