
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DP2ClosestWordsTest {
  LinkedList<String> closestWords = null;
  private int[][] matrix;
  private int[][] matrixCopy;
  private int matches = 0;
  private String oldString;
  int closestDistance = -1;

  int partDist(String w1, String w2, int w1len, int w2len) {
	  if (matches == 0){
		  for (int i = 1; i <= w1len; i++){
			  for (int j = 1; j <= w2len; j++){
				  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i-1) == w2.charAt(j-1) ? 0 : 1),
				  Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1));		  
			  }
		  }
	  }
	  else{
		  for (int i = 1; i <= matches; i++){
			  for (int j = 1; j <= matches; j++){
				  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
				  Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1));		  
			  }
			  if (matrix[i][matches] != matrixCopy[i][matches]) System.out.println(w1 + " " + w2 + " " +matrix[i][matches] + " " + matrixCopy[i][matches] + " "+i+","+ matches);
			  for (int j = matches; j <= w2len; j++){
				  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
				  Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1));		  
			  }
		  }
			  for (int i = matches + 1; i <= w1len; i++){
				  for (int j = 1; j <= w2len; j++){
					  matrix[i][j] = Math.min(matrix[i-1][j-1] + (w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1),
					  Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1));		  
				  }
			  }
		  }
	  matrixCopy = new int[w1len][w2len];
	  for(int i = 0; i < w1len; i++){
	  	matrixCopy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
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

  public DP2ClosestWordsTest(String w, List<String> wordList) {
	  for (String s : wordList) {
		  if (closestDistance == -1){
			  matches = 0;
			  int dist = Distance(w, s);
			  closestDistance = dist;
			  closestWords = new LinkedList<String>();
			  oldString = s;
			  closestWords.add(s);
		  }
		  else{
	  		  int l1 = w.length();
			  int l2 = s.length();
			  int lo = oldString.length();
			  int lengthDiff = Math.abs(l2-l1);
			  if (lengthDiff <= closestDistance){
				  int nrSimilar = 0;
				  for(int i = 0; i < l1; i++){
					  if (s.contains(w.substring(i, i+1))){
						  nrSimilar++;
					  }
				  }
				  if (((Math.min(l2, lo) - nrSimilar + lengthDiff) <= closestDistance)){
					  matches = 0;
					  while ((s.charAt(matches) == oldString.charAt(matches))){
						  matches++;
						  if (!(matches < l2 && matches < lo)) break;
					  }
					//					  makeMatrix(l1, l2, matches);
					  
					  }
				  int dist = Distance(w, s);
					  // System.out.println("d(" + w + "," + s + ")=" + dist);
				  if (dist < closestDistance) {
					  closestDistance = dist;
					  closestWords = new LinkedList<String>();
					  oldString = s;
					  closestWords.add(s);
					  }
					  else if (dist == closestDistance)
						  oldString = s;
						  closestWords.add(s);
				  }			  
			  }
		  oldString = s;
		  }
	  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
