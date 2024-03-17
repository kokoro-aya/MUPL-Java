package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Snd implements Expr {
    public Expr e;

    public Snd(Expr e) {
        this.e = e;
    }

    @Override
    public Expr accept(Visitor v) {
        var tuple = e.accept(v);
        return v.visitSnd(new Snd(tuple));
    }
}
