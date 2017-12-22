package nu.smashit.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
    private final Set<Power> powers;
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
        setLastPlayerToHitPaddle(gm.getPlayers()[0]);
    }

    private boolean powerTypeIsActive(Power p) {
        for (Power p2 : powers) {
            if (p.getClass().equals(p2.getClass())) {
                return true;
            }
        }
        return false;
    }

    private boolean runBrickRowCollision(int i, GameStateUpdateAction updateState) {
        BrickRow brickRow = getField().getRow(i);

        if (Collision.isVerCollision(getBall(), brickRow)) {
            for (Brick brick : brickRow.getBricks()) {
                if (brick != null && !brick.isBroken() && Collision.isHorCollision(getBall(), brick)) {
                    brick.smashBrick();
                    updateState.addBrick(brick);

                    if (Collision.isTopOrBottomCollision(getBall(), brick)) {
                        getBall().inverseVerSpeed();
                    } else {
                        getBall().inverseHorSpeed();
                    }

                    if (brick.isBroken() && getLastPlayerToHitPaddle() != null) {
                        brickHits += 1;
                        getLastPlayerToHitPaddle().getScore().addBrickSmash(brick);
                        Power power = brick.getPower();
                        if (!(power instanceof NoPower) && !powerTypeIsActive(power)) {
                            if (power.multiplePlayers) {
                                for (Player p : gameSession.getPlayers()) {
                                    power.addPlayer(p);
                                }
                            } else {
                                power.addPlayer(lastPlayerToHitPaddle);
                            }
                            powers.add(power);
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
                getBall().inverseHorSpeed();
            }
            setLastPlayerToHitPaddle(player);
            return true;
        }

        return false;
    }

    public List getScores() {
        List scores = new ArrayList<>(2);
        for (Player p : gameSession.getPlayers()) {
            scores.add(p.getScore().getPoints());
        }
        return scores;
    }

    private void addPowersForPlayer(GameStateUpdateAction updateState, Player p) {
        Set<Integer> powerSet = new HashSet<>();
        for (Power pow : powers) {
            if (pow.containsPlayer(p)) {
                powerSet.add(pow.powerID);
            }
        }
        updateState.setPowers(powerSet);
    }

    private int getCombinedPaddleHash() {
        String str = "";
        for (Player p : gameSession.getPlayers()) {
            Paddle paddle = p.getPaddle();
            str += ":" + paddle.getHash();
        }
        return str.hashCode();
    }

    public void initRun() {
        setInitRun(true);
        run();
        setInitRun(false);
    }

    @Override
    public void run() {
        GameStateUpdateAction updateState = new GameStateUpdateAction(getBall(), getGameSession().getCountDown());
        List originalScores = getScores();
        int originalPowersHash = powers.hashCode();
        int originalPaddlesHash = getCombinedPaddleHash();

        if (prevTime != gameSession.getTime()) {
            updateState.setTime(gameSession.getTime());
            prevTime = gameSession.getTime();
        }

        Iterator<Power> i = powers.iterator();
        while (i.hasNext()) {
            Power power = i.next();
            power.updateEffect(this);
            if (!power.isActive()) {
                i.remove();
            }
        }
        if (getGameSession().getCountDown() > 0) {
            if (isInitRun()) {
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
                paddle.move();
            }
            runLoop(updateState);
        }

        List newScores = getScores();
        if (isInitRun() || !originalScores.equals(newScores)) {
            updateState.addScores(newScores);
        }

        if (isInitRun() || originalPaddlesHash != getCombinedPaddleHash()) {
            for (Player p : gameSession.getPlayers()) {
                Paddle paddle = p.getPaddle();
                updateState.addPaddle(paddle);
            }
        }

        boolean hasPowersChanged = originalPowersHash != powers.hashCode();

        if (getGameSession() instanceof MultiplayerGame) {
            MultiplayerGame mg = (MultiplayerGame) getGameSession();

            // Player 0 state update
            Player p = mg.getBottomPlayer();
            if (hasPowersChanged) {
                addPowersForPlayer(updateState, p);
            }
            p.getUser().getClient().sendAction(updateState);

            // Player 1 state update
            updateState.reverseState();
            p = mg.getTopPlayer();
            updateState.clearPowers();
            if (hasPowersChanged) {
                addPowersForPlayer(updateState, p);
            }
            p.getUser().getClient().sendAction(updateState);

            // Reverse bodies back so game logic keeps working
            updateState.reverseState();
        } else {
            SingleplayerGame sg = (SingleplayerGame) getGameSession();
            if (hasPowersChanged) {
                addPowersForPlayer(updateState, sg.getPlayer());
            }
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
