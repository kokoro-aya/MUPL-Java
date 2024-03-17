package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Call implements Expr {
    Expr funexp;
    Expr actual;

    public Call(Expr funexp, Expr actual) {
        this.funexp = funexp;
        this.actual = actual;
    }

    @Override
    public Expr accept(Visitor v) {
        var f1 = funexp.accept(v);
        var a1 = actual.accept(v);
        return v.visitCall(new Call(f1, a1));
    }
}
