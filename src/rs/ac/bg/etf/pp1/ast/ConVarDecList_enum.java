// generated with ast extension for cup
// version 0.8
// 5/1/2026 18:54:37


package rs.ac.bg.etf.pp1.ast;

public class ConVarDecList_enum extends ConVarDecList {

    private EnumDeclList EnumDeclList;
    private ConVarDecList ConVarDecList;

    public ConVarDecList_enum (EnumDeclList EnumDeclList, ConVarDecList ConVarDecList) {
        this.EnumDeclList=EnumDeclList;
        if(EnumDeclList!=null) EnumDeclList.setParent(this);
        this.ConVarDecList=ConVarDecList;
        if(ConVarDecList!=null) ConVarDecList.setParent(this);
    }

    public EnumDeclList getEnumDeclList() {
        return EnumDeclList;
    }

    public void setEnumDeclList(EnumDeclList EnumDeclList) {
        this.EnumDeclList=EnumDeclList;
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
        if(EnumDeclList!=null) EnumDeclList.accept(visitor);
        if(ConVarDecList!=null) ConVarDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclList!=null) EnumDeclList.traverseTopDown(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclList!=null) EnumDeclList.traverseBottomUp(visitor);
        if(ConVarDecList!=null) ConVarDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConVarDecList_enum(\n");

        if(EnumDeclList!=null)
            buffer.append(EnumDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConVarDecList!=null)
            buffer.append(ConVarDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConVarDecList_enum]");
        return buffer.toString();
    }
}
