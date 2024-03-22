package moe.irony.mupl;

import moe.irony.mupl.data.*;

import java.util.LinkedList;
import java.util.List;

public class Lib {

    public static Expr javaListToMuplList(List<Expr> exprs) {
        if (exprs.isEmpty()) {
            return new AUnit();
        } else {
            var head = exprs.get(0);
            return new APair(head, javaListToMuplList(exprs.subList(1, exprs.size())));
        }
    }

    public static List<Expr> muplListToJavaList(Expr expr) {
        if (expr instanceof APair p) {
            var combined = new LinkedList<Expr>();
            combined.add(p.e1);
            combined.addAll(muplListToJavaList(p.e2));
            return combined;
        } else {
            return new LinkedList<>();
        }
    }

    public static Expr ifAUnit(Expr e1, Expr e2, Expr e3) {
        return new IfGreater(new IsAUnit(e1), new Int(0), e2, e3);
    }

    public static Expr MLetStar(List<Pair<String, Expr>> lstlst, Expr e2) {
        if (lstlst.isEmpty()) {
            return e2;
        } else {
            var aPair = lstlst.get(0);
            var s = aPair.first;
            var e = aPair.second;
            return new MLet(s, e, Lib.MLetStar(lstlst.subList(1, lstlst.size()), e2));
        }
    }

    public static Expr IfEq(Expr e1, Expr e2, Expr e3, Expr e4) {
        return new MLet("_x", e1,
                new MLet("_y", e2,
                new IfGreater(new Var("_x"), new Var("_y"),
                        e4,
                        new IfGreater(new Var("_y"), new Var("_x"),
                                e4, e3)
                        )));
    }

    public static Expr muplMap() {
        return new Fun("mupl-map", "m-fun",
                new Fun("map-helper", "m-list",
                        Lib.ifAUnit(new Var("m-list"), new AUnit(),
                                new APair(new Call(new Var("m-fun"), new Fst(new Var("m-list"))),
                                        new Call(new Var("map-helper"), new Snd(new Var("m-list")))))));
    }

    public static Expr muplMapAddN() {
        return new MLet("map", muplMap(),
                new Fun("mupl-mapAddN", "n",
                        new Fun("mupl-mapAddN-inner", "xs",
                                new Call(new Call(new Var("map"),
                                        new Fun("addN", "x", new Add(new Var("x"), new Var("n")))),
                                        new Var("xs")
                                )))
                );
    }
}
