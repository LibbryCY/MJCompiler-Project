// generated with ast extension for cup
// version 0.8
// 3/1/2026 14:41:21


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclList_e extends MethodDeclList {

    public MethodDeclList_e () {
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
        buffer.append("MethodDeclList_e(\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclList_e]");
        return buffer.toString();
    }
}
