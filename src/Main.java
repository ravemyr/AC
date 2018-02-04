/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig fÃ¶rfattare: Viggo Kann KTH viggo@nada.kth.se      */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static List<String> readWordList(BufferedReader input) throws IOException {
    LinkedList<String> list = new LinkedList<String>();
    while (true) {
      String s = input.readLine();
      if (s.equals("#"))
        break;
      list.add(s);
    }
    return list;
  }

  public static void main(String args[]) throws IOException {
//	  List<String> wordList = new ArrayList<String>();
//	  wordList.add("blad");
//	  DPClosestWords myClosestWords = new DPClosestWords("labs", wordList);
//	  for (int i = 0; i <= 4; i++){
//		  for (int j = 0; j <= 4; j++){
//			  System.out.println("i = " + i + " " + "j = " + j + ": " + myClosestWords.partDist("labs", "blad", i, j));
//		  }
//	  }
	  
    long t1 = System.currentTimeMillis();
	  ArrayList<String> wordList = new ArrayList<String>();
	  String nextLine;
	  Scanner myScanner = new Scanner(new File("testord2.indata"));
	  while (true) {
		  String s = myScanner.nextLine();
		  if (s.equals("#"))
			  break;
		  wordList.add(s);
      }
	  String word;
	  while(myScanner.hasNext()){
		  word = myScanner.nextLine();
		  DP1ClosestWordsTest myClosestWords = new DP1ClosestWordsTest(word, wordList);
		  System.out.print(word + " (" + myClosestWords.getMinDistance() + ")");
		  for (String w : myClosestWords.getClosestWords()){
			  System.out.print(" " + w);
		  }
		  System.out.println();
	  }
	  long tottime = (System.currentTimeMillis() - t1);
	  System.out.println("CPU time: " + tottime + " ms");
	  
	  
//	  for (String a : wordList){
//		  System.out.println(a);
//	  }    
//	  while(myScanner.hasNext()){
//		  nextLine = myScanner.nextLine();
//		  System.out.println(nextLine);
//	  }
//	  DPClosestWords myClosestWords = new DPClosestWords()
	  
	  
//    //    long t1 = System.currentTimeMillis();
//    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
//    // SÃ¤krast att specificera att UTF-8 ska anvÃ¤ndas, fÃ¶r vissa system har annan
//    // standardinstÃ¤llning fÃ¶r teckenkodningen.
//    List<String> wordList = readWordList(stdin);
//    String word;
//    while ((word = stdin.readLine()) != null) {
//      ClosestWords closestWords = new ClosestWords(word, wordList);
//      System.out.print(word + " (" + closestWords.getMinDistance() + ")");
//      for (String w : closestWords.getClosestWords())
//        System.out.print(" " + w);
//      System.out.println();
//    }
//    //    long tottime = (System.currentTimeMillis() - t1);
//    //    System.out.println("CPU time: " + tottime + " ms");

  }
}
