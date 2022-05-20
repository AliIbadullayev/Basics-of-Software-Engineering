package web;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManagedBeanTest {

    private static ManagedBean bean;

    @BeforeClass
    public static void setUp() {
        bean = new ManagedBean();
    }

    @Test
    public void checkRectangle_shouldPass() {
        int x = -2;
        float y = 0.5f;
        int r = 3;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkRectangle_shouldFail() {
        int x = -3;
        float y = 4;
        int r = 4;
        assertFalse(bean.isHit(x, y, r));
    }

    @Test
    public void checkTriangle_shouldPass() {
        int x = -1;
        float y = -0.5f;
        int r = 5;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkTriangle_shouldFail() {
        int x = -2;
        float y = -2;
        int r = 1;
        assertFalse(bean.isHit(x, y, r));
    }

    @Test
    public void checkCircle_shouldPass() {
        int x = 1;
        float y = -1.5f;
        int r = 3;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkCircle_shouldFail() {
        int x = 2;
        float y = -2;
        int r = 1;
        assertFalse(bean.isHit(x, y, r));
    }

    @Test
    public void checkEmptySpase_shouldFail() {
        int x = 1;
        float y = 1;
        int r = 1;
        assertFalse(bean.isHit(x, y, r));
    }

    @Test
    public void checkCenter_shouldPass() {
        int x = 0;
        float y = 0;
        int r = 1;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkEdgeOfRectangle_shouldPass() {
        int x = 0;
        float y = 0.5f;
        int r = 2;
        assertTrue(bean.isHit(x, y, r));
        y = 1;
        assertTrue(bean.isHit(x, y, r));
        x = -1;
        assertTrue(bean.isHit(x, y, r));
        x = -2;
        assertTrue(bean.isHit(x, y, r));
        y = 0;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkEdgeOfRectangle_shouldFail() {
        int x = 1;
        float y = 0.5f;
        int r = 2;
        assertFalse(bean.isHit(x, y, r));
        y = 1.1f;
        assertFalse(bean.isHit(x, y, r));
        x = -1;
        assertFalse(bean.isHit(x, y, r));
        x = -3;
        assertFalse(bean.isHit(x, y, r));
        y = -0.1f;
        assertFalse(bean.isHit(x, y, r));
    }

    @Test
    public void checkEdgeOfTriangle_shouldPass(){
        int x = -1;
        float y = -1;
        int r = 3;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkEdgeOfTriangle_shouldFail(){
        int x = -1;
        float y = -1.1f;
        int r = 3;
        assertFalse(bean.isHit(x, y, r));
    }

    @Test
    public void checkEdgeOfCircle_shouldPass(){
        int x = 3;
        float y = -4;
        int r = 5;
        assertTrue(bean.isHit(x, y, r));
    }

    @Test
    public void checkEdgeOfCircle_shouldFail(){
        int x = 3;
        float y = -4.1f;
        int r = 5;
        assertFalse(bean.isHit(x, y, r));
    }
}