package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

import java.util.Map;

public class Closure implements Expr {
    public Map<String, Expr> env;
    public Fun fun;

    public Closure(Map<String, Expr> env, Fun fun) {
        this.env = env;
        this.fun = fun;
    }

    @Override
    public Expr accept(Visitor v) {
        // Some pitfall here since the `accept` function dispatch this object to `visitClosure`
        // Maybe better refactor the evaluations into Visitor instead of making them in Exprs?
        return v.visitClosure(this);
    }
}
