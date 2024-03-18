package moe.irony.mupl;

import moe.irony.mupl.data.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
//        var expr = new Add(new Add(new Int(1), new Int(2)), new Add(new Int(3), new Int(4)));

//        var expr = new IsAUnit(new Int(39));
//        var expr = new IfGreater(new Int(12), new Int(27), new Int(12), new Int(33));
//        var expr = new Snd(new APair(new Int(33), new Int(20)));

//        var expr = new MLet("x", new Int(1), new Add(new Int(5), new Var("x")));

        var expr = new Call(new Closure(new HashMap<>(), new Fun("#f", "x", new Add(new Var("x"), new Int(7)))), new Int(1));

        var visitor = new Visitor();
        var res = expr.accept(visitor);

        if (res instanceof Int v) {
            System.out.println(v.value);
        }

    }
}