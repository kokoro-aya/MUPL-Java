package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class IsAUnit implements Expr {
    Expr e;

    public IsAUnit(Expr e) {
        this.e = e;
    }

    @Override
    public Expr accept(Visitor v) {
        var vUnit = e.accept(v);
        return v.visitIsAUnit(new IsAUnit(vUnit));
    }
}
