package nu.smashit.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import nu.smashit.core.bodies.Field;
import nu.smashit.core.bodies.BrickRow;
import nu.smashit.core.bodies.Brick;
import nu.smashit.core.bodies.Ball;
import java.util.TimerTask;
import nu.smashit.core.bodies.Paddle;
import nu.smashit.core.power.NoPower;
import nu.smashit.core.power.Power;
import nu.smashit.data.Repositories;
import nu.smashit.socket.actions.GameStateUpdateAction;

/**
 *
 * @author jodus
 */
public abstract class GameLoop extends TimerTask {

    private Ball ball;
    private final Field field;
    private final Game gameSession;
    private Player lastPlayerToHitPaddle;
    private boolean initRun;
    private Set<Power> powers;
    private int brickHits;
    private int prevTime;

    public GameLoop(Game gm, Field field) {
        this.gameSession = gm;
        this.field = field;
        this.powers = new HashSet<>();
        this.brickHits = 0;
        double speedBall = Repositories.getLevelRepository().getDifficulty(gm.getLevel()).getSpeedBall();
        setBall(new Ball(speedBall));
        setInitRun(false);
    }

    private boolean runBrickRowCollision(int i, GameStateUpdateAction updateState) {
        BrickRow brickRow = getField().getRow(i);

        if (Collision.isVerCollision(getBall(), brickRow)) {
            for (Brick brick : brickRow.getBricks()) {
                if (brick != null && !brick.isBroken() && Collision.isHozCollision(getBall(), brick)) {
                    brick.smashBrick();
                    updateState.addBrick(brick);

                    if (Collision.isTopOrBottomCollision(getBall(), brick)) {
                        getBall().inverseVerSpeed();
                    } else {
                        getBall().inverseHozSpeed();
                    }

                    if (brick.isBroken() && getLastPlayerToHitPaddle() != null) {
                        brickHits += 1;
                        Power power = brick.getPower();
                        if (!(power instanceof NoPower)) {
                            power.setPlayer(lastPlayerToHitPaddle);
                            powers.add(power);
                            getLastPlayerToHitPaddle().getScore().addBrickSmash(brick);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    protected boolean runBrickCollision(GameStateUpdateAction updateState) {
        int start = 0;
        int end = getField().getNumberOfRows() - 1;

        if (getBall().isGoingUp()) {
            for (int i = end; i >= start; i--) {
                if (runBrickRowCollision(i, updateState)) {
                    return true;
                }
            }
        } else {
            for (int i = start; i <= end; i++) {
                if (runBrickRowCollision(i, updateState)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean runPaddleCollision(GameStateUpdateAction updateState) {
        int pIndex = (getBall().isGoingUp() && getGameSession().playerCount() > 1) ? 1 : 0;
        Player player = getGameSession().getPlayers()[pIndex];

        if (player != null && Collision.isCollision(getBall(), player.getPaddle())) {
            if (Collision.isTopOrBottomCollision(getBall(), player.getPaddle())) {
                getBall().reactToPaddleHit(player.getPaddle());
            } else {
                getBall().inverseHozSpeed();
            }
            setLastPlayerToHitPaddle(player);
            return true;
        }

        return false;
    }

    private void reverseYBodies(GameStateUpdateAction updateState) {
        for (Player p : gameSession.getPlayers()) {
            p.getPaddle().reverseY();
        }
        for (Brick b : updateState.bricks) {
            b.reverseY();
        }
        updateState.ball.reverseY();
    }

    public void initRun() {
        setInitRun(true);
        run();
        setInitRun(false);
    }

    @Override
    public void run() {
        GameStateUpdateAction updateState = new GameStateUpdateAction(getBall(), getGameSession().getCountDown());

        if (prevTime != gameSession.getTime()) {
            updateState.setTime(gameSession.getTime());
            prevTime = gameSession.getTime();
        }

        Iterator<Power> i = powers.iterator();
        while (i.hasNext()) {
            Power power = i.next();
            power.updateEffect(this);
            if (!power.isActive()) {
                //System.out.println("Remove power " + power.getClass());//TODO temp
                i.remove();
            }
            //System.out.println("---->  " + power.getClass()); //TODO temp
        }
        if (getGameSession().getCountDown() > 0) {
            if (isInitRun()) {
                for (Player p : gameSession.getPlayers()) {
                    updateState.addPaddle(p.getPaddle());
                }
                for (BrickRow br : getField().getBrickRows()) {
                    for (Brick b : br.getBricks()) {
                        if (b != null) {
                            updateState.addBrick(b);
                        }
                    }
                }
            }
        } else {
            for (Player p : gameSession.getPlayers()) {
                Paddle paddle = p.getPaddle();
                boolean hasMoved = paddle.move();
                if (hasMoved) {
                    updateState.addPaddle(paddle);
                }
            }
            runLoop(updateState);
        }

        for (Player p : gameSession.getPlayers()) {
            updateState.addScore(p.getScore().getPoints());
        }

        if (getGameSession().playerCount() > 1) {
            MultiplayerGame mg = (MultiplayerGame) getGameSession();

            mg.getBottomPlayer().getUser().getClient().sendAction(updateState);
            reverseYBodies(updateState);
            Collections.reverse(updateState.paddles);
            mg.getTopPlayer().getUser().getClient().sendAction(updateState);
            reverseYBodies(updateState);
        } else {
            getGameSession().broadcastAction(updateState);
        }
    }

    protected abstract void runLoop(GameStateUpdateAction updateState);

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Field getField() {
        return field;
    }

    public Game getGameSession() {
        return gameSession;
    }

    public Player getLastPlayerToHitPaddle() {
        return lastPlayerToHitPaddle;
    }

    protected void setLastPlayerToHitPaddle(Player lastPlayerToHitPaddle) {
        this.lastPlayerToHitPaddle = lastPlayerToHitPaddle;
    }

    public boolean isInitRun() {
        return initRun;
    }

    protected void setInitRun(boolean initRun) {
        this.initRun = initRun;
    }

    protected int getBrickHits() {
        return brickHits;
    }
}
