package game.managers;


import game.main.Handler;

public class GameLogic {

    private Handler handler;

    public GameLogic(Handler handler) {
        this.handler = handler;
    }

    public void update() {
        if(handler.getEntityManager().getPlayer().getMapPos().equals(handler.getMap().getEnd())) {
            handler.getStateManager().setState(3);
            handler.getStateManager().getGameState().reset();
        } else if(handler.getEntityManager().getPlayer().getHealth() <= 0) {
            handler.getStateManager().setState(4);
            handler.getStateManager().getGameState().reset();
        }
    }

}
