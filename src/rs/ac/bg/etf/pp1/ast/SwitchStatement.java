// generated with ast extension for cup
// version 0.8
// 17/1/2026 20:27:46


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatement extends MatchedStatement {

    private SwitchStart SwitchStart;
    private SwitchGoTo SwitchGoTo;
    private CaseList CaseList;

    public SwitchStatement (SwitchStart SwitchStart, SwitchGoTo SwitchGoTo, CaseList CaseList) {
        this.SwitchStart=SwitchStart;
        if(SwitchStart!=null) SwitchStart.setParent(this);
        this.SwitchGoTo=SwitchGoTo;
        if(SwitchGoTo!=null) SwitchGoTo.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public SwitchStart getSwitchStart() {
        return SwitchStart;
    }

    public void setSwitchStart(SwitchStart SwitchStart) {
        this.SwitchStart=SwitchStart;
    }

    public SwitchGoTo getSwitchGoTo() {
        return SwitchGoTo;
    }

    public void setSwitchGoTo(SwitchGoTo SwitchGoTo) {
        this.SwitchGoTo=SwitchGoTo;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchStart!=null) SwitchStart.accept(visitor);
        if(SwitchGoTo!=null) SwitchGoTo.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchStart!=null) SwitchStart.traverseTopDown(visitor);
        if(SwitchGoTo!=null) SwitchGoTo.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchStart!=null) SwitchStart.traverseBottomUp(visitor);
        if(SwitchGoTo!=null) SwitchGoTo.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatement(\n");

        if(SwitchStart!=null)
            buffer.append(SwitchStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchGoTo!=null)
            buffer.append(SwitchGoTo.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatement]");
        return buffer.toString();
    }
}
