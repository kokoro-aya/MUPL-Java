package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class IfGreater implements Expr {
    Expr e1;
    Expr e2;
    Expr e3;
    Expr e4;

    public IfGreater(Expr e1, Expr e2, Expr e3, Expr e4) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
    }

    @Override
    public Expr accept(Visitor v) {
        var v1 = e1.accept(v);
        var v2 = e2.accept(v);
        var v3 = e3.accept(v);
        var v4 = e4.accept(v);
        return v.visitIfGreater(new IfGreater(v1, v2, v3, v4));
    }
}

