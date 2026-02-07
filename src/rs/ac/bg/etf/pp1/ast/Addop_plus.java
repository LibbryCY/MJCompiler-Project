// generated with ast extension for cup
// version 0.8
// 6/1/2026 14:36:46


package rs.ac.bg.etf.pp1.ast;

public class Addop_plus extends Addop {

    public Addop_plus () {
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
        buffer.append("Addop_plus(\n");

        buffer.append(tab);
        buffer.append(") [Addop_plus]");
        return buffer.toString();
    }
}
