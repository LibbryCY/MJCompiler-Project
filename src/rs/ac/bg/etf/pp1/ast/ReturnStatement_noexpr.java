// generated with ast extension for cup
// version 0.8
// 16/1/2026 1:42:26


package rs.ac.bg.etf.pp1.ast;

public class ReturnStatement_noexpr extends MatchedStatement {

    public ReturnStatement_noexpr () {
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
        buffer.append("ReturnStatement_noexpr(\n");

        buffer.append(tab);
        buffer.append(") [ReturnStatement_noexpr]");
        return buffer.toString();
    }
}
