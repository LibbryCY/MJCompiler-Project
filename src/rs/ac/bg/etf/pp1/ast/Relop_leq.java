// generated with ast extension for cup
// version 0.8
// 26/0/2026 19:56:4


package src.rs.ac.bg.etf.pp1.ast;

public class Relop_leq extends Relop {

    public Relop_leq () {
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
        buffer.append("Relop_leq(\n");

        buffer.append(tab);
        buffer.append(") [Relop_leq]");
        return buffer.toString();
    }
}
