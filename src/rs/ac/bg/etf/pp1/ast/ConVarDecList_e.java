// generated with ast extension for cup
// version 0.8
// 16/1/2026 2:9:38


package rs.ac.bg.etf.pp1.ast;

public class ConVarDecList_e extends ConVarDecList {

    public ConVarDecList_e () {
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
        buffer.append("ConVarDecList_e(\n");

        buffer.append(tab);
        buffer.append(") [ConVarDecList_e]");
        return buffer.toString();
    }
}
