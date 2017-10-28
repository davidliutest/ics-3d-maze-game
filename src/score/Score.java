package score;

// Class that store information for a player's score;
// able to sort a score
public class Score implements Comparable<Score> {

    private String name;
    private int score;

    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public String toString() {
        return "Score: " + this.score + " " + ", Name: " + this.name;
    }

    public int compareTo(Score o) {
        if(this.score != o.getScore())
            return this.score - o.getScore();
        return this.name.compareTo(o.getName());
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
