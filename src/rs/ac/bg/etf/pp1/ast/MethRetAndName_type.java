// generated with ast extension for cup
// version 0.8
// 17/1/2026 14:50:28


package rs.ac.bg.etf.pp1.ast;

public class MethRetAndName_type extends MethRetAndName {

    private Type Type;
    private String I2;

    public MethRetAndName_type (Type Type, String I2) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethRetAndName_type(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethRetAndName_type]");
        return buffer.toString();
    }
}
