package rs.ac.bg.etf.pp1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;

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
        } catch (Exception e) {
            log.error("Compilation error", e);
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}