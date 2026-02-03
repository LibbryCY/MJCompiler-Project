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