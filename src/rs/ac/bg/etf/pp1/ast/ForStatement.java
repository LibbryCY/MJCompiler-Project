// generated with ast extension for cup
// version 0.8
// 17/1/2026 20:27:46


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends MatchedStatement {

    private ForDesignatorStatement ForDesignatorStatement;
    private ForStart ForStart;
    private ForCondition ForCondition;
    private ForStepStart ForStepStart;
    private ForStep ForStep;
    private ForBodyStart ForBodyStart;
    private MatchedStatement MatchedStatement;

    public ForStatement (ForDesignatorStatement ForDesignatorStatement, ForStart ForStart, ForCondition ForCondition, ForStepStart ForStepStart, ForStep ForStep, ForBodyStart ForBodyStart, MatchedStatement MatchedStatement) {
        this.ForDesignatorStatement=ForDesignatorStatement;
        if(ForDesignatorStatement!=null) ForDesignatorStatement.setParent(this);
        this.ForStart=ForStart;
        if(ForStart!=null) ForStart.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForStepStart=ForStepStart;
        if(ForStepStart!=null) ForStepStart.setParent(this);
        this.ForStep=ForStep;
        if(ForStep!=null) ForStep.setParent(this);
        this.ForBodyStart=ForBodyStart;
        if(ForBodyStart!=null) ForBodyStart.setParent(this);
        this.MatchedStatement=MatchedStatement;
        if(MatchedStatement!=null) MatchedStatement.setParent(this);
    }

    public ForDesignatorStatement getForDesignatorStatement() {
        return ForDesignatorStatement;
    }

    public void setForDesignatorStatement(ForDesignatorStatement ForDesignatorStatement) {
        this.ForDesignatorStatement=ForDesignatorStatement;
    }

    public ForStart getForStart() {
        return ForStart;
    }

    public void setForStart(ForStart ForStart) {
        this.ForStart=ForStart;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForStepStart getForStepStart() {
        return ForStepStart;
    }

    public void setForStepStart(ForStepStart ForStepStart) {
        this.ForStepStart=ForStepStart;
    }

    public ForStep getForStep() {
        return ForStep;
    }

    public void setForStep(ForStep ForStep) {
        this.ForStep=ForStep;
    }

    public ForBodyStart getForBodyStart() {
        return ForBodyStart;
    }

    public void setForBodyStart(ForBodyStart ForBodyStart) {
        this.ForBodyStart=ForBodyStart;
    }

    public MatchedStatement getMatchedStatement() {
        return MatchedStatement;
    }

    public void setMatchedStatement(MatchedStatement MatchedStatement) {
        this.MatchedStatement=MatchedStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForDesignatorStatement!=null) ForDesignatorStatement.accept(visitor);
        if(ForStart!=null) ForStart.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForStepStart!=null) ForStepStart.accept(visitor);
        if(ForStep!=null) ForStep.accept(visitor);
        if(ForBodyStart!=null) ForBodyStart.accept(visitor);
        if(MatchedStatement!=null) MatchedStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForDesignatorStatement!=null) ForDesignatorStatement.traverseTopDown(visitor);
        if(ForStart!=null) ForStart.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForStepStart!=null) ForStepStart.traverseTopDown(visitor);
        if(ForStep!=null) ForStep.traverseTopDown(visitor);
        if(ForBodyStart!=null) ForBodyStart.traverseTopDown(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForDesignatorStatement!=null) ForDesignatorStatement.traverseBottomUp(visitor);
        if(ForStart!=null) ForStart.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForStepStart!=null) ForStepStart.traverseBottomUp(visitor);
        if(ForStep!=null) ForStep.traverseBottomUp(visitor);
        if(ForBodyStart!=null) ForBodyStart.traverseBottomUp(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForDesignatorStatement!=null)
            buffer.append(ForDesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStart!=null)
            buffer.append(ForStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStepStart!=null)
            buffer.append(ForStepStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStep!=null)
            buffer.append(ForStep.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForBodyStart!=null)
            buffer.append(ForBodyStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement!=null)
            buffer.append(MatchedStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
