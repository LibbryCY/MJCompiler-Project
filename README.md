# MicroJava Compiler

A fully functional compiler for the **MicroJava** programming language, developed as a university project for the **Programming Translators (Programski Prevodioci)** course at the School of Electrical Engineering, University of Belgrade (ETF).

The compiler is implemented in **Java** and covers all four classic phases of compilation.

---

## Compiler Phases

### 1. Lexical Analysis
Tokenizes the MicroJava source code into a stream of meaningful tokens (keywords, identifiers, literals, operators, etc.). Implemented using a **JFlex**-generated lexer (`.lex` specification).

### 2. Syntax Analysis
Parses the token stream according to the MicroJava grammar and builds a concrete syntax tree. Implemented using **CUP** (a LALR parser generator for Java).

### 3. Semantic Analysis
Traverses the syntax tree and performs semantic checks, including:
- Type checking and type compatibility
- Scope and symbol table management
- Declaration before use validation
- Method signature and call validation
- Control flow checks (e.g., `return`, `break`, `continue` used in valid contexts)

### 4. Code Generation
Generates executable **MicroJava bytecode** from the semantically validated AST, targeting the MicroJava virtual machine.

---

## Project Structure
```
MJCompiler-Project/
├── src/rs/ac/bg/etf/pp1/   # Java source files (lexer, parser, semantic analyzer, code gen)
├── spec/                    # JFlex and CUP specification files
├── lib/                     # Required libraries (JFlex, CUP, MJ runtime)
├── bin/                     # Compiled class files
├── test/                    # Sample MicroJava test programs
├── logs/                    # Compiler output logs
├── config/                  # Configuration files
└── build.xml                # Ant build script
```

---

## Technologies Used

- **Java** — Core implementation language
- **JFlex** — Lexer generator (lexical analysis)
- **CUP** — LALR parser generator (syntax analysis)
- **MicroJava VM** — Target virtual machine for code generation
- **Apache Ant** — Build automation

---

## How to Build and Run

### Prerequisites
- Java JDK 8+
- Apache Ant

### Build Parser and AST files
```bash
build.xml -> Ant Build -> Target: compile
```

### Run the Compiler
```bash
test/rs/ac/bg/etf/pp1/Compile.java -> Run Application
```

### Start runObj:
```bash
build.xml -> Ant Build -> Target: runObj
```

The compiler will output a `.obj` bytecode file that can be executed on the MicroJava virtual machine.

---

## About MicroJava

MicroJava is a simplified, educational programming language inspired by Java. It supports:
- Primitive types: `int`, `char`, `bool`
- Arrays
- Methods with parameters and return values
- Standard control flow: `if/else`, `for`, `switch/case`, `break`, `continue`, `return`
- Built-in I/O: `print()`, `read()`

---

## Course

**Programming Translators (Programski Prevodioci)**  
School of Electrical Engineering — University of Belgrade (ETF)
