// generated with ast extension for cup
// version 0.8
// 25/0/2026 23:42:36


package src.rs.ac.bg.etf.pp1.ast;

public class ConVarDecList_con extends ConVarDecList {

    private ConVarDecList ConVarDecList;
    private ConDecList ConDecList;

    public ConVarDecList_con (ConVarDecList ConVarDecList, ConDecList ConDecList) {
        this.ConVarDecList=ConVarDecList;
        if(ConVarDecList!=null) ConVarDecList.setParent(this);
        this.ConDecList=ConDecList;
        if(ConDecList!=null) ConDecList.setParent(this);
    }

    public ConVarDecList getConVarDecList() {
        return ConVarDecList;
    }

    public void setConVarDecList(ConVarDecList ConVarDecList) {
        this.ConVarDecList=ConVarDecList;
    }

    public ConDecList getConDecList() {
        return ConDecList;
    }

    public void setConDecList(ConDecList ConDecList) {
        this.ConDecList=ConDecList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConVarDecList!=null) ConVarDecList.accept(visitor);
        if(ConDecList!=null) ConDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseTopDown(visitor);
        if(ConDecList!=null) ConDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConVarDecList!=null) ConVarDecList.traverseBottomUp(visitor);
        if(ConDecList!=null) ConDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConVarDecList_con(\n");

        if(ConVarDecList!=null)
            buffer.append(ConVarDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConDecList!=null)
            buffer.append(ConDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConVarDecList_con]");
        return buffer.toString();
    }
}
