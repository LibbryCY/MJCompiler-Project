// generated with ast extension for cup
// version 0.8
// 8/1/2026 14:28:48


package rs.ac.bg.etf.pp1.ast;

public class Relop_neq extends Relop {

    public Relop_neq () {
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
        buffer.append("Relop_neq(\n");

        buffer.append(tab);
        buffer.append(") [Relop_neq]");
        return buffer.toString();
    }
}
