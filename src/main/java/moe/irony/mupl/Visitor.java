package moe.irony.mupl;

import moe.irony.mupl.data.*;

public class Visitor {

    public Expr visitVar(Var v) {
        return null;
    }

    public Expr visitInt(Int i) {
        return i;
    }

    public Expr visitAdd(Add a) {
        var v1 = a.e1;
        var v2 = a.e2;
        if (v1 instanceof Int iv1 && v2 instanceof Int iv2) {
            return new Int(iv1.value + iv2.value);
        } else {
            throw new IllegalStateException("MUPL addition applied to non-number");
        }
    }

    public Expr visitIfGreater(IfGreater i) {
        return null;
    }

    public Expr visitFun(Fun f) {
        return null;
    }

    public Expr visitCall(Call c) {
        return null;
    }

    public Expr visitMLet(MLet l) {
        return null;
    }

    public Expr visitAPair(APair a) {
        return null;
    }

    public Expr visitFst(Fst f) {
        return null;
    }

    public Expr visitSnd(Snd s) {
        return null;
    }

    public Expr visitAUnit(AUnit a) {
        return null;
    }

    public Expr visitIsAUnit(IsAUnit i) {
        return null;
    }

}
