// generated with ast extension for cup
// version 0.8
// 25/0/2026 23:0:1


package src.rs.ac.bg.etf.pp1.ast;

public class Expr_term extends Expr {

    private Negativ Negativ;
    private TermList TermList;

    public Expr_term (Negativ Negativ, TermList TermList) {
        this.Negativ=Negativ;
        if(Negativ!=null) Negativ.setParent(this);
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
    }

    public Negativ getNegativ() {
        return Negativ;
    }

    public void setNegativ(Negativ Negativ) {
        this.Negativ=Negativ;
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Negativ!=null) Negativ.accept(visitor);
        if(TermList!=null) TermList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Negativ!=null) Negativ.traverseTopDown(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Negativ!=null) Negativ.traverseBottomUp(visitor);
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr_term(\n");

        if(Negativ!=null)
            buffer.append(Negativ.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr_term]");
        return buffer.toString();
    }
}
