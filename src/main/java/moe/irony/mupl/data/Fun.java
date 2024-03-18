package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class Fun implements Expr {
    public String nameopt;
    public String formal;
    public Expr body;

    public Fun(String nameopt, String formal, Expr body) {
        this.nameopt = nameopt;
        this.formal = formal;
        this.body = body;
    }

    @Override
    public Expr accept(Visitor v) {
        // Some pitfall here since the `accept` function dispatch this object to `visitFun`
        // Maybe better refactor the evaluations into Visitor instead of making them in Exprs?
        return v.visitFun(this);
    }
}
