// generated with ast extension for cup
// version 0.8
// 6/1/2026 14:36:46


package rs.ac.bg.etf.pp1.ast;

public class Relop_eq extends Relop {

    public Relop_eq () {
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
        buffer.append("Relop_eq(\n");

        buffer.append(tab);
        buffer.append(") [Relop_eq]");
        return buffer.toString();
    }
}
