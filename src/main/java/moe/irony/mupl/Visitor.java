package moe.irony.mupl;

import moe.irony.mupl.data.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Visitor {

    Map<String, Expr> env = new HashMap<>();

    public Visitor() {

    }

    public Visitor(Map<String, Expr> context) {
        this.env = context;
    }

    private Expr envLookup(String str) {
        if (env.containsKey(str)) {
            return env.get(str);
        } else {
            throw new IllegalStateException("unbound variable during evaluation " + str);
        }
    }


    public Expr visitVar(Var v) {
        return envLookup(v.name);
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
        var v1 = i.e1.accept(this);
        var v2 = i.e2.accept(this);
        // The e3 and e4 will only be evaluated accordingly to the result of
        // v1 and v2 (hence evaluations of e1 and e2)
        if (v1 instanceof Int iv1 && v2 instanceof Int iv2) {
            if (iv1.value > iv2.value) {
                return i.e3.accept(this);
            } else {
                return i.e4.accept(this);
            }
        } else {
            throw new IllegalStateException("MUPL ifgreater operation applied to non-number");
        }
    }

    public Expr visitMLet(MLet l) {
        var label = l.var;
        var ex = l.e;
        var newEnv = new HashMap<>(this.env);
        newEnv.put(label, ex);
        // Tricky part of MLet to evaluated an augmented environment
        return l.body.accept(new Visitor(newEnv));
    }

    public Expr visitFun(Fun f) {
        return new Closure(this.env, f);
    }

    public Expr visitCall(Call c) {
        var vFn = c.funexp;
        var vAct = c.actual;
        if (vFn instanceof Closure clos) {
            var closFun = clos.fun;
            var closEnv = clos.env;
            var funName = closFun.nameopt;
            var funArg = closFun.formal;

            var newEnv = new HashMap<String, Expr>();
            if (!funName.equals("#f")) {
                newEnv.put(funName, vFn);
            }
            newEnv.put(funArg, vAct);
            newEnv.putAll(closEnv);

            return closFun.body.accept(new Visitor(newEnv));
        } else {
            throw new IllegalStateException("MUPL call operation must have first subexpression evaluated to closure");
        }
    }

    public Expr visitAPair(APair a) {
        var v1 = a.e1;
        var v2 = a.e2;
        return new APair(v1, v2);
    }

    public Expr visitFst(Fst f) {
        var v = f.e;
        if (v instanceof APair ap) {
            return ap.e1;
        } else {
            throw new IllegalStateException("MUPL fst operation applied to non-pair");
        }
    }

    public Expr visitSnd(Snd s) {
        var v = s.e;
        if (v instanceof APair ap) {
            return ap.e2;
        } else {
            throw new IllegalStateException("MUPL snd operation applied to non-pair");
        }
    }

    public Expr visitAUnit(AUnit a) {
        return a;
    }

    public Expr visitClosure(Closure c) {
        return c;
    }

    public Expr visitIsAUnit(IsAUnit i) {
        var v = i.e;
        if (v instanceof AUnit au) {
            return new Int(1);
        } else {
            return new Int(0);
        }
    }

}
