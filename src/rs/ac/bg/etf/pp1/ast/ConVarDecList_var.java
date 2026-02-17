// generated with ast extension for cup
// version 0.8
// 17/1/2026 20:27:46


package rs.ac.bg.etf.pp1.ast;

public class ConVarDecList_var extends ConVarDecList {

    private VarDeclList VarDeclList;
    private ConVarDecList ConVarDecList;

    public ConVarDecList_var (VarDeclList VarDeclList, ConVarDecList ConVarDecList) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.ConVarDecList=ConVarDecList;
        if(ConVarDecList!=null) ConVarDecList.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
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
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(ConVarDecList!=null) ConVarDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConVarDecList_var(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConVarDecList!=null)
            buffer.append(ConVarDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConVarDecList_var]");
        return buffer.toString();
    }
}
