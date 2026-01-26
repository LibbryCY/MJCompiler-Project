// generated with ast extension for cup
// version 0.8
// 26/0/2026 20:56:5


package src.rs.ac.bg.etf.pp1.ast;

public class EnumList_comma extends EnumList {

    private EnumList EnumList;
    private EnumItem EnumItem;

    public EnumList_comma (EnumList EnumList, EnumItem EnumItem) {
        this.EnumList=EnumList;
        if(EnumList!=null) EnumList.setParent(this);
        this.EnumItem=EnumItem;
        if(EnumItem!=null) EnumItem.setParent(this);
    }

    public EnumList getEnumList() {
        return EnumList;
    }

    public void setEnumList(EnumList EnumList) {
        this.EnumList=EnumList;
    }

    public EnumItem getEnumItem() {
        return EnumItem;
    }

    public void setEnumItem(EnumItem EnumItem) {
        this.EnumItem=EnumItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumList!=null) EnumList.accept(visitor);
        if(EnumItem!=null) EnumItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumList!=null) EnumList.traverseTopDown(visitor);
        if(EnumItem!=null) EnumItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumList!=null) EnumList.traverseBottomUp(visitor);
        if(EnumItem!=null) EnumItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumList_comma(\n");

        if(EnumList!=null)
            buffer.append(EnumList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumItem!=null)
            buffer.append(EnumItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumList_comma]");
        return buffer.toString();
    }
}
