package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Fst implements Expr {
    Expr e;

    public Fst(Expr e) {
        this.e = e;
    }

    @Override
    public Expr accept(Visitor v) {
        var tuple = e.accept(v);
        return v.visitFst(new Fst(tuple));
    }
}
