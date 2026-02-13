package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.SemAnalyzer;
import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class Compiler {

    static {
        DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
        Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
    }

    public static void main(String[] args) throws Exception {
        Logger log = Logger.getLogger(Compiler.class);
        Reader br = null;
        try {
            File sourceCode = new File("test/program.mj");
            log.info("Compiling source file: " + sourceCode.getAbsolutePath());
            br = new BufferedReader(new FileReader(sourceCode));
            Yylex lexer = new Yylex(br);
            MJParser p = new MJParser(lexer);
            Symbol s = p.parse(); // formiranje AST
            System.out.println();
            Program prog = (Program) (s.value);
            log.info(prog.toString(""));
            log.info("-------------------------------------------");
        
            /* Inicjalizacija tabele simbola */
            Tab.init(); 
            Struct boolType = new Struct(Struct.Bool);
            Tab.insert(Obj.Type, "bool", boolType);
            
            /* Sematicka analiza */
            
            SemAnalyzer sa = new SemAnalyzer();
            prog.traverseBottomUp(sa);
            
            /* Ispis tabele simbola */
            log.info("============================================");
            Tab.dump();
            
            
            if(!p.errorDetected && sa.passed()) {
            	// Generisanje koda
            	File objFile = new File("test/program.obj");   //new File(args[1]);
	        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	CodeGenerator codeGenerator = new CodeGenerator();
	        	prog.traverseBottomUp(codeGenerator);
	        	Code.dataSize =  sa.nVars;
	        	Code.mainPc = codeGenerator.getmainPc();
	        	Code.write(new FileOutputStream(objFile));
            	
            	log.info("Parsiranje uspesno zavrseno!");
            }else {
            	log.error("Parsiranje NIJE uspesno!");
            }
            
            
            
        } catch (Exception e) {
            log.error("Compilation error", e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
