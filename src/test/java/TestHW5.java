import moe.irony.mupl.Lib;
import moe.irony.mupl.Pair;
import moe.irony.mupl.Visitor;
import moe.irony.mupl.data.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
        var expr = Lib.ifAUnit(new Int(1), new Int(2), new Int(3));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 3);
        } else {
            fail();
        }
    }

    @Test
    public void testMLetStar() {
        var expr = Lib.MLetStar(
                List.of(new Pair<String, Expr>("x", new Int(10))),
                new Var("x"));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 10);
        } else {
            fail();
        }
    }

    @Test
    public void testIfEq() {
        var expr = Lib.IfEq(new Int(1), new Int(2), new Int(3), new Int(4));
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof Int res) {
            assertEquals(res.value, 4);
        } else {
            fail();
        }
    }

    @Test
    public void testMuplMap() {
        var expr = new Call(new Call(Lib.muplMap(),
                new Fun("#f", "x", new Add(new Var("x"), new Int(7)))),
                new APair(new Int(1), new AUnit())
                );
        var visitor = new Visitor();
        if (expr.accept(visitor) instanceof APair res) {
            assertTrue(res.e1 instanceof Int i && i.value == 8);
            assertInstanceOf(AUnit.class, res.e2);
        } else {
            fail();
        }
    }

    @Test
    public void testCombined() {
        var expr = new Call(new Call(Lib.muplMapAddN(), new Int(7)),
                        Lib.javaListToMuplList(
                                List.of(new Int(3), new Int(4), new Int(9))));

        var res = expr.accept(new Visitor());

        var resJava = Lib.muplListToJavaList(res);

        assertEquals(resJava.size(), 3);
        if (resJava.get(0) instanceof Int i) {
            assertEquals(i.value, 10);
        }
        if (resJava.get(1) instanceof Int i) {
            assertEquals(i.value, 11);
        }
        if (resJava.get(2) instanceof Int i) {
            assertEquals(i.value, 16);
        }

    }


}
