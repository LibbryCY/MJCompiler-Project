// generated with ast extension for cup
// version 0.8
// 17/1/2026 20:27:46


package rs.ac.bg.etf.pp1.ast;

public class Case implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private LabelNumber LabelNumber;
    private CaseStatements CaseStatements;

    public Case (LabelNumber LabelNumber, CaseStatements CaseStatements) {
        this.LabelNumber=LabelNumber;
        if(LabelNumber!=null) LabelNumber.setParent(this);
        this.CaseStatements=CaseStatements;
        if(CaseStatements!=null) CaseStatements.setParent(this);
    }

    public LabelNumber getLabelNumber() {
        return LabelNumber;
    }

    public void setLabelNumber(LabelNumber LabelNumber) {
        this.LabelNumber=LabelNumber;
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
        if(LabelNumber!=null) LabelNumber.accept(visitor);
        if(CaseStatements!=null) CaseStatements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(LabelNumber!=null) LabelNumber.traverseTopDown(visitor);
        if(CaseStatements!=null) CaseStatements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(LabelNumber!=null) LabelNumber.traverseBottomUp(visitor);
        if(CaseStatements!=null) CaseStatements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Case(\n");

        if(LabelNumber!=null)
            buffer.append(LabelNumber.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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
