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
/**
 *
 * @author muhammadammar
 */

class Token
        {
    String ClassPart;
    String ValuePart;
    int lineNumber;
    
    Token(String classPart,String valuePart,int lineNumber )
    {
        this.ClassPart=classPart;
        this.ValuePart=valuePart;
        this.lineNumber=lineNumber;
    }
    
}
class LexicalAnalyzer
{
    ArrayList <String> WordBreakedArray;
    ArrayList<String> KeyWordArray = new ArrayList<String>(Arrays.asList("return","void","int","String","class","interface"));

       
      
      ArrayList<String> PunctuatorArray = new ArrayList<String>(Arrays.asList(";",",","{","}","(",")","[","]"));
      ArrayList<String> booleanarray = new ArrayList<String>(Arrays.asList("true","false"));
     
      LexicalAnalyzer(ArrayList <String> File)
    {
        this.WordBreakedArray= File;
        
        
    }
void generatrtoken(){
   int i,j;
   String n="\r";
   int lineno=1;
    for(i=0;i<WordBreakedArray.size();i++)
    {
        if(WordBreakedArray.get(i).equals(n)){
        lineno++;
        }
   for(j=0;j<KeyWordArray.size();j++){
       
     if(WordBreakedArray.get(i).equals(KeyWordArray.get(j))){   
            Token t8= new Token(KeyWordArray.get(j),WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t8.ClassPart+" "+"  Valuepart:"+t8.ValuePart+"  line#" +t8.lineNumber);
     }   
}
   if(WordBreakedArray.get(i).equals("+") || WordBreakedArray.get(i).equals("-") ){
         Token t2= new Token("PM",WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t2.ClassPart+" "+"Valuepart:"+t2.ValuePart+"line#" +t2.lineNumber);
          }
      else if(WordBreakedArray.get(i).equals("*") || WordBreakedArray.get(i).equals("/") ||WordBreakedArray.get(i).equals("%") ){
          Token t3= new Token("AM",WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t3.ClassPart+" "+"Valuepart:"+t3.ValuePart+"line#" +t3.lineNumber);
      }
      else if(WordBreakedArray.get(i).equals("||") || WordBreakedArray.get(i).equals("&&")){
          Token t4= new Token("Logicaloperator",WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t4.ClassPart+" "+"Valuepart:"+t4.ValuePart+"line#" +t4.lineNumber);
      }
      else if(WordBreakedArray.get(i).equals("!")){
          Token t5= new Token("Notoperator",WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t5.ClassPart+" "+"Valuepart:"+t5.ValuePart+"line#" +t5.lineNumber);
      }
      else if(WordBreakedArray.get(i).equals("=")){
          Token t6= new Token("Assignmentoperator",WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t6.ClassPart+" "+"Valuepart:"+t6.ValuePart+"line#" +t6.lineNumber);
      }
       else if(WordBreakedArray.get(i).equals("<") || WordBreakedArray.get(i).equals(">")){
          Token t7= new Token("Rationaloperator",WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t7.ClassPart+" "+"Valuepart:"+t7.ValuePart+"line#" +t7.lineNumber);
      }
       else{
           
       }
   for(j=0;j<PunctuatorArray.size();j++){
       
     if(WordBreakedArray.get(i).equals(PunctuatorArray.get(j))){   
            Token t1= new Token(PunctuatorArray.get(j),WordBreakedArray.get(i),lineno);
   System.out.println("Classpart:"+t1.ClassPart+" "+"Valuepart:"+t1.ValuePart+"line#" +t1.lineNumber);
     }   
}
   Pattern identifier = Pattern.compile("([A-Za-z][_]?|_)([A-Za-z0-9]+[_]?)*");
        Matcher idmatch = identifier.matcher(WordBreakedArray.get(i));
        boolean id = idmatch.matches();
        if(id==true){
        Token t9 = new Token("Identifier",WordBreakedArray.get(i),lineno);
        System.out.println("Classpart:"+t9.ClassPart+" "+"Valuepart:"+t9.ValuePart+"line#" +t9.lineNumber);
        }
        
        Pattern doublematch = Pattern.compile("[0-9]{1,13}(\\.[0-9]*)?");
        Matcher dbmatch = doublematch.matcher(WordBreakedArray.get(i));
        boolean db = dbmatch.matches();
        if(db==true){
        Token t9 = new Token("Double",WordBreakedArray.get(i),lineno);
        System.out.println("Classpart:"+t9.ClassPart+" "+"Valuepart:"+t9.ValuePart+"line#" +t9.lineNumber);
        }
                Pattern stringmatch = Pattern.compile("\".*?\"");
        Matcher stmatch = stringmatch.matcher(WordBreakedArray.get(i));
        boolean st = stmatch.matches();
        if(st==true){
        Token t9 = new Token("String",WordBreakedArray.get(i),lineno);
        System.out.println("Classpart:"+t9.ClassPart+" "+"Valuepart:"+t9.ValuePart+"line#" +t9.lineNumber);
        }
         for(j=0;j<booleanarray.size();j++){
       
     if(WordBreakedArray.get(i).equals(booleanarray.get(j))){   
            Token t1= new Token("Boolean",WordBreakedArray.get(i),lineno);
   System.out.println("Boolean:"+t1.ClassPart+" "+"Valuepart:"+t1.ValuePart+"line#" +t1.lineNumber);
     }   
}
         
        
   
    }
   }
   
}

public class JavaApplicationCompiler {
    

   static String readFile() throws IOException
    {
           String  data = new String(Files.readAllBytes(Paths.get("/home/muhammadammar/Documents/test.txt")));
       return data;
    }
    
    
   static boolean spacebreaker(char ch ){
       if(ch ==' '){
        return true ;       
}else return false;
    } 
   static boolean punctuatorbreaker(char ch){
       String [] punctuators={";",",","{","}","(",")","[","]"};
 
      
     for(int i =0 ;i<punctuators.length; i ++ )
     {
         if(punctuators[i].equals(String.valueOf(ch)))
             return true;
                 
                     
     }
     return false;
   }
      static boolean operatorbreaker(char ch){
       String [] operators={"+","-","*","/","%","<",">","&&","||","!","="};
 
      
     for(int i =0 ;i<operators.length; i ++ )
     {
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
      
    
            static boolean commentbreaker(char ch){
       String  comment="//";
       String comment1="/";
     if(comment1==(String.valueOf(ch))){
      if(comment==(String.valueOf(ch))){
             return true;
                 
         }    }
     
     return false;
   }
    public static void main(String[] args) throws IOException {
          
      ArrayList <String> allChars = new ArrayList();
        
     
String fileInput = readFile();
      ArrayList <Integer> lineno = new ArrayList();
      
        
      String temp = "";
      
      for(int i =0;i<fileInput.length();i++){
          
           if( commentbreaker(fileInput.charAt(i)) == true)
               {
                while(fileInput.charAt(i)!='\r'){
                    i++;
                }
                   
                }
        
           else if( spacebreaker(fileInput.charAt(i)) == true)
               {
                   
                   
                if(temp.length()!=0){
                    allChars.add(temp);
                    temp="";
             
                }
        }
           else if(operatorbreaker(fileInput.charAt(i))==true)
      {
              {
        if(temp.length()==0)
      allChars.add(String.valueOf(fileInput.charAt(i)));
        else{ 
            allChars.add(temp);
                    temp="";
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                   
                  }
      }
      }
      
   else if(punctuatorbreaker(fileInput.charAt(i))==true)
      {
        if(temp.length()==0)
      allChars.add(String.valueOf(fileInput.charAt(i)));
        else{
            allChars.add(temp);
                    temp="";
                    allChars.add(String.valueOf(fileInput.charAt(i)));
                   
                  }
      }
      
           
                 else if(linechangebreaker(fileInput.charAt(i))==true)
      {
              if(temp.length()!=0){
                    allChars.add(temp);
                    temp="";
                  }
      }
          
                  
   else if(fileInput.charAt(i)=='.')
              {
                  if(isNumeric(temp)== true) //temphas numbers 
                  {
                    temp+= fileInput.charAt(i);
                    
                      }
                      
                             
                      i++;
                      while(Character.isDigit(fileInput.charAt(i))== true)
                  {
                      temp+= fileInput.charAt(i);
                      i++;
                  }  allChars.add(temp);
                    temp="";
                    
                  }                             
   else {
                
            temp+=fileInput.charAt(i);
            }
           
    
           
           
      }
            
             for(String x : allChars){
                 
      System.out.println(x);
      }
               
        LexicalAnalyzer input = new LexicalAnalyzer(allChars);    
        
        input.generatrtoken();
       
        
           
   
        
      }

    private static boolean isNumeric(String temp) {
    try {  

        

String someString = temp;
boolean isNumeric = someString.chars().allMatch( Character::isDigit );
        //Integer.parseInt(temp);  
    return true;
  }
    catch(NumberFormatException e){  
    return false;  
  }  
    }
    
}