package moe.irony.mupl.data;

import moe.irony.mupl.Visitor;

public interface Expr {
    Expr accept(Visitor v);
}
