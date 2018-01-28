// https://github.com/davidliutest/ics-maze-game 
import java.util.Comparator;

// Compares RCN data structures using the heuristics
public class RCNCompare implements Comparator<RCN>{
    public int compare(RCN rcn1, RCN rcn2) {return rcn1.heuristic - rcn2.heuristic;}
}
