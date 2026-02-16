package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

import java.util.Stack;

import org.apache.log4j.Logger;


public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPC;
	private final static int fieldSize = 4;
	
	public int getmainPc() {
		return this.mainPC;
	}
	

	private Stack<Integer> ternaryElseFixup = new Stack<>();
	private Stack<Integer> ternaryEndFixup  = new Stack<>();
	
	private Stack<Integer> condFalse = new Stack<>();
	private Stack<Integer> condTrue = new Stack<>();
	
	Logger log = Logger.getLogger(getClass());

	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	/* METHOD DECLARATIONS */
	
	@Override
	public void visit(MethRetAndName_type methRetAndName_type) {

		methRetAndName_type.obj.setAdr(Code.pc);
		
		Code.put(Code.enter);
		Code.put(methRetAndName_type.obj.getLevel()); 
		Code.put(methRetAndName_type.obj.getLocalSymbols().size()); 
	}
	
	@Override
	public void visit(MethRetAndName_void methRetAndName_void) {
		methRetAndName_void.obj.setAdr(Code.pc);
		if(methRetAndName_void.getI1().equalsIgnoreCase("main"))
			this.mainPC = Code.pc;
		
		Code.put(Code.enter);
		Code.put(methRetAndName_void.obj.getLevel()); 
		Code.put(methRetAndName_void.obj.getLocalSymbols().size());
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	/* STATEMENTS */
	
	//Single statements
	@Override
	public void visit(PrintStatement ps) {
		Code.loadConst(0);
		if(ps.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}
	
	@Override
	public void visit(PrintStatement2 ps) {
		Code.loadConst(ps.getN2());
		if(ps.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}
	
	@Override
	public void visit(ReturnStatement_noexpr rse) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReturnStatement_expr rsn) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReadStatement rs) {
		Obj desObj = rs.getDesignator().obj;
		if(desObj.getType().equals(Tab.charType)) {
			Code.put(Code.bread);
		}else {
			Code.put(Code.read);		
		}
		Code.store(desObj);
	}
	
	private Stack<Integer> ifElseFixup = new Stack<>();
	private Stack<Integer> ifEndFixup  = new Stack<>();
	
	@Override
	public void visit(IfCondition sc) {
		ifEndFixup.push(Code.pc - 2);
	}
	
	
	@Override
	public void visit(IfElseStatement_non_else ine) {
		Code.fixup(ifEndFixup.pop());
	}
	
	@Override
	public void visit(IfElseStatement_else ine) {
		Code.fixup(ifEndFixup.pop());
	}
	
	@Override
	public void visit(IfElseStatement ies) {
		Code.fixup(ifEndFixup.pop());
	}
	
	
	
	
//	Stack<Integer> forCondStart = new Stack<>();
//	private Stack<Integer> forEndFixup  = new Stack<>();
//	
//	
//	@Override
//	public void visit(ForStart fs) {
//		forCondStart.push(Code.pc); 
//		report_info("Poseta ForStart", fs);
//	}
//	
//	@Override
//	public void visit(ForCondition fc) {		
//		Code.loadConst(0);
//		Code.putFalseJump(Code.eq, 0);
//		
//		forEndFixup.push(Code.pc-2);
//		report_info("Poseta ForCondition", fc);
//	}
//	
//	@Override
//	public void visit(ForStatement fc) {
//		report_info("Poseta ForStatement", fc);
//	    if(!forCondStart.isEmpty()) {
//	        Code.putJump(forCondStart.pop());
//	        Code.fixup(forEndFixup.pop());
//	    }
//	}
	
	
//  init 
//	START: 
//	cond 
//	putFalseJump END
//	body 
//	step 
//	putJump START 
//	END:
	
	/* CONDITION */
	
	@Override
	public void visit(ConditionList cl) {
		
	}
	
	
	@Override
	public void visit(CondFact_relop cf) {
	    int op = 0;
		if(cf.getRelop() instanceof Relop_eq) {
			op = Code.eq;
		}else if(cf.getRelop() instanceof Relop_leq) {
			op = Code.le;
		}else if(cf.getRelop() instanceof Relop_neq) {
			op = Code.ne;
		}else if(cf.getRelop() instanceof Relop_Geq) {
			op = Code.ge;
		}else if(cf.getRelop() instanceof Relop_grt) {
			op = Code.gt;
		}else if(cf.getRelop() instanceof Relop_ls) {
			op = Code.lt;
		}
		
		Code.putFalseJump(op, 0);
	}
	
	
	/* FACTOR, TERM, EXPR */
	
	@Override
	public void visit(Expr_notern expr_notern) {
		expr_notern.struct = expr_notern.getTermList().struct;
	}


	@Override
	public void visit(TermList_addop tla) {
		if(tla.getAddop() instanceof Addop_minus) {
			Code.put(Code.sub);
		}else if(tla.getAddop() instanceof Addop_plus) {
			Code.put(Code.add);
		}
	}
	
	@Override
	public void visit(FactorList_mulop flm) {
		if(flm.getMulop() instanceof Mulop_mul) {
			Code.put(Code.mul);
		}else if(flm.getMulop() instanceof Mulop_div) {
			Code.put(Code.div);
		}else if(flm.getMulop() instanceof Mulop_mod) {
			Code.put(Code.rem);
		}
	}
	
	@Override
	public void visit(Factor_des factor_des) {
		if (factor_des.getDesignator() instanceof Designator_len) {
	        Code.load(factor_des.getDesignator().obj);
	        Code.put(Code.arraylength);
	    } else {
	        Code.load(factor_des.getDesignator().obj);
	    }	
	}
	
	@Override
	public void visit(Factor_methnopars fd) {
		int offset = fd.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	@Override
	public void visit(Factor_methpars fd) {
		int offset = fd.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	@Override
	public void visit(Factor_n factor_n) {
		Code.loadConst(factor_n.getN1());;
	}
	
	@Override
	public void visit(Factor_c factor_c) {
		Code.loadConst(factor_c.getC1());;
	}
	
	@Override
	public void visit(Factor_b factor_n) {
		Code.loadConst(factor_n.getB1());;
	}
	
	@Override
	public void visit(Factor factor) {
		if (factor.getUnary() instanceof Unary_m) {
			Code.put(Code.neg);
		} 
	}
	
	@Override
	public void visit(Factor_new_array factor_new_array) {
		Code.put(Code.newarray);
		if(factor_new_array.getType().struct.equals(Tab.intType)) {
			Code.put(1);
		}else {
			Code.put(0);
		}
	}
	
	
	// Designator statements
	@Override
	public void visit(DesignatorStatement_ass dsAss) {
		Code.store(dsAss.getDesignator().obj);
	}	
	
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Code.load(designatorArrayName.obj);
	}
	
	@Override
	public void visit(DesignatorStatement_inc dsInc) {
		if(dsInc.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(dsInc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(dsInc.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_dec dsDec) {
		if(dsDec.getDesignator().obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(dsDec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dsDec.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_noactpar ds) {
		int offset = ds.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
		if(ds.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}
	
	@Override
	public void visit(DesignatorStatement_actpar ds) {
		int offset = ds.getDesignator().obj.getAdr() - Code.pc;
		
		Code.put(Code.call);
		Code.put2(offset);
		
		if(ds.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}
	
	
	// Designator
	
	
	/* TERNARY */
	
	public void visit(TernaryCondition sc) {
	    ternaryElseFixup.push(Code.pc - 2);   // condition je već napravio putFalseJump
	    									  // samo zapamtimo adresu za fixup
	}
	
	@Override
	public void visit(TernExpr1 te1) {
	    Code.putJump(0);
	    ternaryEndFixup.push(Code.pc - 2);

	    Code.fixup(ternaryElseFixup.pop());
	}
	
	public void visit(TernExpr2 te) {

	    Code.fixup(ternaryEndFixup.pop());
	}
	
	
}