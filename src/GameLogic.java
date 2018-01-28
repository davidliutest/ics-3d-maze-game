// https://github.com/davidliutest/ics-maze-game

// Handles the gameplay logic
public class GameLogic {

    private Handler handler;

    public GameLogic(Handler handler) {
        this.handler = handler;
    }

    public void update() {
        if(handler.getEntityManager().getPlayer().getMapPos().equals(handler.getMap().getEnd())) {
            // Check win (if player reach objective)
            handler.getStateManager().setState(3);
            handler.getStateManager().getGameState().reset();
        } else if(handler.getEntityManager().getPlayer().getHealth() <= 0) {
            // Check lose (if player health is 0)
            handler.getStateManager().setState(4);
            handler.getStateManager().getGameState().reset();
        }
    }

}
