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
        var vFun = fun.accept(v);
        return new Closure(env, fun);
    }
}
