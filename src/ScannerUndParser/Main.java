package ScannerUndParser;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Parser parser=new Parser();
        LA la = new LA("/Users/radoje.stojisic/Downloads/Compilerbau/example/LA.txt");
        String token;
        int i = 0;
        
        ParserM parserm = new ParserM(0, "/Users/radoje.stojisic/Downloads/Compilerbau/example/LA.txt");
        ArrayList<String> TokenName = la.TokenName;
        ArrayList<Byte> TokenType = la.TokenType;
        Scanner input = new Scanner(System.in);
        System.out.println("1: lexical analysis");
        System.out.println("2: lexical analysis & parser");
        
        //TODO: Implement failure handeling for CLI calls
        System.out.println("-h or --help for CLI exaple prompt");
        int Input = Integer.parseInt(input.nextLine());
        if (Input == 1) {
        	// code generator and scanner
            while (true) {
                token = la.Nexttoken();
                System.out.println(token);
                System.out.println("\n" + la.CurrentState + "\n");

                if (token.equals("$")) {
                    break;
                }
            }

            for (int j = 0; j < TokenName.size(); j++) {
                System.out.println("\n" + j + " : " + TokenName.get(j) + "  " + TokenType.get(j)); 
            }
            
        } else if (Input == 2) {
            while (true) {
                token = la.Nexttoken();
                System.out.println(token);
                System.out.println(la.CurrentState);

                if (token.equals("$")) {
                    break;
                }
            }

            for (int j = 0; j < TokenName.size(); j++) {
                System.out.println(j + " : " + TokenName.get(j) + "  " + TokenType.get(j));
            }
            if (parserm.EXPRESSION(0)) {
            	
                System.out.println("\n => DEBUG: Everything fine \n \n => Stack is empty and the automaton is in a final state \n ");
                for (int j = 0; j < parserm.Childname.size(); j++) {
                    for (int k = 0; k < parserm.Chilnummer.get(j); k++) {
                        System.out.print("\t");
                    }
                    System.out.println(parserm.Childname.get(j));
                }
            
            }else {
            	System.err.println("\nERROR: The compiler has encountered a syntax error \n\n\n\n  " ) ;
            }
    }
}
}
