import moe.irony.mupl.Visitor;
import moe.irony.mupl.data.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestHW5 {

    @Test
    public void testIfGreater() {
        var expr = new IfGreater(new Int(3), new Int(4), new Int(3), new Int(2));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 2);
        } else {
            fail();
        }
    }

    @Test
    public void testMLet() {
        var expr = new MLet("x", new Int(1), new Add(new Int(5), new Var("x")));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 6);
        } else {
            fail();
        }
    }

    @Test
    public void testCall() {
        var expr = new Call(new Closure(new HashMap<>(), new Fun("#f", "x", new Add(new Var("x"), new Int(7)))), new Int(1));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 8);
        } else {
            fail();
        }
    }

    @Test
    public void testSnd() {
        var expr = new Snd(new APair(new Int(1), new Int(2)));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 2);
        } else {
            fail();
        }
    }

    @Test
    public void testIsAUnit() {
        var expr = new IsAUnit(new Closure(new HashMap<>(), new Fun("#f", "x", new AUnit())));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 0);
        } else {
            fail();
        }
    }

    @Test
    public void testIfAUnit() {
        fail();
    }

    @Test
    public void testMLetStar() {
        fail();
    }

    @Test
    public void testIfEq() {
        fail();
    }

    @Test
    public void testMuplMap() {
        fail();
    }

    @Test
    public void testCombined() {
        fail();
    }


}
