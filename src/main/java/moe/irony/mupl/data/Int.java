package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Int implements Expr {
    public int value;

    public Int(int value) {
        this.value = value;
    }

    @Override
    public Expr accept(Visitor v) {
        return v.visitInt(this);
    }
}
