// generated with ast extension for cup
// version 0.8
// 17/1/2026 20:27:46


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement_else extends UnmatchedStatement {

    private IfCondition IfCondition;
    private MatchedStatement MatchedStatement;
    private ThenEnd ThenEnd;
    private ElseStart ElseStart;
    private UnmatchedStatement UnmatchedStatement;

    public IfElseStatement_else (IfCondition IfCondition, MatchedStatement MatchedStatement, ThenEnd ThenEnd, ElseStart ElseStart, UnmatchedStatement UnmatchedStatement) {
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.MatchedStatement=MatchedStatement;
        if(MatchedStatement!=null) MatchedStatement.setParent(this);
        this.ThenEnd=ThenEnd;
        if(ThenEnd!=null) ThenEnd.setParent(this);
        this.ElseStart=ElseStart;
        if(ElseStart!=null) ElseStart.setParent(this);
        this.UnmatchedStatement=UnmatchedStatement;
        if(UnmatchedStatement!=null) UnmatchedStatement.setParent(this);
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public MatchedStatement getMatchedStatement() {
        return MatchedStatement;
    }

    public void setMatchedStatement(MatchedStatement MatchedStatement) {
        this.MatchedStatement=MatchedStatement;
    }

    public ThenEnd getThenEnd() {
        return ThenEnd;
    }

    public void setThenEnd(ThenEnd ThenEnd) {
        this.ThenEnd=ThenEnd;
    }

    public ElseStart getElseStart() {
        return ElseStart;
    }

    public void setElseStart(ElseStart ElseStart) {
        this.ElseStart=ElseStart;
    }

    public UnmatchedStatement getUnmatchedStatement() {
        return UnmatchedStatement;
    }

    public void setUnmatchedStatement(UnmatchedStatement UnmatchedStatement) {
        this.UnmatchedStatement=UnmatchedStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(MatchedStatement!=null) MatchedStatement.accept(visitor);
        if(ThenEnd!=null) ThenEnd.accept(visitor);
        if(ElseStart!=null) ElseStart.accept(visitor);
        if(UnmatchedStatement!=null) UnmatchedStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseTopDown(visitor);
        if(ThenEnd!=null) ThenEnd.traverseTopDown(visitor);
        if(ElseStart!=null) ElseStart.traverseTopDown(visitor);
        if(UnmatchedStatement!=null) UnmatchedStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseBottomUp(visitor);
        if(ThenEnd!=null) ThenEnd.traverseBottomUp(visitor);
        if(ElseStart!=null) ElseStart.traverseBottomUp(visitor);
        if(UnmatchedStatement!=null) UnmatchedStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement_else(\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement!=null)
            buffer.append(MatchedStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ThenEnd!=null)
            buffer.append(ThenEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseStart!=null)
            buffer.append(ElseStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(UnmatchedStatement!=null)
            buffer.append(UnmatchedStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement_else]");
        return buffer.toString();
    }
}
