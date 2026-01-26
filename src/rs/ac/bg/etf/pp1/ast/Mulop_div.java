// generated with ast extension for cup
// version 0.8
// 26/0/2026 20:56:5


package src.rs.ac.bg.etf.pp1.ast;

public class Mulop_div extends Mulop {

    public Mulop_div () {
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
        buffer.append("Mulop_div(\n");

        buffer.append(tab);
        buffer.append(") [Mulop_div]");
        return buffer.toString();
    }
}
