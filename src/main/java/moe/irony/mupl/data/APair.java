package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class APair implements Expr {
    Expr e1;
    Expr e2;

    public APair(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Expr accept(Visitor v) {
        var v1 = e1.accept(v);
        var v2 = e2.accept(v);
        return v.visitAPair(new APair(v1, v2));
    }
}
