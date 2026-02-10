// generated with ast extension for cup
// version 0.8
// 10/1/2026 20:16:45


package rs.ac.bg.etf.pp1.ast;

public class ConVarDecList_con extends ConVarDecList {

    private ConDecList ConDecList;
    private ConVarDecList ConVarDecList;

    public ConVarDecList_con (ConDecList ConDecList, ConVarDecList ConVarDecList) {
        this.ConDecList=ConDecList;
        if(ConDecList!=null) ConDecList.setParent(this);
        this.ConVarDecList=ConVarDecList;
        if(ConVarDecList!=null) ConVarDecList.setParent(this);
    }

    public ConDecList getConDecList() {
        return ConDecList;
    }

    public void setConDecList(ConDecList ConDecList) {
        this.ConDecList=ConDecList;
    }

    public ConVarDecList getConVarDecList() {
        return ConVarDecList;
    }

    public void setConVarDecList(ConVarDecList ConVarDecList) {
        this.ConVarDecList=ConVarDecList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConDecList!=null) ConDecList.accept(visitor);
        if(ConVarDecList!=null) ConVarDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConDecList!=null) ConDecList.traverseTopDown(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConDecList!=null) ConDecList.traverseBottomUp(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConVarDecList_con(\n");

        if(ConDecList!=null)
            buffer.append(ConDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConVarDecList!=null)
            buffer.append(ConVarDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConVarDecList_con]");
        return buffer.toString();
    }
}
