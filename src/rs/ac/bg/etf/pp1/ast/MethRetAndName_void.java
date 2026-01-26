// generated with ast extension for cup
// version 0.8
// 26/0/2026 19:56:4


package src.rs.ac.bg.etf.pp1.ast;

public class MethRetAndName_void extends MethRetAndName {

    private String I1;

    public MethRetAndName_void (String I1) {
        this.I1=I1;
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethRetAndName_void(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethRetAndName_void]");
        return buffer.toString();
    }
}
