package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Add implements Expr {

    public Expr e1;
    public Expr e2;

    public Add(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expr accept(Visitor v) {
        var v1 = e1.accept(v);
        var v2 = e2.accept(v);
        return v.visitAdd(new Add(v1, v2));
    }
}
