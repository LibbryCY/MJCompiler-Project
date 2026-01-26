// generated with ast extension for cup
// version 0.8
// 26/0/2026 20:56:5


package src.rs.ac.bg.etf.pp1.ast;

public class ConVarDecList_var extends ConVarDecList {

    private ConVarDecList ConVarDecList;
    private VarDeclList VarDeclList;

    public ConVarDecList_var (ConVarDecList ConVarDecList, VarDeclList VarDeclList) {
        this.ConVarDecList=ConVarDecList;
        if(ConVarDecList!=null) ConVarDecList.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public ConVarDecList getConVarDecList() {
        return ConVarDecList;
    }

    public void setConVarDecList(ConVarDecList ConVarDecList) {
        this.ConVarDecList=ConVarDecList;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConVarDecList!=null) ConVarDecList.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConVarDecList!=null) ConVarDecList.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConVarDecList_var(\n");

        if(ConVarDecList!=null)
            buffer.append(ConVarDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConVarDecList_var]");
        return buffer.toString();
    }
}
