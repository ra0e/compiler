package ScannerUndParser;

public interface TokenList {
    final byte

    	NUM=1,
    	OPEN_PAR=2,
    	CLOSE_PAR=3,
    	PLUS=4,
    	MINUS=5,
    	MULT=6,
    	DIV=7,
    	ID=8,
    	ERROR=9,
    	FLOAT=10,
    	SEMICOLON=11,
    	OPEN_ACC=12,
    	CLOSE_ACC=13,
    	LINECOMMEN=14,
    	MULTILINECOMMEN=15,
    	STRING=16,
    	EQUAL=17,
    	ZWEIEQUAL=18,
    	LESS=19,
    	LESSEQUAL=20,
    	MORE=21,
    	MOREEQUAL=22,
    	NOTEQUAL=23,
    	Keyword=24;
}
