package javaapplicationcompiler;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Token {

    String class_Part;
    String value_Part;
    int line_Number;

    Token(String classPart, String valuePart, int lineNumber) {
        this.class_Part = classPart;
        this.value_Part = valuePart;
        this.line_Number = lineNumber;
    }
}

class LexicalAnalyzer {

    ArrayList<String> word_BreakedArray;
    ArrayList<String> key_WordArray = new ArrayList<String>(Arrays.asList("return", "void", "class", "interface"));
    ArrayList<String> datatype = new ArrayList<String>(Arrays.asList("int", "String"));
    ArrayList<String> punctuator_Array = new ArrayList<String>(Arrays.asList(";", ",", "{", "}", "(", ")", "[", "]"));
    ArrayList<String> boolean_Array = new ArrayList<String>(Arrays.asList("true", "false"));

    LexicalAnalyzer(ArrayList<String> file) {
        this.word_BreakedArray = file;
    }

    void GenerateToken() {
        int i, j;
        String n = "\n";
        int lineNumber = 1;
        boolean isTokenGenrated = false;
        for (i = 0; i < word_BreakedArray.size(); i++) {
            if (word_BreakedArray.get(i).equals(n)) {
                lineNumber++;

            }

            if (word_BreakedArray.get(i).equals("+") || word_BreakedArray.get(i).equals("-")) {
                Token PmToken = new Token("PM", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + PmToken.class_Part + "," + PmToken.value_Part + "," + PmToken.line_Number + "}");
            } else if (word_BreakedArray.get(i).equals("*") || word_BreakedArray.get(i).equals("/") || word_BreakedArray.get(i).equals("%")) {
                Token AmToken = new Token("AM", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + AmToken.class_Part + "," + AmToken.value_Part + "," + AmToken.line_Number + "}");
            } else if (word_BreakedArray.get(i).equals("||") || word_BreakedArray.get(i).equals("&&")) {
                Token LogicalOperatorToken = new Token("Logicaloperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + LogicalOperatorToken.class_Part + "," + LogicalOperatorToken.value_Part + "," + LogicalOperatorToken.line_Number + "}");
            } else if (word_BreakedArray.get(i).equals("!")) {
                Token NotOperatorToken = new Token("Notoperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + NotOperatorToken.class_Part + "," + NotOperatorToken.value_Part + "," + NotOperatorToken.line_Number + "}");
            } else if (word_BreakedArray.get(i).equals("=")) {
                Token AssignmentoperatorToken = new Token("Assignmentoperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + AssignmentoperatorToken.class_Part + "," + AssignmentoperatorToken.value_Part + "," + AssignmentoperatorToken.line_Number + "}");
            } else if (word_BreakedArray.get(i).equals("<") || word_BreakedArray.get(i).equals(">")) {
                Token RationaloperatorToken = new Token("Rationaloperator", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + RationaloperatorToken.class_Part + "," + RationaloperatorToken.value_Part + "," + RationaloperatorToken.line_Number + "}");
            } else {

            }
            for (j = 0; j < punctuator_Array.size(); j++) {

                if (word_BreakedArray.get(i).equals(punctuator_Array.get(j))) {
                    Token PunctuatorToken = new Token(punctuator_Array.get(j), word_BreakedArray.get(i), lineNumber);
                    isTokenGenrated = true;
                    System.out.println("{" + PunctuatorToken.class_Part + "," + PunctuatorToken.value_Part + "," + PunctuatorToken.line_Number + "}");
                }
            }

            Pattern identifier = Pattern.compile("([A-Za-z][_]?|_)([A-Za-z0-9]+[_]?)*");
            Matcher idmatch = identifier.matcher(word_BreakedArray.get(i));
            boolean id = idmatch.matches();
            boolean IsIdentifier;
            if (id == true) {
                IsIdentifier = false;
                if (IsIdentifier == false) {
                    for (j = 0; j < key_WordArray.size(); j++) {
                        if (word_BreakedArray.get(i).equals(key_WordArray.get(j))) {
                            Token KeywordToken = new Token(key_WordArray.get(j), word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + KeywordToken.class_Part + "," + KeywordToken.value_Part + "," + KeywordToken.line_Number + "}");
                            IsIdentifier = true;
                        }
                    }
                }
                if (IsIdentifier == false) {
                    for (j = 0; j < datatype.size(); j++) {

                        if (word_BreakedArray.get(i).equals(datatype.get(j))) {
                            Token DataTypeToken = new Token("Datatype", word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + DataTypeToken.class_Part + "," + DataTypeToken.value_Part + "," + DataTypeToken.line_Number + "}");
                            IsIdentifier = true;
                        }
                    }
                }
                if (IsIdentifier == false) {
                    for (j = 0; j < boolean_Array.size(); j++) {
                        if (word_BreakedArray.get(i).equals(boolean_Array.get(j))) {
                            Token BoolToken = new Token("Boolean", word_BreakedArray.get(i), lineNumber);
                            isTokenGenrated = true;
                            System.out.println("{" + BoolToken.class_Part + "," + BoolToken.value_Part + "," + BoolToken.line_Number + "}");
                            IsIdentifier = true;
                        }
                    }
                }
                if (IsIdentifier == false) {

                    Token IdentifierToken = new Token("Identifier", word_BreakedArray.get(i), lineNumber);
                    isTokenGenrated = true;
                    System.out.println("{" + IdentifierToken.class_Part + "," + IdentifierToken.value_Part + "," + IdentifierToken.line_Number + "}");
                }
            }

            Pattern doubleMatch = Pattern.compile("[+-]?[0-9]*[.]?[0-9]+");
            Matcher dbMatch = doubleMatch.matcher(word_BreakedArray.get(i));
            boolean dB = dbMatch.matches();
            if (dB == true) {
                Token DoubleToken = new Token("Double", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + DoubleToken.class_Part + "," + DoubleToken.value_Part + "," + DoubleToken.line_Number + "}");
            }
            Pattern stringMatch = Pattern.compile("\".*?\"");
            Matcher stMatch = stringMatch.matcher(word_BreakedArray.get(i));
            boolean sT = stMatch.matches();
            if (sT == true) {
                Token StringToken = new Token("String", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + StringToken.class_Part + "," + StringToken.value_Part + "," + StringToken.line_Number + "}");
            }

            if (isTokenGenrated == false) {
                Token InvalidToken = new Token("invalid", word_BreakedArray.get(i), lineNumber);
                isTokenGenrated = true;
                System.out.println("{" + InvalidToken.class_Part + " " + "Valuepart:" + InvalidToken.value_Part + "," + InvalidToken.line_Number + "}");
            }
            isTokenGenrated = false;
        }
    }
}


public class JavaApplicationCompiler {
   static String ReadFile() throws IOException{
           String  data = new String(Files.readAllBytes(Paths.get("C:\\Users\\Bilal\\Desktop\\test.txt")));
       return data;
    }
   static boolean SpaceBreaker(char ch ){
       if(ch ==' '){
        return true ;       
}else return false;
    } 
   static boolean PunctuatorBreaker(char ch){
       String [] punctuators={";",",","{","}","(",")","[","]"};
 
     for(int i =0 ;i<punctuators.length; i ++ ){
         if(punctuators[i].equals(String.valueOf(ch)))
             return true;
     }
     return false;}
   
      static boolean OperatorBreaker(char ch){
       String [] operators={"+","-","*","/","%","<",">","&&","||","!","="};
    
     for(int i =0 ;i<operators.length; i ++ ){
         if(operators[i].equals(String.valueOf(ch) ))
             return true;
     }
     return false;
   }
     static boolean linechangebreaker(char ch ){
          
       if(ch == '\r'){
           
        return true ;       
}else return false;
    }
      static boolean linechangebreaker1(char ch ){
          
       if(ch == '\n'){
           
        return true ;       
}else return false;
    }
            static boolean CommentBreaker(char ch){
       String  comment="//";
       String comment1="/";
     if(comment1==(String.valueOf(ch))){
      if(comment==(String.valueOf(ch))){
             return true;
                 
         }    }
     
     return false;
   }
             static boolean IsDigit(String ch){
                 
       String [] digits={"0","1","2","3","4","5","6","7","8","9"};
 for(int j=0;j<ch.length();j++){
      
     for(int i =0 ;i<digits.length; i ++ ){
         if(digits[i].equals(String.valueOf(ch) ))
             return true;
     }}
     return false;
   }   
    public static void main(String[] args) throws IOException {
          
     ArrayList <String> allChars = new ArrayList();
      String fileInput = ReadFile();
      String temp = "";
      
      for(int i =0;i<fileInput.length();i++){
          
           if( CommentBreaker(fileInput.charAt(i)) == true)
               {
                while(fileInput.charAt(i)!='\r'){
                    i++;
                }}
        
           else if( SpaceBreaker(fileInput.charAt(i)) == true){  
                if(temp.length()!=0){
                    allChars.add(temp);
                    temp="";
             }}
           else if(OperatorBreaker(fileInput.charAt(i))==true){
        if(temp.length()==0)
      allChars.add(String.valueOf(fileInput.charAt(i)));
        else{ 
            allChars.add(temp);
                    temp="";
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                   
                  }}
      
   else if(PunctuatorBreaker(fileInput.charAt(i))==true){
        if(temp.length()==0)
      allChars.add(String.valueOf(fileInput.charAt(i)));
        else{
            allChars.add(temp);
                    temp="";
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                  }}
     else if(linechangebreaker(fileInput.charAt(i))==true)
      {
              if(temp.length()!=0){
                  
                    allChars.add(temp);
                    temp="";
                    i++;
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                    
                  }
              else{
                 
                    i++;
                    allChars.add(String.valueOf(fileInput.charAt(i)));
              }
      }
     else if(linechangebreaker1(fileInput.charAt(i))==true)
      {
              if(temp.length()!=0){
                  
                    allChars.add(temp);
                    temp="";
                    
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                    
                  }else{
              allChars.add(String.valueOf(fileInput.charAt(i)));
              }
      }
     
     
   else if(fileInput.charAt(i)=='.'){
                   if(temp.length()==0){
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                      }
                    String regex = "[0-9]+";
                  if(temp.matches(regex)){//temphas numbers
                    temp+= fileInput.charAt(i);
                    }
                  else{
                   allChars.add(temp);
                    temp="";
                    temp+=(String.valueOf(fileInput.charAt(i)));
                  }}
  else if(fileInput.charAt(i)=='"'){
       allChars.add(temp);
       temp="";
       temp+=(String.valueOf(fileInput.charAt(i)));
       i++;
       while(fileInput.charAt(i)!='"'){
           if(fileInput.charAt(i)=='\n')
           {allChars.add(temp);
       temp="";
       //i++;
           }else
           {  temp+=(String.valueOf(fileInput.charAt(i)));
           i++;
           }}
       temp+=(String.valueOf(fileInput.charAt(i)));
       allChars.add(temp);
       temp="";
  } 
   else {
                
            temp+=fileInput.charAt(i);
            }}
            for(String x : allChars){
//                if(x =='\r')
//                    x++;
      System.out.println(x);
      }
        LexicalAnalyzer input = new LexicalAnalyzer(allChars);    
        input.GenerateToken();
      }
private static boolean isNumeric(String temp) {
    try {  
String someString = temp;
boolean isNumeric = someString.chars().allMatch( Character::isDigit );
        Integer.parseInt(temp);  
    return true;
  }
    catch(NumberFormatException e){  
    return false;  
  }
    }}
//implemented till date 
/*double RE 
linechange
*/
//tested  till date 
/*          ststus
double RE    all good 
linechange  space is genrating 
*/
//remaing  till date                                       task assigned
/*space printing ignore in  linechange on wordbreak level    ammar
linechange ignore in token generation                        bilal
srting double quotes at value part level (at last )        ignore
comment implemenation                                      ammar
*/
