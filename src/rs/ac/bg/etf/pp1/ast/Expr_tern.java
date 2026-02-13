// generated with ast extension for cup
// version 0.8
// 13/1/2026 15:4:11


package rs.ac.bg.etf.pp1.ast;

public class Expr_tern extends Expr {

    private TernaryCondition TernaryCondition;
    private TernExpr1 TernExpr1;
    private TernExpr2 TernExpr2;

    public Expr_tern (TernaryCondition TernaryCondition, TernExpr1 TernExpr1, TernExpr2 TernExpr2) {
        this.TernaryCondition=TernaryCondition;
        if(TernaryCondition!=null) TernaryCondition.setParent(this);
        this.TernExpr1=TernExpr1;
        if(TernExpr1!=null) TernExpr1.setParent(this);
        this.TernExpr2=TernExpr2;
        if(TernExpr2!=null) TernExpr2.setParent(this);
    }

    public TernaryCondition getTernaryCondition() {
        return TernaryCondition;
    }

    public void setTernaryCondition(TernaryCondition TernaryCondition) {
        this.TernaryCondition=TernaryCondition;
    }

    public TernExpr1 getTernExpr1() {
        return TernExpr1;
    }

    public void setTernExpr1(TernExpr1 TernExpr1) {
        this.TernExpr1=TernExpr1;
    }

    public TernExpr2 getTernExpr2() {
        return TernExpr2;
    }

    public void setTernExpr2(TernExpr2 TernExpr2) {
        this.TernExpr2=TernExpr2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TernaryCondition!=null) TernaryCondition.accept(visitor);
        if(TernExpr1!=null) TernExpr1.accept(visitor);
        if(TernExpr2!=null) TernExpr2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TernaryCondition!=null) TernaryCondition.traverseTopDown(visitor);
        if(TernExpr1!=null) TernExpr1.traverseTopDown(visitor);
        if(TernExpr2!=null) TernExpr2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TernaryCondition!=null) TernaryCondition.traverseBottomUp(visitor);
        if(TernExpr1!=null) TernExpr1.traverseBottomUp(visitor);
        if(TernExpr2!=null) TernExpr2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr_tern(\n");

        if(TernaryCondition!=null)
            buffer.append(TernaryCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernExpr1!=null)
            buffer.append(TernExpr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernExpr2!=null)
            buffer.append(TernExpr2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr_tern]");
        return buffer.toString();
    }
}
