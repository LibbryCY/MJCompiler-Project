// generated with ast extension for cup
// version 0.8
// 4/1/2026 20:32:41


package rs.ac.bg.etf.pp1.ast;

public class CondFact_relop extends CondFact {

    private TermList TermList;
    private Relop Relop;
    private TermList TermList1;

    public CondFact_relop (TermList TermList, Relop Relop, TermList TermList1) {
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
        this.Relop=Relop;
        if(Relop!=null) Relop.setParent(this);
        this.TermList1=TermList1;
        if(TermList1!=null) TermList1.setParent(this);
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
    }

    public Relop getRelop() {
        return Relop;
    }

    public void setRelop(Relop Relop) {
        this.Relop=Relop;
    }

    public TermList getTermList1() {
        return TermList1;
    }

    public void setTermList1(TermList TermList1) {
        this.TermList1=TermList1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermList!=null) TermList.accept(visitor);
        if(Relop!=null) Relop.accept(visitor);
        if(TermList1!=null) TermList1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
        if(Relop!=null) Relop.traverseTopDown(visitor);
        if(TermList1!=null) TermList1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        if(Relop!=null) Relop.traverseBottomUp(visitor);
        if(TermList1!=null) TermList1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFact_relop(\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Relop!=null)
            buffer.append(Relop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList1!=null)
            buffer.append(TermList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFact_relop]");
        return buffer.toString();
    }
}
