// generated with ast extension for cup
// version 0.8
// 5/1/2026 18:54:37


package rs.ac.bg.etf.pp1.ast;

public class ConDeclMore_comma extends ConDeclMore {

    private ConDecl ConDecl;
    private ConDeclMore ConDeclMore;

    public ConDeclMore_comma (ConDecl ConDecl, ConDeclMore ConDeclMore) {
        this.ConDecl=ConDecl;
        if(ConDecl!=null) ConDecl.setParent(this);
        this.ConDeclMore=ConDeclMore;
        if(ConDeclMore!=null) ConDeclMore.setParent(this);
    }

    public ConDecl getConDecl() {
        return ConDecl;
    }

    public void setConDecl(ConDecl ConDecl) {
        this.ConDecl=ConDecl;
    }

    public ConDeclMore getConDeclMore() {
        return ConDeclMore;
    }

    public void setConDeclMore(ConDeclMore ConDeclMore) {
        this.ConDeclMore=ConDeclMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConDecl!=null) ConDecl.accept(visitor);
        if(ConDeclMore!=null) ConDeclMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConDecl!=null) ConDecl.traverseTopDown(visitor);
        if(ConDeclMore!=null) ConDeclMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConDecl!=null) ConDecl.traverseBottomUp(visitor);
        if(ConDeclMore!=null) ConDeclMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConDeclMore_comma(\n");

        if(ConDecl!=null)
            buffer.append(ConDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConDeclMore!=null)
            buffer.append(ConDeclMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConDeclMore_comma]");
        return buffer.toString();
    }
}
