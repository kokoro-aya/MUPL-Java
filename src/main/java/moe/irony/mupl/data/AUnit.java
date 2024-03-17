package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class AUnit implements Expr {
    public AUnit() {
    }

    @Override
    public Expr accept(Visitor v) {
        return v.visitAUnit(this);
    }
}
