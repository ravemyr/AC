
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DP1ClosestWordsTest {
  LinkedList<String> closestWords = null;
  private int[][] matrix;
  private int[][] matrixCopy;
  private int matches = 0;
  private String oldString = " ";
  private int count1Tot = 0;
  private int count2Tot = 0;

  int closestDistance = -1;
  public String removeChar(String in , String track){
	  for(int i=1; i<in.length();i++){
		  if(in.substring(i,i+1).equals(track)){
			  return in.substring(0,i-1) + in.substring(i+1,in.length()-1);
		  }
	  }
	  return in;
  }
  int partDist(String w1, String w2, int w1len, int w2len) {
	  if (matrix[w1len][w2len] != -1){
		  return matrix[w1len][w2len];
	  }
	  if (matches == 0){
		  for (int i = 1; i <= w1len; i++){
			  for (int j = 1; j <= w2len; j++){
				  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
						  Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1));		  
			  }
		  }
	  }
	  else{
//		  int count1 = 0;
		  if (matches >= w1len) {
			  for (int i = 1; i <= w1len; i++) {
				  System.arraycopy(matrixCopy[i], 1, matrix[i], 1, matches);
//				  count1++;
			  }
			  for (int i = 1; i <= w1len; i++) {
				  for (int j = matches + 1; j <= w2len ; j++) {
					  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
							  Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1));
//					  count1++;
				  }
			  }
		  }
		  else {
			  for (int i = 1; i <= matches; i++) {
				  System.arraycopy(matrixCopy[i], 1, matrix[i], 1, matches);
			  }
			  for (int i = 1; i <= matches; i++) {
				  for (int j = matches + 1; j <= w2len ; j++) {
					  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
							  Math.min(matrix[i-1][j], matrix[i][j-1])+1);
				  }
			  }
			  for (int i = matches + 1; i <= w1len; i++) {
				  for (int j = 1; j <= w2len; j++) {
					  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
							  Math.min(matrix[i-1][j], matrix[i][j-1])+1);
//					  count1++;
				  }
			  }
		  }
	  }
	  matrixCopy = new int[w1len+1][];
	  for (int i = 0; i < w1len + 1; i++){
		  matrixCopy[i] = matrix[i].clone();
	  }
	  return matrix[w1len][w2len]; 
  }

  int Distance(String w1, String w2) {
	int l1 = w1.length();
	int l2 = w2.length();
	matrix = new int[l1+1][l2+1];
	for (int[] a : matrix){
		Arrays.fill(a, -1);
	}
	for (int i = 0; i <= l1; i++){
		matrix[i][0] = i;
	}
	for (int i = 1; i <= l2; i++){
		matrix[0][i] = i;
	}
    return partDist(w1, w2, l1, l2);
  }

  public DP1ClosestWordsTest(String w, List<String> wordList) {
	  for (String s : wordList) {
		  if (closestDistance == -1){
			  matches = 0;
			  int dist = Distance(w, s);
			  closestDistance = dist;
			  closestWords = new LinkedList<String>();
			  closestWords.add(s);
			  oldString = s;
		  }
		  else{
	  		  int l1 = w.length();
			  int l2 = s.length();
			  int lengthDiff = Math.abs(l2-l1);
			  if (lengthDiff <= closestDistance){
				  int nrSimilar = 0;
				  String sTemp = s;
				  for(int i = 0; i < l1; i++){
					  String track = w.substring(i, i+1);
					  if (sTemp.contains(track)){
						  nrSimilar++;
						  sTemp = removeChar(sTemp, track);
					  }
					  if(sTemp.length()==0){
						  break;
					  }
				  }
				  if (((Math.min(l2, l1) - nrSimilar + lengthDiff) <= closestDistance)){
					  matches = 0;
					  while ((oldString.charAt(matches) == s.charAt(matches))){
						  matches++;
						  if (!(matches < oldString.length() && matches < l2)) break;
					  }
					  int dist = Distance(w, s);
					  if (dist < closestDistance) {
						  closestDistance = dist;
						  closestWords = new LinkedList<String>();
						  closestWords.add(s);
					  }
					  else if (dist == closestDistance)
						  closestWords.add(s);
					  oldString = s;
				  }			  
			  }
		  }
		  
	  }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}

