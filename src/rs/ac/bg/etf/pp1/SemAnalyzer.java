package rs.ac.bg.etf.pp1;

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
	

	/* LOG MESSAGES */
	public void report_error(String message, SyntaxNode info) {
		errorDetected  = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
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
		Tab.chainLocalSymbols(currentProgam);
		Tab.closeScope();
		currentProgam = null;
		
		if(mainMethod == null || mainMethod.getLevel() > 0)
			report_error("Program nema main metodu", program);
		else {
			
		}
	}
	
	/* CONST DECLARATIONS */
	@Override
	public void visit(ConDecl conDecl) {
		Obj conObj = Tab.find(conDecl.getI1());
		if(conObj != Tab.noObj) {
			report_error("Dvostruka definicija konstante: " + conDecl.getI1(), conDecl);
		}
		else {
			if(constantType.assignableTo(currentType)) {
				conObj = Tab.insert(Obj.Con, conDecl.getI1(), currentType);
				conObj.setAdr(constant);
			}
			else {
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
		if(currentMethod == null)
			varObj = Tab.find(varDecl_var.getI1());
		else
			varObj = Tab.currentScope().findSymbol(varDecl_var.getI1());
		
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, varDecl_var.getI1(), currentType);
		}
		else{
			report_error("Dvostruka definicija promenljiva: " + varDecl_var.getI1(), varDecl_var);
		}
	}
	
	@Override
	public void visit(VarDecl_array varDecl_array) {
		Obj varObj = null;
		if(currentMethod == null)
			varObj = Tab.find(varDecl_array.getI1());
		else
			varObj = Tab.currentScope().findSymbol(varDecl_array.getI1());
		
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, varDecl_array.getI1(), new Struct(Struct.Array, currentType));
		}
		else{
			report_error("Dvostruka definicija promenljiva: " + varDecl_array.getI1(), varDecl_array);
		}
	}
	
	/* METHOD DECLARATIONS */
	@Override
	public void visit(MethRetAndName_void methRetAndName_void) {
		currentMethod = Tab.insert(Obj.Meth, methRetAndName_void.getI1(), Tab.noType);
		Tab.openScope();
		if(methRetAndName_void.getI1().equalsIgnoreCase("main"))
			mainMethod = currentMethod;
	}
	
	@Override
	public void visit(MethRetAndName_type methRetAndName_type) {
		currentMethod = Tab.insert(Obj.Meth, methRetAndName_type.getI2(), currentType);
		Tab.openScope();
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		currentMethod = null;
	}
	
	/* FORMPAR DECLARATION */
	
	@Override
	public void visit(FormPar_var formPar_var) {
		Obj varObj = null;
		if(currentMethod == null)
			report_error("Semanticka greska. [FormPar_var]", formPar_var);
		else
			varObj = Tab.currentScope().findSymbol(formPar_var.getI2());
		
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, formPar_var.getI2(), currentType);
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		}
		else{
			report_error("Dvostruka definicija promenljiva: " + formPar_var.getI2(), formPar_var);
		}
	}
	
	@Override
	public void visit(FormPar_array formPar_array) {
		Obj varObj = null;
		if(currentMethod == null)
			report_error("Semanticka greska. [formPar_array]", formPar_array);
		else
			varObj = Tab.currentScope().findSymbol(formPar_array.getI2());
		
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, formPar_array.getI2(), new Struct(Struct.Array, currentType));
		}
		else{
			report_error("Dvostruka definicija promenljiva: " + formPar_array.getI2(), formPar_array);
		}
	}
	
	/* ENUM DECLARATION */
	
	@Override
	public void visit(EnumName enumName) {
		Obj enumObj = Tab.find(enumName.getI1());
		if(enumObj != Tab.noObj) {
			report_error("Dvostruka definicija enuma: " + enumName.getI1(), enumName);
			currentEnum = null;
		}
		else {
			enumCounter = 0;
			currentEnum = Tab.insert(Obj.Type, enumName.getI1(), new Struct(Struct.Enum));
			Tab.openScope();
		}
	}
	
	@Override
	public void visit(EnumItem_var enumItem_var) {
	   if(currentEnum == null) {
		   report_error("Semanticka greska. [enumItem_var]", enumItem_var);
	   }else {
		   Obj enumItem = Tab.currentScope().findSymbol(enumItem_var.getI1());
		   if( enumItem != null ) {
			   report_error("Dvostruka definicija enum elementa: " + enumItem_var.getI1() , enumItem_var);
			   return;
		   }else {
			   //report_info("Enum type : "+ currentEnum.getType() + " ,za currEnum "+currentEnum.getName()+ " , enumItem_var "+enumItem_var.getI1(), enumItem_var);
			   enumItem = Tab.insert(Obj.Con, enumItem_var.getI1() , currentEnum.getType() );
			   enumItem.setAdr(enumCounter);
			   enumCounter += 1;
		   }
	   }
	}
	
	@Override
	public void visit(EnumItem_ass enumItem_ass) {
	   if(currentEnum == null) {
		   report_error("Semanticka greska. [enumItem_ass]", enumItem_ass);
	   }else {
		   Obj enumItem = Tab.currentScope().findSymbol(enumItem_ass.getI1());
		   if( enumItem != null ) {
			   report_error("Dvostruka definicija enum elementa: " + enumItem_ass.getI1(), enumItem_ass);
			   return;
		   }else {
			   //report_info("Enum type : "+ currentEnum.getType() + " ,za currEnum "+currentEnum.getName()+ " , enumItem_var "+enumItem_var.getI1(), enumItem_var);
			   enumItem = Tab.insert(Obj.Con, enumItem_ass.getI1() , currentEnum.getType() );
			   enumItem.setAdr(enumItem_ass.getN2());
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
	
	// Expr
	
	@Override
	public void visit(Expr_notern expr_notern) {
		expr_notern.struct = expr_notern.getTermList().struct; 
	}
	
	@Override
	public void visit(Expr_tern expr_tern) {
		if(expr_tern.getCondition().struct.equals(boolType)) {
			report_error("Uslov u ternarnom operatoru mora biti bool", expr_tern);
	        expr_tern.struct = Tab.noType;
		}
		else if(!expr_tern.getExpr().struct.equals(expr_tern.getExpr1().struct)) {
			report_error("Tipovi izraza u ternarnom operatoru se ne poklapaju", expr_tern);
	        expr_tern.struct = Tab.noType;
		}else {
			expr_tern.struct = expr_tern.getExpr().struct;
		}
	}
	
	
	// TermList 
	
	@Override
	public void visit(TermList_addop tla) {
		if(!tla.getTermList().struct.equals(Tab.intType) 
				|| !tla.getTerm().struct.equals(Tab.intType)) {
			report_error("Sabiranje neint tipova [TermList_addop]", tla);
			tla.struct = Tab.noType;
		}else {
			report_info("TermList addop " , tla);
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
		if(!flm.getFactorList().struct.equals(Tab.intType) 
				|| !flm.getFactor().struct.equals(Tab.intType)) {
			report_error("Mnozenje neint tipova [FactorList_mulop]", flm);
			flm.struct = Tab.noType;
		}else {
			flm.struct = Tab.intType;
		}
	}
	
	
	// Designator
	
	@Override
	public void visit(Designator_var designator_var) {
	    Obj varObj = Tab.find(designator_var.getI1());
	    if(varObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenjivi: " + designator_var.getI1(), designator_var);
			designator_var.obj = Tab.noObj;
	    }
	    else if(varObj.getKind()!= Obj.Con  && varObj.getKind()!= Obj.Meth && varObj.getKind()!= Obj.Var ) {
	    	report_error("Neadekvatna promenjiva: " + designator_var.getI1(), designator_var);
	    	designator_var.obj = Tab.noObj;
	    }else if (varObj.getKind()== Obj.Meth) {
	    	report_info("Obrada design_var, metoda [Designator_var]: "+ designator_var.getI1() + " varObj.kind: "+ varObj.getKind(), designator_var);
	    	designator_var.obj = varObj;
	    }
	    else {
	    	report_info("Obrada design_var, varijabla [Designator_var]: "+ designator_var.getI1() + " varObj.kind: "+ varObj.getKind(), designator_var);
	    	designator_var.obj = varObj;
	    }
	}
	
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Obj arrObj = Tab.find(designatorArrayName.getI1());
	    if(arrObj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenjivi niza: " + designatorArrayName.getI1(), designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
	    }
	    else if(arrObj.getType().getKind() != Struct.Array || arrObj.getKind()!= Obj.Var ) {
	    	report_error("Neadekvatna promenjiva niza: " + designatorArrayName.getI1(), designatorArrayName);
	    	designatorArrayName.obj = Tab.noObj;
	    }
	    else {
	    	designatorArrayName.obj = arrObj;
	    }
	}
	
	@Override
	public void visit(Designator_elem designator_elem) {
	    Obj arrObj = designator_elem.getDesignatorArrayName().obj;
	    if(arrObj == Tab.noObj) {
	    	designator_elem.obj = Tab.noObj;
	 
	    }
	    else if(designator_elem.getExpr().struct.equals(Tab.intType)) {
	    	report_error("Indeksiranje sa neint vrednoscu [Designator_elem] ", designator_elem);
	    	designator_elem.obj = Tab.noObj;
	    }
	    else {
	    	designator_elem.obj = new Obj(Obj.Elem,arrObj.getName()+"[$]" , arrObj.getType().getElemType());
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
	    factor_des.struct = factor_des.getDesignator().obj.getType();
	}
	
	@Override
	public void visit(Factor_new_array factor_new_array) {
		if(factor_new_array.getExpr().struct.equals(Tab.intType)) {
			report_error("Indeksiranje neint tipom [Factor_new_array]", factor_new_array);
			factor_new_array.struct = Tab.noType;
		}
		else {
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
		if(metObj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode: " + factor_methnopars.getDesignator().obj.getKind(), factor_methnopars);
			factor_methnopars.struct = Tab.noType;
		}else {
			report_info("Obrada cvora factor_methnopars, metoda: "+ factor_methnopars.getDesignator().obj.getName(), factor_methnopars);
			factor_methnopars.struct = metObj.getType();
		}
			
	}
		
	
	@Override
	public void visit(Factor_methpars factor_methpars) {
		Obj metObj = factor_methpars.getDesignator().obj;
		if(metObj.getKind() != Obj.Meth) {
			report_error("Poziv neadekvatne metode: " + factor_methpars.getDesignator().obj.getName(), factor_methpars);
			factor_methpars.struct = Tab.noType;
		}else if (metObj.getLevel() != 0 ) { // != list.size() umesto 0
	        report_error("Metoda prima parametre [Factor_methpars]", factor_methpars);
	        factor_methpars.struct = Tab.noType;
	    }else {
			report_info("Obrada cvora factor_methpars, metoda: "+ factor_methpars.getDesignator().obj.getName(), factor_methpars);
			factor_methpars.struct = metObj.getType();
		}
	}	
	
	
	// Factor
	@Override
	public void visit(Factor factor) {
	    if(factor.getUnary() instanceof Unary_m) {
	      if(factor.getFactorUnar().struct.equals(Tab.intType)) {
	    	  factor.struct = Tab.intType;
	      }	else {
			   report_error("Negacija neint vrednosti", factor);
			   factor.struct = Tab.noType;
	      }
	    }
	    else {
	    	factor.struct = factor.getFactorUnar().struct;
	    }
	    
	}
	
	
	@Override
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		if(typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka: " + type.getI1(), type);
			currentType = Tab.noType;
		}
		else if(typeObj.getKind() != Obj.Type) {
			report_error("Neadekvatan tip podatka: " + type.getI1(), type);
			currentType = Tab.noType;
		}
		else
			currentType = typeObj.getType();
	}
}