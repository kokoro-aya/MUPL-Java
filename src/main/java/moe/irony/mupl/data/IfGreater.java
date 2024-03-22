package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class IfGreater implements Expr {
    public Expr e1;
    public Expr e2;
    public Expr e3;
    public Expr e4;

    public IfGreater(Expr e1, Expr e2, Expr e3, Expr e4) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
    }

    @Override
    public Expr accept(Visitor v) {
        // This is where the evaluation is delicate
        // In fact, common visitor implementation put `accept` for components
        // in the `accept` function of parent, which means the expression is
        // evaluated instantly hence we lost the delayed semantic
        return v.visitIfGreater(this);
    }
}

