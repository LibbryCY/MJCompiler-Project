// generated with ast extension for cup
// version 0.8
// 28/0/2026 15:26:29


package rs.ac.bg.etf.pp1.ast;

public class Factor implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Unary Unary;
    private FactorUnar FactorUnar;

    public Factor (Unary Unary, FactorUnar FactorUnar) {
        this.Unary=Unary;
        if(Unary!=null) Unary.setParent(this);
        this.FactorUnar=FactorUnar;
        if(FactorUnar!=null) FactorUnar.setParent(this);
    }

    public Unary getUnary() {
        return Unary;
    }

    public void setUnary(Unary Unary) {
        this.Unary=Unary;
    }

    public FactorUnar getFactorUnar() {
        return FactorUnar;
    }

    public void setFactorUnar(FactorUnar FactorUnar) {
        this.FactorUnar=FactorUnar;
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
        if(Unary!=null) Unary.accept(visitor);
        if(FactorUnar!=null) FactorUnar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Unary!=null) Unary.traverseTopDown(visitor);
        if(FactorUnar!=null) FactorUnar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Unary!=null) Unary.traverseBottomUp(visitor);
        if(FactorUnar!=null) FactorUnar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Factor(\n");

        if(Unary!=null)
            buffer.append(Unary.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorUnar!=null)
            buffer.append(FactorUnar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Factor]");
        return buffer.toString();
    }
}
