import java.util.ArrayList;
import java.util.Random;

/***
 * % java PercolationStats 200 100
 * mean = 0.5976909999999999
stddev = 6.440787439393935E-4
95% confidence interval = 0.5927167735247401 ; 0.6026652264752597
 * 
 * 
 * 
 * 
 * % java PercolationStats 20 5
mean = 0.5575
stddev = 0.0022906249999999984
95% confidence interval = 0.5155484446057121 ; 0.5994515553942878



% java PercolationStats 100 100
mean = 0.606343
stddev = 1.723606575757574E-4
95% confidence interval = 0.6037697905212691 ; 0.6089162094787308



% java PercolationStats 20 5
mean = 0.5545
stddev = 0.0014481250000000021
95% confidence interval = 0.5211439900467697 ; 0.5878560099532303




 * @author Anna_Shkuta
 *
 */

public class PercolationStats { 
	
	private ArrayList<Double> mX = new ArrayList<Double>();
	
	private int T = 0;

	// проведемо T окремих експериментів в N на N матриці
	public PercolationStats(int N, int T){
		
		int print = 1;
		
		this.T = T;
		
		for(int k = 0; k < T; k++){
			
			System.out.println(print);
			
			Percolation p = new Percolation(N);
			
			int n = 0;
			
			while(n == 0){
				
				Random r = new Random(System.currentTimeMillis());
		        
		        int i = r.nextInt(N);
		        int j = r.nextInt(N);
				
		        p.open(i, j);
		        
		        
		        if(!p.percolates())
		        	continue;
		        
		        double x = p.getOpenedCount();
		        x = x/(N*N);
		        
		        mX.add(x);
		        
		        n = 1;
			}
			print++;
		}
		
		
		
	}

	// рахує середнє
	public double mean(){
		
		double m = 0;
		
		for(Double i : mX)
			m += i;
		
		m = m/T;
		
		return m;
		
	}

	// рахує відхилення	
	public double stddev(){
		
		double m = mean();
		
		double std = 0;
		
		for(Double i : mX)
			std += Math.pow((i - m), 2);
		
			/*
		for(int i=0; i<mX.size(); i++)
			std += Math.pow((mX.get(i)-m), 2);
		*/
		
		std = std/(T-1);
		
		return std;
		
	}

	// запускаємо експерименти і рахуємо відповідні значення середнє, відхилення, інтервал довіри, та виводимо на екран
	public static void main(String[] args) {
		
		//int f = 100;
		//int s = 100;
		
		int f = 20;
		int s = 30;
		
		System.out.println("% java PercolationStats "+f+" "+s);
		
		PercolationStats ps = new PercolationStats(f, s);
		
		System.out.println("mean = " + ps.mean());
		
		System.out.println("stddev = " + ps.stddev());
		
		System.out.println("95% confidence interval = " + ( ps.mean() - (1.96*Math.sqrt(ps.stddev()))/Math.sqrt(s)) +
				 " ; " + ( ps.mean() + (1.96*Math.sqrt(ps.stddev()))/Math.sqrt(s)));
		
		
		
	}
	

}

