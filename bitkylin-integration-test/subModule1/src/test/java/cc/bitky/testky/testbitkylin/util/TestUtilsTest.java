package cc.bitky.testky.testbitkylin.util;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author limingliang
 */
public class TestUtilsTest extends TestCase {

    @Test
    public void testFetchTwoStatic() {
        System.out.println("testFetchTwoStatic: " + TestUtils.fetchTwoStatic());
    }
}
