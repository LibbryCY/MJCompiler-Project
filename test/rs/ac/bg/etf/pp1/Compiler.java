package rs.ac.bg.etf.pp1;


import java.io.File;
import java.io.IOException;
import java.io.Reader;

import java.io.BufferedReader;
import java.io.FileReader;

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

            Program prog = (Program) (s.value);

            // ispis AST
            log.info(prog.toString(""));
            log.info("============================================");

            if (!p.errorDetected) {
                log.info("Parsiranje uspesno zavrseno!");
            } else {
                log.error("Parsiranje NIJE uspesno zavrseno!");
            }

        } finally {
            if (br != null)
				try {
                br.close();
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }

    }
}
