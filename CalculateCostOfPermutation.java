import java.util.*;
public class CalculateCostOfPermutation {
	//This class calculates the cost of a non-adaptive k-of-n strategy using dynamic programming
	
	ArrayList<Variable> permutation;
	int n;
	int k; //must be between 0 and n
	//Note: in pseudocode, permutation is indexed from 1 to n. Must be careful about the bounds on this array.
	
	public CalculateCostOfPermutation(ArrayList<Variable> perm, int k)
	{
		permutation = perm;
		this.n = permutation.size();
		this.k = k;
	}
	
	public double calculateCost()
	{
		int z = n - k + 1; //number of zeros needed
		double[][] P = new double[n+1][k+1]; //P[i,j] = probability of exactly j 1s in bits x1 ... xi
		
		//base cases: i = 0, j = 0:
		P[0][0] = 1;
		for (int j = 1; j <= k; j++)
			P[0][j] = 0;
		double product = 1;
		for (int i = 1; i <= n; i++)
		{
			product *= permutation.get(i-1).mpi;
			P[i][0] = product;
		}
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= k; j++)
			{
				if (j > i)
					P[i][j] = 0;
				else
				{
					P[i][j] = P[i-1][j-1] * permutation.get(i-1).pi + P[i-1][j] * permutation.get(i-1).mpi;
				}
			}
		
		double[][] Q = new double[n+1][z+1]; //Q[i,j] = probability of exactly j 0s in bits x1 ... xi
		//base cases, i = 0, j = 0:
		Q[0][0] = 1;
		for (int j = 1; j <= z; j++)
			Q[0][j] = 0;
		product = 1;
		for (int i = 1; i <= n; i++)
		{ 
			product *= permutation.get(i-1).pi;
			Q[i][0] = product;
		}
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= z; j++)
			{
				if (j > i)
					Q[i][j] = 0;
			
				else
					Q[i][j] = Q[i-1][j-1] * permutation.get(i-1).mpi + Q[i-1][j] * permutation.get(i-1).pi;
			}
		
		//printing tables just to check
		
		/*System.out.println("Printing P[i,j] table");
			
		for (int j = 0; j <= k; j++)
		{
			for (int i = 0; i <= n; i++)
				System.out.print(P[i][j] + " " );
			System.out.println();			
		}
		System.out.println("Printing Q[i,j] table");

		
		for (int j = 0; j <= k; j++)
		{
			for (int i = 0; i <= n; i++)
				System.out.print(Q[i][j] + " " );
			System.out.println();			
		}
		
		System.out.println("z = " + z);*/
		
		// calculating cost of given permutation
		
		double cost = 0;
		for (int i = 1; i <= n; i++)
		{
			double tempCost = P[i-1][k-1] * permutation.get(i-1).pi + Q[i-1][z-1] * permutation.get(i-1).mpi;
			tempCost *= i; //for unit costs, can change later to be sum of cis until now
			cost += tempCost;			
		}
		return cost;
	}

}
