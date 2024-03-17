package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Var implements Expr {
    String name;

    public Var(String name) {
        this.name = name;
    }

    @Override
    public Expr accept(Visitor v) {
        return v.visitVar(this);
    }
}
