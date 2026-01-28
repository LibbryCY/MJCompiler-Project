// generated with ast extension for cup
// version 0.8
// 28/0/2026 21:31:48


package rs.ac.bg.etf.pp1.ast;

public class Mulop_mod extends Mulop {

    public Mulop_mod () {
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
        buffer.append("Mulop_mod(\n");

        buffer.append(tab);
        buffer.append(") [Mulop_mod]");
        return buffer.toString();
    }
}
