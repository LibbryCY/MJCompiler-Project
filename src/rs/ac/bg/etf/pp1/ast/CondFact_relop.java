// generated with ast extension for cup
// version 0.8
// 26/0/2026 20:56:5


package src.rs.ac.bg.etf.pp1.ast;

public class CondFact_relop extends CondFact {

    private NonTernaryExpr NonTernaryExpr;
    private Relop Relop;
    private NonTernaryExpr NonTernaryExpr1;

    public CondFact_relop (NonTernaryExpr NonTernaryExpr, Relop Relop, NonTernaryExpr NonTernaryExpr1) {
        this.NonTernaryExpr=NonTernaryExpr;
        if(NonTernaryExpr!=null) NonTernaryExpr.setParent(this);
        this.Relop=Relop;
        if(Relop!=null) Relop.setParent(this);
        this.NonTernaryExpr1=NonTernaryExpr1;
        if(NonTernaryExpr1!=null) NonTernaryExpr1.setParent(this);
    }

    public NonTernaryExpr getNonTernaryExpr() {
        return NonTernaryExpr;
    }

    public void setNonTernaryExpr(NonTernaryExpr NonTernaryExpr) {
        this.NonTernaryExpr=NonTernaryExpr;
    }

    public Relop getRelop() {
        return Relop;
    }

    public void setRelop(Relop Relop) {
        this.Relop=Relop;
    }

    public NonTernaryExpr getNonTernaryExpr1() {
        return NonTernaryExpr1;
    }

    public void setNonTernaryExpr1(NonTernaryExpr NonTernaryExpr1) {
        this.NonTernaryExpr1=NonTernaryExpr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonTernaryExpr!=null) NonTernaryExpr.accept(visitor);
        if(Relop!=null) Relop.accept(visitor);
        if(NonTernaryExpr1!=null) NonTernaryExpr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonTernaryExpr!=null) NonTernaryExpr.traverseTopDown(visitor);
        if(Relop!=null) Relop.traverseTopDown(visitor);
        if(NonTernaryExpr1!=null) NonTernaryExpr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonTernaryExpr!=null) NonTernaryExpr.traverseBottomUp(visitor);
        if(Relop!=null) Relop.traverseBottomUp(visitor);
        if(NonTernaryExpr1!=null) NonTernaryExpr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFact_relop(\n");

        if(NonTernaryExpr!=null)
            buffer.append(NonTernaryExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Relop!=null)
            buffer.append(Relop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NonTernaryExpr1!=null)
            buffer.append(NonTernaryExpr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFact_relop]");
        return buffer.toString();
    }
}
