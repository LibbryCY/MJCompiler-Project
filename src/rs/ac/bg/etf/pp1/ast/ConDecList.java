// generated with ast extension for cup
// version 0.8
// 25/0/2026 23:0:1


package src.rs.ac.bg.etf.pp1.ast;

public class ConDecList implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConDecl ConDecl;
    private ConDeclMore ConDeclMore;

    public ConDecList (Type Type, ConDecl ConDecl, ConDeclMore ConDeclMore) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConDecl=ConDecl;
        if(ConDecl!=null) ConDecl.setParent(this);
        this.ConDeclMore=ConDeclMore;
        if(ConDeclMore!=null) ConDeclMore.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConDecl!=null) ConDecl.accept(visitor);
        if(ConDeclMore!=null) ConDeclMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConDecl!=null) ConDecl.traverseTopDown(visitor);
        if(ConDeclMore!=null) ConDeclMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConDecl!=null) ConDecl.traverseBottomUp(visitor);
        if(ConDeclMore!=null) ConDeclMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConDecList(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [ConDecList]");
        return buffer.toString();
    }
}
