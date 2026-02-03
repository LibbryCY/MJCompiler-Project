// generated with ast extension for cup
// version 0.8
// 3/1/2026 14:41:21


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends MatchedStatement {

    private Condition Condition;
    private MatchedStatement MatchedStatement;
    private MatchedStatement MatchedStatement1;

    public IfElseStatement (Condition Condition, MatchedStatement MatchedStatement, MatchedStatement MatchedStatement1) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.MatchedStatement=MatchedStatement;
        if(MatchedStatement!=null) MatchedStatement.setParent(this);
        this.MatchedStatement1=MatchedStatement1;
        if(MatchedStatement1!=null) MatchedStatement1.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public MatchedStatement getMatchedStatement() {
        return MatchedStatement;
    }

    public void setMatchedStatement(MatchedStatement MatchedStatement) {
        this.MatchedStatement=MatchedStatement;
    }

    public MatchedStatement getMatchedStatement1() {
        return MatchedStatement1;
    }

    public void setMatchedStatement1(MatchedStatement MatchedStatement1) {
        this.MatchedStatement1=MatchedStatement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(MatchedStatement!=null) MatchedStatement.accept(visitor);
        if(MatchedStatement1!=null) MatchedStatement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseTopDown(visitor);
        if(MatchedStatement1!=null) MatchedStatement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseBottomUp(visitor);
        if(MatchedStatement1!=null) MatchedStatement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement!=null)
            buffer.append(MatchedStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement1!=null)
            buffer.append(MatchedStatement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
