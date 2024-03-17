package moe.irony.mupl;

import moe.irony.mupl.data.Add;
import moe.irony.mupl.data.Int;

public class Main {
    public static void main(String[] args) {
        var expr = new Add(new Add(new Int(1), new Int(2)), new Add(new Int(3), new Int(4)));
        var visitor = new Visitor();
        var res = expr.accept(visitor);

        if (res instanceof Int v) {
            System.out.println(v.value);
        }

    }
}