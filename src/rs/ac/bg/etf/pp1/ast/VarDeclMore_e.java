// generated with ast extension for cup
// version 0.8
// 8/1/2026 14:28:48


package rs.ac.bg.etf.pp1.ast;

public class VarDeclMore_e extends VarDeclMore {

    public VarDeclMore_e () {
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
        buffer.append("VarDeclMore_e(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclMore_e]");
        return buffer.toString();
    }
}
