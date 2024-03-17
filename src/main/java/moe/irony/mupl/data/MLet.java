package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public class MLet implements Expr {
    public String var;
    public Expr e;
    public Expr body;

    public MLet(String var, Expr e, Expr body) {
        this.var = var;
        this.e = e;
        this.body = body;
    }

    @Override
    public Expr accept(Visitor v) {
        var vE = e.accept(v);
        var vBody = body.accept(v);
        return v.visitMLet(new MLet(var, vE, vBody));
    }
}
