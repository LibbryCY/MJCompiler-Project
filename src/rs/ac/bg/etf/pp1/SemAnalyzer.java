package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemAnalyzer extends VisitorAdaptor {

	private boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	private Obj currentProgam;
	private Struct currentType;
	private int constant;
	private Struct constantType;
	private Struct boolType = Tab.find("bool").getType();
	private Obj mainMethod;
	private Obj currentMethod;

	private Obj currentEnum;
	private int enumCounter;
	Set<Integer> enumValues = new HashSet<>();

	private int depthLoop = 0;
	private int depthSwitch = 0;
	Set<Integer> caseValues = new HashSet<>();
	private Boolean returnFound = false;

	Stack<Struct> switchTypeStack = new Stack<>();
	Stack<HashSet<Integer>> caseValueStack = new Stack<>();

	public ArrayList<Struct> actParsList = new ArrayList<>();
	int nVars;

	private boolean isIntLike(Struct s) {
		return s == Tab.intType || s.getKind() == Struct.Enum;
	}

	/* LOG MESSAGES */
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public boolean passed() {
		return !errorDetected;
	}

	/* SEMANTIC PASS CODE */

	@Override
	public void visit(ProgramName programName) {
		currentProgam = Tab.insert(Obj.Prog, programName.getI1(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(Program program) {
		nVars = Tab.currentScope().getnVars();
		Tab.chainLocalSymbols(currentProgam);
		Tab.closeScope();
		currentProgam = null;

		if (mainMethod == null || mainMethod.getLevel() > 0)
			report_error("Program nema main metodu", program);
		else {

		}
	}

	/* CONST DECLARATIONS */
	@Override
	public void visit(ConDecl conDecl) {
		Obj conObj = Tab.find(conDecl.getI1());
		if (conObj != Tab.noObj) {
			report_error("Dvostruka definicija konstante: " + conDecl.getI1(), conDecl);
		} else {
			if (constantType.assignableTo(currentType)) {
				conObj = Tab.insert(Obj.Con, conDecl.getI1(), currentType);
				conObj.setAdr(constant);
			} else {
				report_error("Neadekvatna dodela konstanti: " + conDecl.getI1(), conDecl);
			}
		}
	}

	@Override
	public void visit(Constant_n constant_n) {
		constant = constant_n.getN1();
		constantType = Tab.intType;
	}

	@Override
	public void visit(Constant_c constant_c) {
		constant = constant_c.getC1();
		constantType = Tab.charType;
	}

	@Override
	public void visit(Constant_b constant_b) {
		constant = constant_b.getB1();
		constantType = boolType;
	}

	/* VAR DECLARATIONS */

	@Override
	public void visit(VarDecl_var varDecl_var) {
		Obj varObj = null;
		if (currentMethod == null)
			varObj = Tab.find(varDecl_var.getI1());
		else
			varObj = Tab.currentScope().findSymbol(varDecl_var.getI1());

		if (varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, varDecl_var.getI1(), currentType);
		} else {
			report_error("Dvostruka definicija promenljiva: " + varDecl_var.getI1(), varDecl_var);
		}
	}

	@Override
	public void visit(VarDecl_array varDecl_array) {
		Obj varObj = null;
		if (currentMethod == null)
			varObj = Tab.find(varDecl_array.getI1());
		else
			varObj = Tab.currentScope().findSymbol(varDecl_array.getI1());

		if (varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, varDecl_array.getI1(), new Struct(Struct.Array, currentType));
		} else {
			report_error("Dvostruka definicija promenljiva: " + varDecl_array.getI1(), varDecl_array);
		}
	}

	/* METHOD DECLARATIONS */
	@Override
	public void visit(MethRetAndName_void methRetAndName_void) {
		methRetAndName_void.obj = currentMethod = Tab.insert(Obj.Meth, methRetAndName_void.getI1(), Tab.noType);
		Tab.openScope();
		returnFound = false;
		if (methRetAndName_void.getI1().equalsIgnoreCase("main"))
			mainMethod = currentMethod;
	}

	@Override
	public void visit(MethRetAndName_type methRetAndName_type) {
		methRetAndName_type.obj = currentMethod = Tab.insert(Obj.Meth, methRetAndName_type.getI2(), currentType);
		Tab.openScope();
		returnFound = false;
	}

	@Override
	public void visit(MethodDecl methodDecl) {
		if (currentMethod.getType() != Tab.noType && !returnFound) {
			report_error("Nevoid metoda nema return statement: " + currentMethod.getName(), methodDecl);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		currentMethod = null;
	}

	/* FORMPAR DECLARATION */

	@Override
	public void visit(FormPar_var formPar_var) {
		Obj varObj = null;
		if (currentMethod == null)
			report_error("Semanticka greska. [FormPar_var]", formPar_var);
		else
			varObj = Tab.currentScope().findSymbol(formPar_var.getI2());

		if (varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, formPar_var.getI2(), currentType);
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel() + 1);
			report_info("Citanje form parametra, currentLevel: " + currentMethod.getLevel(), formPar_var);
		} else {
			report_error("Dvostruka definicija promenljiva: " + formPar_var.getI2(), formPar_var);
		}

	}

	@Override
	public void visit(FormPar_array formPar_array) {
		Obj varObj = null;
		if (currentMethod == null)
			report_error("Semanticka greska. [formPar_array]", formPar_array);
		else
			varObj = Tab.currentScope().findSymbol(formPar_array.getI2());

		if (varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, formPar_array.getI2(), new Struct(Struct.Array, currentType));
		} else {
			report_error("Dvostruka definicija promenljiva: " + formPar_array.getI2(), formPar_array);
		}
	}

	// ActPars

	@Override
	public void visit(ActPars_single aps) {
		actParsList.clear();
		actParsList.add(aps.getExpr().struct);
		// report_info("Obrada ActParametra: " + actParsList.size(), aps);
	}

	@Override
	public void visit(ActPars_list apl) {
		actParsList.add(apl.getExpr().struct);
		// report_info("Obrada ActParametraList: " + actParsList.size(), apl);
	}

	/* ENUM DECLARATION */

	@Override
	public void visit(EnumName enumName) {
		Obj enumObj = Tab.find(enumName.getI1());
		enumValues.clear();
		if (enumObj != Tab.noObj) {
			report_error("Dvostruka definicija enuma: " + enumName.getI1(), enumName);
			currentEnum = null;
		} else {
			enumCounter = 0;
			currentEnum = Tab.insert(Obj.Type, enumName.getI1(), new Struct(Struct.Enum));
			Tab.openScope();
		}
	}

	@Override
	public void visit(EnumItem_var enumItem_var) {
		if (currentEnum == null) {
			report_error("Semanticka greska. [enumItem_var]", enumItem_var);
		} else {
			Obj enumItem = Tab.currentScope().findSymbol(enumItem_var.getI1());
			if (enumItem != null) {
				report_error("Dvostruka definicija enum elementa: " + enumItem_var.getI1(), enumItem_var);
				return;
			} else if (enumValues.contains(enumCounter)) {
				enumItem = Tab.insert(Obj.Con, enumItem_var.getI1(), Tab.intType);
				enumItem.setAdr(enumCounter);
				enumCounter += 1;
				report_error("Enum item sa nejedinstvenom vrednoscu: " + enumItem_var.getI1(), enumItem_var);
			} else {
				// report_info("Enum type : "+ currentEnum.getType() + " ,za currEnum
				// "+currentEnum.getName()+ " , enumItem_var "+enumItem_var.getI1(),
				// enumItem_var);
				enumItem = Tab.insert(Obj.Con, enumItem_var.getI1(), Tab.intType);
				enumItem.setAdr(enumCounter);
				enumValues.add(enumCounter);
				enumCounter += 1;
			}
		}
	}

	@Override
	public void visit(EnumItem_ass enumItem_ass) {
		if (currentEnum == null) {
			report_error("Semanticka greska. [enumItem_ass]", enumItem_ass);
		} else {
			Obj enumItem = Tab.currentScope().findSymbol(enumItem_ass.getI1());
			if (enumItem != null) {
				report_error("Dvostruka definicija enum elementa: " + enumItem_ass.getI1(), enumItem_ass);
				return;
			} else if (enumValues.contains(enumItem_ass.getN2())) {
				enumItem = Tab.insert(Obj.Con, enumItem_ass.getI1(), Tab.intType);
				enumItem.setAdr(enumCounter);
				enumCounter += 1;
				report_error("Enum item sa nejedinstvenom vrednoscu: " + enumItem_ass.getI1(), enumItem_ass);
			} else {
				// report_info("Enum type : "+ currentEnum.getType() + " ,za currEnum
				// "+currentEnum.getName()+ " , enumItem_var "+enumItem_var.getI1(),
				// enumItem_var);
				enumItem = Tab.insert(Obj.Con, enumItem_ass.getI1(), Tab.intType);
				enumItem.setAdr(enumItem_ass.getN2());
				enumValues.add(enumItem_ass.getN2());
				enumCounter = enumItem_ass.getN2() + 1;
			}
		}
	}

	@Override
	public void visit(EnumDeclList enumDecl) {
		Tab.chainLocalSymbols(currentEnum);
		Tab.closeScope();
		currentEnum = null;
		enumCounter = 0;
	}

	/* CONTEXT CONDITIONS */

	// Statements

	@Override
	public void visit(PrintStatement ps) {
		Struct exprType = ps.getExpr().struct;
		if (!exprType.equals(boolType) && !exprType.equals(Tab.charType) && !exprType.equals(Tab.intType)) {
			report_error("Print1 pokusao da printuje neadekvatan tip ", ps);
		}
	}

	@Override
	public void visit(PrintStatement2 ps) {
		Struct exprType = ps.getExpr().struct;
		if (!exprType.equals(boolType) && !exprType.equals(Tab.charType) && !exprType.equals(Tab.intType)) {
			report_error("Print2 pokusao da printuje neadekvatan tip ", ps);
		}
	}

	@Override
	public void visit(ReadStatement rs) {
		Obj desObj = rs.getDesignator().obj;
		if (!desObj.getType().equals(boolType) && !desObj.getType().equals(Tab.charType)
				&& !desObj.getType().equals(Tab.intType)) {
			report_error("Read pokusao da cita neadekvatan tip " + desObj.getName(), rs);
			return;
		}
		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem) {
			report_error("Read neadekvatnoj promenljivoj " + desObj.getName(), rs);
			return;
		}
	}

	@Override
	public void visit(ReturnStatement_noexpr rse) {
		returnFound = true;
		if (currentMethod.getType() != Tab.noType) {
			report_error("Metoda " + currentMethod.getName() + " mora imati povratnu vrednost", rse);
		}
	}

	@Override
	public void visit(ReturnStatement_expr rsn) {
		if (currentMethod == null) {
			report_error("Return van metode", rsn);
			return;
		}

		if (!rsn.getExpr().struct.assignableTo(currentMethod.getType()))
			report_error("Povratna vrednost metode " + currentMethod.getName() + " nije odgovarajuceg tipa", rsn);
		returnFound = true;
	}

	@Override
	public void visit(IfElseStatement ies) {
		if (!ies.getCondition().struct.equals(boolType)) {
			report_error("Uslov nije boolType u IfElse Matched ", ies);
			return;
		}
		// report_info("IfElse Matched", ies);
	}

	@Override
	public void visit(IfElseStatement_non_else ifStmt) {
		if (!ifStmt.getCondition().struct.equals(boolType)) {
			report_error("Uslov u if naredbi mora biti bool", ifStmt);
		}
		// report_info("If UnMatched", ifStmt);
	}

	@Override
	public void visit(IfElseStatement_else ifElseStmt) {
		if (!ifElseStmt.getCondition().struct.equals(boolType)) {
			report_error("Uslov u if naredbi mora biti bool", ifElseStmt);
		}
		// report_info("IfElse UnMatched", ifElseStmt);
	}

	@Override
	public void visit(BreakStatement bs) {
		if (depthLoop <= 0 && depthSwitch <= 0) {
			report_error("Poziv break izvan FOR petlje ili SWITCH", bs);
			return;
		}
	}

	@Override
	public void visit(ContinueStatement cs) {
		if (depthLoop <= 0) {
			report_error("Poziv continue izvan FOR petlje", cs);
			return;
		}

	}

	@Override
	public void visit(ForStart fs) {
		depthLoop++;
	}

	@Override
	public void visit(ForCondition_for fc) {
		if (!fc.getCondition().struct.equals(boolType)) {
			report_error("Uslov u for petlji mora biti bool tipa", fc);
		}
	}

	@Override
	public void visit(ForStatement fs) {
		depthLoop--;
	}

	@Override
	public void visit(SwitchStart ss) {
		Struct expr = ss.getExpr().struct;
		depthSwitch++;
		if (!expr.equals(Tab.intType) && expr.getKind() != Struct.Enum) {
			report_error("Switch izraz mora biti int/char/enum", ss);
			return;
		}

		switchTypeStack.push(expr);
		caseValueStack.push(new HashSet<>());
	}

	@Override
	public void visit(SwitchStatement ss) {
		depthSwitch--;
		if (!switchTypeStack.isEmpty())
			switchTypeStack.pop();

		if (!caseValueStack.isEmpty())
			caseValueStack.pop();
	}

	@Override
	public void visit(Case ca) {
		if (switchTypeStack.isEmpty()) {
			report_error("CASE van switch-a", ca);
			return;
		}

		Struct currentLevelType = switchTypeStack.peek();
		int caseVal = ca.getN1();

		HashSet<Integer> usedValues = caseValueStack.peek();

		if (usedValues.contains(caseVal)) {
			report_error("Duplirana CASE vrednost: " + caseVal, ca);
		} else {
			usedValues.add(caseVal);
		}
	}

	@Override
	public void visit(CaseStatements_state cs) {
		// report_info("Posecen caseStatement", cs);
	}

	// DesignatorStatement

	@Override
	public void visit(DesignatorStatement_ass dsAss) {
		Obj desObj = dsAss.getDesignator().obj;
		Struct exprType = dsAss.getExpr().struct;

		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem) {
			report_error("Dodela neadekvatnoj promenljivoj" + desObj.getName(), dsAss);
			return;
		}

		Boolean isAssignable = exprType.assignableTo(desObj.getType())
				|| (desObj.getType().getKind() == Struct.Enum && exprType == Tab.intType);
		if (!isAssignable) {
			report_error("Nekompatabilna dodela promenjivi: " + desObj.getName(), dsAss);
			return;
		}
		// report_info("DS assign", dsAss);

	}

	@Override
	public void visit(DesignatorStatement_inc dsInc) {
		Obj desObj = dsInc.getDesignator().obj;

		if (!desObj.getType().equals(Tab.intType)) {
			report_error("Inkrement neint vrednosti!", dsInc);
			return;
		}
		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem) {
			report_error("Dodela neadekvatnoj promenljivoj, increment " + desObj.getName(), dsInc);
			return;
		}
		// report_info("Increment " + desObj.getName(), dsInc);
	}

	@Override
	public void visit(DesignatorStatement_dec dsDec) {
		Obj desObj = dsDec.getDesignator().obj;

		if (!desObj.getType().equals(Tab.intType)) {
			report_error("Dekrement neint vrednosti!", dsDec);
			return;
		}
		if (desObj.getKind() != Obj.Var && desObj.getKind() != Obj.Elem) {
			report_error("Dodela neadekvatnoj promenljivoj, dekrement " + desObj.getName(), dsDec);
		}
		// report_info("Decrement "+desObj.getName(), dsDec);
	}

	@Override
	public void visit(DesignatorStatement_noactpar dsnoact) {
		Obj metObj = dsnoact.getDesignator().obj;

		if (metObj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode: " + dsnoact.getDesignator().obj.getName(), dsnoact);
		} else if (metObj.getLevel() != 0) {
			report_error("Metoda ima parametre [DesignatorStatement]: " + metObj.getName(), dsnoact);
		} else {
			report_info("Poziv metode [DS_noactpar]: " + dsnoact.getDesignator().obj.getName() + ", Obj: "
					+ metObj.getKind() + ", " + metObj.getLevel() + ", " + metObj.getAdr(), dsnoact);
		}
	}

	@Override
	public void visit(DesignatorStatement_actpar dsact) {
		Obj metObj = dsact.getDesignator().obj;
		int argNum = actParsList.size();

		if (metObj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode [DesignatorStatement]: " + dsact.getDesignator().obj.getName(),
					dsact);
			return;
		} else if (metObj.getLevel() != argNum) { // != list.size() umesto 0
			report_error("Metoda prima pogresan broj parametara [DesignatorStatement]" + actParsList.size() + " umesto "
					+ metObj.getLevel() + " metoda " + metObj.getName(), dsact);
			return;
		}

		int index = 0;

		for (Obj o : metObj.getLocalSymbols()) {
			if (o.getFpPos() > 0) {
				Struct actualType = actParsList.get(index);

				if (!o.getType().compatibleWith(actualType)) {
					report_error(
							"Metoda prima argument pogresnog tipa, index: " + index + " metoda " + metObj.getName(),
							dsact);
				}
				index++;
			}
		}
		actParsList.clear();
		report_info("Poziv metode [DS_actpars]: " + dsact.getDesignator().obj.getName() + ", Obj: " + metObj.getKind()
				+ ", " + metObj.getLevel() + ", " + metObj.getAdr(), dsact);
	}

	// CondTerm

	public void visit(TernaryCondition sc) {
		sc.struct = sc.getCondition().struct;
	}

	@Override
	public void visit(SingleCondition sc) {
		sc.struct = sc.getCondTerm().struct;
	}

	@Override
	public void visit(ConditionList c) {
		if (!c.getCondition().struct.equals(boolType) || !c.getCondTerm().struct.equals(boolType)) {
			report_error("OR nad nebool elementim [CondTermList]", c);
			c.struct = Tab.noType;
		} else {
			c.struct = boolType;
		}
	}

	// ConditionFact

	@Override
	public void visit(SingleCondTerm sct) {
		sct.struct = sct.getCondFact().struct;
	}

	@Override
	public void visit(CondTermList cfl) {
		if (!cfl.getCondTerm().struct.equals(boolType) || !cfl.getCondFact().struct.equals(boolType)) {
			report_error("AND nad nebool elementim [CondTermList]", cfl);
			cfl.struct = Tab.noType;
		} else {
			cfl.struct = boolType;
		}
	}

	// ConditionFact

	@Override
	public void visit(CondFact_relop cfr) {
		if (!cfr.getTermList().struct.compatibleWith(cfr.getTermList1().struct)) {
			report_error("Relop nad nekompatabilnim tipovima [CondFact_relop]", cfr);
			cfr.struct = Tab.noType;
		} else if (cfr.getTermList().struct.getKind() == Struct.Array) {
			if (!(cfr.getRelop() instanceof Relop_eq) && !(cfr.getRelop() instanceof Relop_neq)) {
				report_error("Nedozvoljena relop operacija nad nizom.", cfr);
				cfr.struct = Tab.noType;
			} else {
				cfr.struct = boolType;
			}
		} else {
			cfr.struct = boolType;
		}
	}

	@Override
	public void visit(CondFact_norelop condFact_norelop) {
		if (!condFact_norelop.getTermList().struct.equals(boolType)) {
			report_error("CondFact nije boolT [CondFact_norelop]", condFact_norelop);
			condFact_norelop.struct = Tab.noType;
		} else {
			condFact_norelop.struct = boolType;
		}
	}

	// Expr

	@Override
	public void visit(TernExpr1 te1) {
		te1.struct = te1.getExpr().struct;
	}

	@Override
	public void visit(TernExpr2 te1) {
		te1.struct = te1.getExpr().struct;
	}

	public void visit(Expr_notern expr_notern) {
		expr_notern.struct = expr_notern.getTermList().struct;
	}

	@Override
	public void visit(Expr_tern expr_tern) {
		if (!expr_tern.getTernaryCondition().struct.equals(boolType)) {
			report_error("Uslov u ternarnom operatoru mora biti bool", expr_tern);
			expr_tern.struct = Tab.noType;
		} else if (!expr_tern.getTernExpr1().struct.compatibleWith(expr_tern.getTernExpr2().struct)) {
			report_error("Tipovi izraza u ternarnom operatoru se ne poklapaju", expr_tern);
			expr_tern.struct = Tab.noType;
		} else {
			expr_tern.struct = expr_tern.getTernExpr1().struct;
		}
	}

	// TermList

	@Override
	public void visit(TermList_addop tla) {
		if (!isIntLike(tla.getTermList().struct) || !isIntLike(tla.getTerm().struct)) {
			report_error("Sabiranje neint tipova [TermList_addop]", tla);
			tla.struct = Tab.noType;
		} else {
			// report_info("TermList addop ", tla);
			tla.struct = Tab.intType;
		}
	}

	@Override
	public void visit(SingleTerm singleTerm) {
		singleTerm.struct = singleTerm.getTerm().struct;
	}

	// Term

	@Override
	public void visit(Term term) {
		term.struct = term.getFactorList().struct;
	}

	// FactorList

	@Override
	public void visit(SingleFactor singleFactor) {
		singleFactor.struct = singleFactor.getFactor().struct;
	}

	@Override
	public void visit(FactorList_mulop flm) {
		if (!isIntLike(flm.getFactorList().struct) || !isIntLike(flm.getFactor().struct)) {
			report_error("Mnozenje neint tipova [FactorList_mulop]", flm);
			flm.struct = Tab.noType;
		} else {
			flm.struct = Tab.intType;
		}
	}

	// Designator

	@Override
	public void visit(Designator_var designator_var) {
		Obj varObj = Tab.find(designator_var.getI1());
		if (varObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenjivi: " + designator_var.getI1(), designator_var);
			designator_var.obj = Tab.noObj;
		} else if (varObj.getKind() != Obj.Con && varObj.getKind() != Obj.Meth && varObj.getKind() != Obj.Var) {
			report_error("Neadekvatna promenjiva: " + designator_var.getI1(), designator_var);
			designator_var.obj = Tab.noObj;
		} else if (varObj.getKind() == Obj.Meth) {
			report_info("Obrada design_var, metoda: " + designator_var.getI1() + ", Obj: " + varObj.getKind() + ", "
					+ varObj.getLevel() + ", " + varObj.getAdr(), designator_var);
			designator_var.obj = varObj;
		} else {
			report_info("Pristupanje varijabli: " + designator_var.getI1() + ", Obj: " + varObj.getKind() + ", "
					+ varObj.getLevel() + ", " + varObj.getAdr(), designator_var);
			designator_var.obj = varObj;
		}
	}

	@Override
	public void visit(Designator_len designator_len) {
		Obj arrObj = Tab.find(designator_len.getI1());

		if (arrObj == Tab.noObj) {
			designator_len.obj = Tab.noObj;
			report_error("Poziv .lenght nad nepostojecim nizom", designator_len);
			return;
		}
		if (arrObj.getType().getKind() != Struct.Array) {
			designator_len.obj = Tab.noObj;
			report_error("Poziv .lenght nad nenizovskom promenjivom", designator_len);
			return;
		}

		designator_len.obj = new Obj(Obj.Var, designator_len.getI1() + ".length", Tab.intType);
		designator_len.obj.setAdr(arrObj.getAdr());
		designator_len.obj.setLevel(arrObj.getLevel());
		// designator_len.obj = arrObj;
	}

	@Override
	public void visit(Designator_enum designator_enum) {
		Obj enumObj = Tab.find(designator_enum.getI1());
		Boolean found = false;

		if (enumObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenjivi enum [Designator_enum]: " + designator_enum.getI1(),
					designator_enum);
			designator_enum.obj = Tab.noObj;
			return;
		} else if (enumObj.getKind() != Obj.Type || enumObj.getType().getKind() != Struct.Enum) {
			report_error("Neadekvatna promenjiva za enum: " + designator_enum.getI1(), designator_enum);
			designator_enum.obj = Tab.noObj;
			return;
		}
		for (Obj o : enumObj.getLocalSymbols()) {
			if (o.getName().contentEquals(designator_enum.getI2())) {
				found = true;
				designator_enum.obj = o;
				break;
			}
		}
		if (!found) {
			report_error("Neadekvatna clan enuma: " + designator_enum.getI2(), designator_enum);
			designator_enum.obj = Tab.noObj;
		} else {
			// report_info("Obrada designator_enum, enum [Designator_enum]: " +
			// designator_enum.getI1(), designator_enum);
			// designator_enum.obj = o;
		}
	}

	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Obj arrObj = Tab.find(designatorArrayName.getI1());
		if (arrObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenjivi niza: " + designatorArrayName.getI1(), designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
		} else if (arrObj.getType().getKind() != Struct.Array || arrObj.getKind() != Obj.Var) {
			report_error("Neadekvatna promenjiva niza: " + designatorArrayName.getI1(), designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
		} else {
			designatorArrayName.obj = arrObj;
		}
	}

	@Override
	public void visit(Designator_elem designator_elem) {
		Obj arrObj = designator_elem.getDesignatorArrayName().obj;
		if (arrObj == Tab.noObj) {
			designator_elem.obj = Tab.noObj;
		} else if (!isIntLike(designator_elem.getExpr().struct)) {
			report_error("Indeksiranje sa neint vrednoscu [Designator_elem] ", designator_elem);
			designator_elem.obj = Tab.noObj;
		} else {
			report_info("Pristupanje elementu niza " + arrObj.getName() + ", Obj: " + arrObj.getKind() + ", "
					+ arrObj.getLevel() + ", " + arrObj.getAdr(), designator_elem);
			designator_elem.obj = new Obj(Obj.Elem, arrObj.getName() + "[$]", arrObj.getType().getElemType());
		}
	}

	// FactorUnar
	@Override
	public void visit(Factor_c factor_c) {
		factor_c.struct = Tab.charType;
	}

	@Override
	public void visit(Factor_b factor_b) {
		factor_b.struct = boolType;
	}

	@Override
	public void visit(Factor_n factor_n) {
		factor_n.struct = Tab.intType;

	}

	@Override
	public void visit(Factor_des factor_des) {
		// report_info("Factor_des za "+factor_des.getDesignator().obj.getName(),
		// factor_des);
		Obj obj = factor_des.getDesignator().obj;

		if (obj == null || obj == Tab.noObj) {
			factor_des.struct = Tab.noType;
			return;
		}
		if (obj.getKind() == Obj.Meth) {
			report_error("Funkcija mora biti pozvana", factor_des);
			factor_des.struct = Tab.noType;
			return;
		}
		factor_des.struct = obj.getType();
	}

	@Override
	public void visit(Factor_new_array factor_new_array) {
		if (!factor_new_array.getExpr().struct.equals(Tab.intType)) {
			report_error("Indeksiranje neint tipom [Factor_new_array]", factor_new_array);
			factor_new_array.struct = Tab.noType;
		} else {
			factor_new_array.struct = new Struct(Struct.Array, currentType);
		}
	}

	@Override
	public void visit(Factor_expr factor_expr) {
		factor_expr.struct = factor_expr.getExpr().struct;
	}

	@Override
	public void visit(Factor_methnopars factor_methnopars) {
		Obj metObj = factor_methnopars.getDesignator().obj;

		if (metObj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode: " + factor_methnopars.getDesignator().obj.getKind(),
					factor_methnopars);
			factor_methnopars.struct = Tab.noType;
		} else if (metObj.getLevel() != 0) {
			report_error("Metoda ima parametre [Factor_methnopars]: " + metObj.getName(), factor_methnopars);
			factor_methnopars.struct = Tab.noType;
		} else {
			report_info("Poziv metode: " + factor_methnopars.getDesignator().obj.getName() + ", Obj: "
					+ metObj.getKind() + ", " + metObj.getLevel() + ", " + metObj.getAdr(), factor_methnopars);
			factor_methnopars.struct = metObj.getType();
		}
	}

	@Override
	public void visit(Factor_methpars factor_methpars) {
		Obj metObj = factor_methpars.getDesignator().obj;
		int argNum = actParsList.size();

		if (metObj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode: " + factor_methpars.getDesignator().obj.getName(), factor_methpars);
			factor_methpars.struct = Tab.noType;
			return;
		} else if (metObj.getLevel() != argNum) { // != list.size() umesto 0
			report_error("Metoda prima pogresan broj parametara [Factor_methpars]" + actParsList.size() + " umesto "
					+ metObj.getLevel() + " metoda " + metObj.getName(), factor_methpars);
			factor_methpars.struct = Tab.noType;
			return;
		}

		int index = 0;

		for (Obj o : metObj.getLocalSymbols()) {
			if (o.getFpPos() > 0) {
				Struct actualType = actParsList.get(index);

				if (!o.getType().compatibleWith(actualType)) {
					report_error(
							"Metoda prima argument pogresnog tipa, index: " + index + " metoda " + metObj.getName(),
							factor_methpars);
					factor_methpars.struct = Tab.noType;
				}
				index++;
			}
		}

		report_info("Poziv metode: " + factor_methpars.getDesignator().obj.getName() + ", Obj: " + metObj.getKind()
				+ ", " + metObj.getLevel() + ", " + metObj.getAdr(), factor_methpars);
		factor_methpars.struct = metObj.getType();
		actParsList.clear();

	}

	// Factor
	@Override
	public void visit(Factor factor) {
		if (factor.getUnary() instanceof Unary_m) {
			if (isIntLike(factor.getFactorUnar().struct)) {
				factor.struct = Tab.intType;
			} else {
				report_error("Negacija neint vrednosti", factor);
				factor.struct = Tab.noType;
			}
		} else {
			factor.struct = factor.getFactorUnar().struct;
		}

	}

	@Override
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		if (typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka: " + type.getI1(), type);
			type.struct = currentType = Tab.noType;
		} else if (typeObj.getKind() != Obj.Type) {
			report_error("Neadekvatan tip podatka: " + type.getI1(), type);
			type.struct = currentType = Tab.noType;
		} else
			type.struct = currentType = typeObj.getType();
	}
}