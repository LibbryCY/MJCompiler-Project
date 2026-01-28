// generated with ast extension for cup
// version 0.8
// 28/0/2026 21:31:48


package rs.ac.bg.etf.pp1.ast;

public class ReturnExpression_non_expr extends ReturnExpression {

    public ReturnExpression_non_expr () {
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
        buffer.append("ReturnExpression_non_expr(\n");

        buffer.append(tab);
        buffer.append(") [ReturnExpression_non_expr]");
        return buffer.toString();
    }
}
