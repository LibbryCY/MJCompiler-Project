// generated with ast extension for cup
// version 0.8
// 28/0/2026 21:31:48


package rs.ac.bg.etf.pp1.ast;

public abstract class VisitorAdaptor implements Visitor { 

    public void visit(Mulop Mulop) { }
    public void visit(MatchedStatement MatchedStatement) { }
    public void visit(FormParList FormParList) { }
    public void visit(Constant Constant) { }
    public void visit(Relop Relop) { }
    public void visit(TermList TermList) { }
    public void visit(Unary Unary) { }
    public void visit(StatementList StatementList) { }
    public void visit(FactorList FactorList) { }
    public void visit(FactorUnar FactorUnar) { }
    public void visit(FormParMore FormParMore) { }
    public void visit(Addop Addop) { }
    public void visit(ForCondition ForCondition) { }
    public void visit(CondTerm CondTerm) { }
    public void visit(ConVarDecList ConVarDecList) { }
    public void visit(Designator Designator) { }
    public void visit(CaseStatements CaseStatements) { }
    public void visit(Condition Condition) { }
    public void visit(VarDeclListRec VarDeclListRec) { }
    public void visit(ReturnExpression ReturnExpression) { }
    public void visit(CaseList CaseList) { }
    public void visit(EnumList EnumList) { }
    public void visit(EnumItem EnumItem) { }
    public void visit(Expr Expr) { }
    public void visit(ForDesignatorStatement ForDesignatorStatement) { }
    public void visit(ActPars ActPars) { }
    public void visit(DesignatorStatement DesignatorStatement) { }
    public void visit(MethRetAndName MethRetAndName) { }
    public void visit(UnmatchedStatement UnmatchedStatement) { }
    public void visit(ConDeclMore ConDeclMore) { }
    public void visit(Statement Statement) { }
    public void visit(VarDecl VarDecl) { }
    public void visit(VarDeclMore VarDeclMore) { }
    public void visit(CondFact CondFact) { }
    public void visit(FormPar FormPar) { }
    public void visit(MethodDeclList MethodDeclList) { }
    public void visit(Mulop_mod Mulop_mod) { visit(); }
    public void visit(Mulop_div Mulop_div) { visit(); }
    public void visit(Mulop_mul Mulop_mul) { visit(); }
    public void visit(Addop_minus Addop_minus) { visit(); }
    public void visit(Addop_plus Addop_plus) { visit(); }
    public void visit(Assignop Assignop) { visit(); }
    public void visit(Relop_leq Relop_leq) { visit(); }
    public void visit(Relop_ls Relop_ls) { visit(); }
    public void visit(Relop_Geq Relop_Geq) { visit(); }
    public void visit(Relop_grt Relop_grt) { visit(); }
    public void visit(Relop_neq Relop_neq) { visit(); }
    public void visit(Relop_eq Relop_eq) { visit(); }
    public void visit(Designator_expr Designator_expr) { visit(); }
    public void visit(Designator_len Designator_len) { visit(); }
    public void visit(Designator_enum Designator_enum) { visit(); }
    public void visit(Designator_var Designator_var) { visit(); }
    public void visit(Factor_expr Factor_expr) { visit(); }
    public void visit(Factor_new_array Factor_new_array) { visit(); }
    public void visit(Factor_b Factor_b) { visit(); }
    public void visit(Factor_c Factor_c) { visit(); }
    public void visit(Factor_n Factor_n) { visit(); }
    public void visit(Factor_methnopars Factor_methnopars) { visit(); }
    public void visit(Factor_methpars Factor_methpars) { visit(); }
    public void visit(Factor_var Factor_var) { visit(); }
    public void visit(Unary_e Unary_e) { visit(); }
    public void visit(Unary_m Unary_m) { visit(); }
    public void visit(Factor Factor) { visit(); }
    public void visit(SingleFactor SingleFactor) { visit(); }
    public void visit(FactorList_mulop FactorList_mulop) { visit(); }
    public void visit(Term Term) { visit(); }
    public void visit(SingleTerm SingleTerm) { visit(); }
    public void visit(TermList_addop TermList_addop) { visit(); }
    public void visit(NonTernaryExpr NonTernaryExpr) { visit(); }
    public void visit(Expr_tern Expr_tern) { visit(); }
    public void visit(Expr_notern Expr_notern) { visit(); }
    public void visit(CondFact_relop CondFact_relop) { visit(); }
    public void visit(CondFact_norelop CondFact_norelop) { visit(); }
    public void visit(SingleCondTerm SingleCondTerm) { visit(); }
    public void visit(CondTermList CondTermList) { visit(); }
    public void visit(SingleCondition SingleCondition) { visit(); }
    public void visit(ConditionList ConditionList) { visit(); }
    public void visit(DesignatorStatement_dec DesignatorStatement_dec) { visit(); }
    public void visit(DesignatorStatement_inc DesignatorStatement_inc) { visit(); }
    public void visit(DesignatorStatement_noactpar DesignatorStatement_noactpar) { visit(); }
    public void visit(DesignatorStatement_actpar DesignatorStatement_actpar) { visit(); }
    public void visit(DesignatorStatement_ass DesignatorStatement_ass) { visit(); }
    public void visit(ActPars_single ActPars_single) { visit(); }
    public void visit(ActPars_list ActPars_list) { visit(); }
    public void visit(CaseStatements_e CaseStatements_e) { visit(); }
    public void visit(CaseStatements_state CaseStatements_state) { visit(); }
    public void visit(Case Case) { visit(); }
    public void visit(CaseList_e CaseList_e) { visit(); }
    public void visit(CaseList_list CaseList_list) { visit(); }
    public void visit(ForCondition_e ForCondition_e) { visit(); }
    public void visit(ForCondition_for ForCondition_for) { visit(); }
    public void visit(ForDesignatorStatement_e ForDesignatorStatement_e) { visit(); }
    public void visit(ForDesignatorStatement_for ForDesignatorStatement_for) { visit(); }
    public void visit(ReturnExpression_non_expr ReturnExpression_non_expr) { visit(); }
    public void visit(ReturnExpression_expr ReturnExpression_expr) { visit(); }
    public void visit(IfElseStatement_else IfElseStatement_else) { visit(); }
    public void visit(IfElseStatement_non_else IfElseStatement_non_else) { visit(); }
    public void visit(RecStatement RecStatement) { visit(); }
    public void visit(ForStatement ForStatement) { visit(); }
    public void visit(SwitchStatement SwitchStatement) { visit(); }
    public void visit(PrintStatement2 PrintStatement2) { visit(); }
    public void visit(PrintStatement PrintStatement) { visit(); }
    public void visit(ReadStatement ReadStatement) { visit(); }
    public void visit(ReturnStatement ReturnStatement) { visit(); }
    public void visit(ContinueStatement ContinueStatement) { visit(); }
    public void visit(BreakStatement BreakStatement) { visit(); }
    public void visit(IfElseStatement IfElseStatement) { visit(); }
    public void visit(DesignStatement DesignStatement) { visit(); }
    public void visit(UnmatchedStat UnmatchedStat) { visit(); }
    public void visit(MatchedStat MatchedStat) { visit(); }
    public void visit(StatementList_e StatementList_e) { visit(); }
    public void visit(StatementList_rec StatementList_rec) { visit(); }
    public void visit(VarDeclListRec_e VarDeclListRec_e) { visit(); }
    public void visit(VarDeclListRec_rec VarDeclListRec_rec) { visit(); }
    public void visit(FormPar_array FormPar_array) { visit(); }
    public void visit(FormPar_var FormPar_var) { visit(); }
    public void visit(FormParMore_e FormParMore_e) { visit(); }
    public void visit(FormParMore_comma FormParMore_comma) { visit(); }
    public void visit(FormParList_e FormParList_e) { visit(); }
    public void visit(FormParList_rec FormParList_rec) { visit(); }
    public void visit(MethRetAndName_void MethRetAndName_void) { visit(); }
    public void visit(MethRetAndName_type MethRetAndName_type) { visit(); }
    public void visit(MethodDecl MethodDecl) { visit(); }
    public void visit(MethodDeclList_e MethodDeclList_e) { visit(); }
    public void visit(MethodDeclList_rec MethodDeclList_rec) { visit(); }
    public void visit(Type Type) { visit(); }
    public void visit(VarDeclMore_e VarDeclMore_e) { visit(); }
    public void visit(VarDeclMore_comma VarDeclMore_comma) { visit(); }
    public void visit(VarDecl_array VarDecl_array) { visit(); }
    public void visit(VarDecl_var VarDecl_var) { visit(); }
    public void visit(VarDeclList VarDeclList) { visit(); }
    public void visit(Constant_b Constant_b) { visit(); }
    public void visit(Constant_c Constant_c) { visit(); }
    public void visit(Constant_n Constant_n) { visit(); }
    public void visit(ConDeclMore_e ConDeclMore_e) { visit(); }
    public void visit(ConDeclMore_comma ConDeclMore_comma) { visit(); }
    public void visit(ConDecl ConDecl) { visit(); }
    public void visit(ConDecList ConDecList) { visit(); }
    public void visit(EnumItem_ass EnumItem_ass) { visit(); }
    public void visit(EnumItem_var EnumItem_var) { visit(); }
    public void visit(EnumList_item EnumList_item) { visit(); }
    public void visit(EnumList_comma EnumList_comma) { visit(); }
    public void visit(EnumDeclList EnumDeclList) { visit(); }
    public void visit(ConVarDecList_e ConVarDecList_e) { visit(); }
    public void visit(ConVarDecList_enum ConVarDecList_enum) { visit(); }
    public void visit(ConVarDecList_var ConVarDecList_var) { visit(); }
    public void visit(ConVarDecList_con ConVarDecList_con) { visit(); }
    public void visit(ProgramName ProgramName) { visit(); }
    public void visit(Program Program) { visit(); }


    public void visit() { }
}
