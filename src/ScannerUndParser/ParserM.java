package ScannerUndParser;

import java.util.ArrayList;

public class ParserM {

    private LA lexicalAnalysis;

    ArrayList<Byte> TokenType = lexicalAnalysis.TokenType;
    static ArrayList<String> Childname = new ArrayList<>();
    static ArrayList<Integer> Chilnummer = new ArrayList<>();

    static int Pointer;
    int i = 0;


    ParserM(int INPUT, String fileName) {
        this.lexicalAnalysis = new LA(fileName);
        Pointer = INPUT;
    }

    boolean EXPRESSION(int b) {
        Childname.add("EXPRESSION");
        Chilnummer.add(b);
        //new
        return term(b + 1) && rightExpression(b + 1);
    }

    boolean rightExpression(int b) {
        Childname.add("rightExpression");
        Chilnummer.add(b);
        if (TokenType.get(Pointer) == TokenList.Keyword) {
            if (ChildAdd("Keyword", b)) {
                return true;
            } else {
                return term(b + 1) && rightExpression(b + 1);
            }
        }// new
        
        if (TokenType.get(Pointer) == TokenList.PLUS) {
            if (ChildAdd("PLUS", b)) {
                return true;
            } else {
                return term(b + 1) && rightExpression(b + 1);
            }
        }
        if (TokenType.get(Pointer) == TokenList.MINUS) {
            if (ChildAdd("MINUS", b)) {
                return true;
            } else {
                return term(b + 1) && rightExpression(b + 1);
            }
        } else {
            Childname.add("EPSILON");
            Chilnummer.add(b + 1);
            return EPSILON(b + 1);
        }
    }

    boolean term(int b) {
        Childname.add("term");
        Chilnummer.add(b);
        return Operator(b + 1) && rightTerm(b + 1);
    }

    boolean rightTerm(int b) {
        Childname.add("rightTerm");
        Chilnummer.add(b);
        if (TokenType.get(Pointer) == TokenList.MULT) {
            if (ChildAdd("MUL", b)) {

                return true;
            } else {
                return Operator(b + 1) && rightTerm(b + 1);
            }
        }
        if (TokenType.get(Pointer) == TokenList.DIV) {
            if (ChildAdd("DIV", b)) {
                return true;
            } else {

                return Operator(b + 1) && rightTerm(b + 1);
            }
        }
        Childname.add("EPSILON");
        Chilnummer.add(b + 1);
        return EPSILON(b + 1);
    }

    //-----------------------------------------------Operator----------- Kellerautomat
    boolean Operator(int b) {
        Childname.add("OPERATOR");
        Chilnummer.add(b);

        if (TokenType.get(Pointer) == TokenList.OPEN_PAR) {

            if  (ChildAdd("OPEN_PAR", b)) {
                return true;
            }
            
            if (EXPRESSION(b + 1)) {
                if (TokenType.get(Pointer) == TokenList.CLOSE_PAR) {
                    Childname.add("CLOSE_PAR");
                    Chilnummer.add(b + 1);
                    if (TokenType.size() - 1 == Pointer)
                        return true;
                    else
                        Pointer++;
                    return true;
                } else {
                    System.err.println("\nError: missing closing parenthesis. \n ");
                    System.err.println("syntax error: a closing parenthesis ')' is expected but not found");
                    return false;
                }
            } else {
                System.out.println("Error Expression");
                return false;
            }
        }
        
        if (TokenType.get(Pointer) == TokenList.NUM) {
            if (ChildAdd("NUM", b))
                return true;
            return true;
        } else {
            Childname.add("EPSILON");
            Chilnummer.add(b + 1);

            return EPSILON(b + 1);
        }
    }

    boolean EPSILON(int b) {
        return true;
    }

    boolean ChildAdd(String Type, int b) {
        if (TokenType.size() - 1 == Pointer) {
            Childname.add(Type);
            Chilnummer.add(b + 1);
            return true;
        } else {
            Childname.add(Type);
            Chilnummer.add(b + 1);
            Pointer++;
            return false;
        }

    }
}
