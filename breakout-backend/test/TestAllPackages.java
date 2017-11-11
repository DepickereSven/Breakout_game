import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Jonas
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    nu.smashit.core.TestSinglePlayer.class, 
    nu.smashit.core.TestMultiplayer.class,
    nu.smashit.core.bodies.TestBall.class,
    nu.smashit.core.bodies.TestBrick.class,
    nu.smashit.core.bodies.TestField.class,
    nu.smashit.core.bodies.TestMobableBody.class,
    nu.smashit.core.bodies.TestPaddle.class,
    nu.smashit.socket.TestClient.class,
    nu.smashit.socket.TestClientManager.class
})
public class TestAllPackages {

}