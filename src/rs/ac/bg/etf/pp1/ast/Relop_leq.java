// generated with ast extension for cup
// version 0.8
// 10/1/2026 20:16:45


package rs.ac.bg.etf.pp1.ast;

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
