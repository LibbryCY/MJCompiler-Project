// generated with ast extension for cup
// version 0.8
// 5/1/2026 18:54:37


package rs.ac.bg.etf.pp1.ast;

public class CaseList_e extends CaseList {

    public CaseList_e () {
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
        buffer.append("CaseList_e(\n");

        buffer.append(tab);
        buffer.append(") [CaseList_e]");
        return buffer.toString();
    }
}
