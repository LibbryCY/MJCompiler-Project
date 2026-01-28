// generated with ast extension for cup
// version 0.8
// 28/0/2026 15:26:29


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
