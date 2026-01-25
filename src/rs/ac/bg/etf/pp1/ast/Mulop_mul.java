// generated with ast extension for cup
// version 0.8
// 25/0/2026 23:0:1


package src.rs.ac.bg.etf.pp1.ast;

public class Mulop_mul extends Mulop {

    public Mulop_mul () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Mulop_mul(\n");

        buffer.append(tab);
        buffer.append(") [Mulop_mul]");
        return buffer.toString();
    }
}
