// generated with ast extension for cup
// version 0.8
// 4/1/2026 20:32:41


package rs.ac.bg.etf.pp1.ast;

public class Addop_minus extends Addop {

    public Addop_minus () {
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
        buffer.append("Addop_minus(\n");

        buffer.append(tab);
        buffer.append(") [Addop_minus]");
        return buffer.toString();
    }
}
