// generated with ast extension for cup
// version 0.8
// 26/0/2026 20:56:5


package src.rs.ac.bg.etf.pp1.ast;

public class Case implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Integer N1;
    private CaseStatements CaseStatements;

    public Case (Integer N1, CaseStatements CaseStatements) {
        this.N1=N1;
        this.CaseStatements=CaseStatements;
        if(CaseStatements!=null) CaseStatements.setParent(this);
    }

    public Integer getN1() {
        return N1;
    }

    public void setN1(Integer N1) {
        this.N1=N1;
    }

    public CaseStatements getCaseStatements() {
        return CaseStatements;
    }

    public void setCaseStatements(CaseStatements CaseStatements) {
        this.CaseStatements=CaseStatements;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseStatements!=null) CaseStatements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseStatements!=null) CaseStatements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseStatements!=null) CaseStatements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Case(\n");

        buffer.append(" "+tab+N1);
        buffer.append("\n");

        if(CaseStatements!=null)
            buffer.append(CaseStatements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Case]");
        return buffer.toString();
    }
}
