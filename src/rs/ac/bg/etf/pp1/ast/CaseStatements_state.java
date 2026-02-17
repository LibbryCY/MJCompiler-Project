// generated with ast extension for cup
// version 0.8
// 17/1/2026 20:27:46


package rs.ac.bg.etf.pp1.ast;

public class CaseStatements_state extends CaseStatements {

    private CaseStatements CaseStatements;
    private Statement Statement;

    public CaseStatements_state (CaseStatements CaseStatements, Statement Statement) {
        this.CaseStatements=CaseStatements;
        if(CaseStatements!=null) CaseStatements.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public CaseStatements getCaseStatements() {
        return CaseStatements;
    }

    public void setCaseStatements(CaseStatements CaseStatements) {
        this.CaseStatements=CaseStatements;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseStatements!=null) CaseStatements.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseStatements!=null) CaseStatements.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseStatements!=null) CaseStatements.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseStatements_state(\n");

        if(CaseStatements!=null)
            buffer.append(CaseStatements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseStatements_state]");
        return buffer.toString();
    }
}
