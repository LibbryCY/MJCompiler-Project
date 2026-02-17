// generated with ast extension for cup
// version 0.8
// 17/1/2026 14:50:28


package rs.ac.bg.etf.pp1.ast;

public class ContinueStatement extends MatchedStatement {

    public ContinueStatement () {
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
        buffer.append("ContinueStatement(\n");

        buffer.append(tab);
        buffer.append(") [ContinueStatement]");
        return buffer.toString();
    }
}
