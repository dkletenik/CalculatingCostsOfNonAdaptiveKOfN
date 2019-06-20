import java.util.*;
public class TestNonAdaptiveStrategy {

	public static void main(String[] args) {
			
		int n = 9;
		int k = 5;
		
		
		//create permutation of ????? 11 00		
		ArrayList<Variable> perm = new ArrayList<Variable>();
		addVariables(.5, 5, 0, perm); //add two ?-variables
		addVariables(1, 2, 5, perm); //add two 1-variables		
		addVariables(0, 2, 7, perm); //add two 0-variables
		System.out.println(perm);
		
		CalculateCostOfPermutation calc = new CalculateCostOfPermutation(perm, k);
		System.out.println(calc.calculateCost());
		
		Collections.sort(perm, Collections.reverseOrder()); //sort in decreasing order so 1-variables are first to create 11 ????? 00
		System.out.println(perm);
		calc = new CalculateCostOfPermutation(perm, k);
		System.out.println(calc.calculateCost());
		
		


	}
	
	private static void addVariables (double probability, int number, int numSoFar, ArrayList<Variable> vars)
	{
		for (int i = 0; i < number; i++)
		{
			vars.add(new Variable ("x"+(numSoFar+i), probability, 1));
		}
			
	}

}
