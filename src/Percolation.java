

public class Percolation {
	
	private static int N = 0;
	QuickFindUF qf;
	private boolean[][] mOpened_closed;
	
	private int amountOfOpened = 0;

	// створюємо матрицю N-на-N, з усіма заблокованими об’єктами 
	public Percolation(int N){
		this.N = N;
		
		qf = new QuickFindUF(N*N);
		
		mOpened_closed = new boolean[N][N];
		
	}
	
	//рахуємо і повертаємо кількість відкритих комірок
	public int getOpenedCount(){
		
		/*int count = 0;

		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(mOpened_closed[i][j])
					count++;
		*/
		
		return amountOfOpened;
	}
	
	
	
	
	// відкрити об’єкт (row i, column j) якщо він ще не відкритий 
	public void open(int i, int j){
		
		if(mOpened_closed[i][j] == false){
			mOpened_closed[i][j] = true;
			amountOfOpened++;
			
			int id = i*N + j;
			
			
			if(i == 0){
				if(j == 0){
					if(mOpened_closed[i][j+1])
						qf.union(id+1,id); //3
				}else if(j == N-1){
					if(mOpened_closed[i][j-1])
						qf.union(id-1,id); //2					
				}else{
					if(mOpened_closed[i][j-1])
						qf.union(id-1,id); //2
					if(mOpened_closed[i][j+1])
						qf.union(id+1,id);//3
				}
				if(mOpened_closed[i+1][j])
					qf.union((N*(i+1)+j),id);//4
			}else if(i == N-1){
				if(j == 0){
					if(mOpened_closed[i][j+1])
						qf.union(id+1,id); //3
				}else if(j == N-1){
					if(mOpened_closed[i][j-1])
						qf.union(id-1,id); //2	
				}else{
					if(mOpened_closed[i][j-1])
						qf.union(id-1,id); //2
					if(mOpened_closed[i][j+1])
						qf.union(id+1,id); //3
				}
				if(mOpened_closed[i-1][j])
					qf.union((N*(i-1)+j),id); //1
			}else if(j == 0){
				if(mOpened_closed[i-1][j])
					qf.union((N*(i-1)+j),id); //1
				if(mOpened_closed[i][j+1])
					qf.union(id+1,id); //3
				if(mOpened_closed[i+1][j])
					qf.union((N*(i+1)+j),id);//4
			}else if(j == N-1){
				if(mOpened_closed[i-1][j])
					qf.union((N*(i-1)+j),id); //1
				if(mOpened_closed[i][j-1])
					qf.union(id-1,id); //2
				if(mOpened_closed[i+1][j])
					qf.union((N*(i+1)+j),id);//4
			}else{
				if(mOpened_closed[i-1][j])
					qf.union((N*(i-1)+j),id); //1
				if(mOpened_closed[i][j-1])
					qf.union(id-1,id); //2
				if(mOpened_closed[i][j+1])
					qf.union(id+1,id); //3
				if(mOpened_closed[i+1][j])
					qf.union((N*(i+1)+j),id);//4
			}
			
		}
		
	}

	/*
	public void union(int p, int q){
		int pid = mId[p];
		int qid = mId[q];
		
		if(pid == qid) return;
		
		for(int i=0; i<mId.length; i++){
			if (mId[i]==pid) 
				mId[i] = qid;
		}
	}*/
	
	// чи відкитий об’єкт (row i, column j)? 
	public boolean isOpened(int i, int j){
		return mOpened_closed[i][j];
	}
	
	
	//чи протікає система
	public boolean percolates(){
		
		for(int i=0; i<N; i++)
			if(mOpened_closed[0][i]){
				for(int j=((N*N)-N); j<N*N; j++)
					if(qf.find(i) == qf.find(j))
						return true;
			}
				
		
		/*
		
		int top = 0;
		
		for(int i=0; i<N; i++){
			if(mOpened_closed[0][i]){
				top = qf.find(i); 
				
				for(int j=N*N-N; j<N*N; j++){
					if(top == qf.find(i))
						return true;
				}
			}
		}
	*/
		return false;
		
	}

	////////////////LIZA WAS HERE
// for conflict 

}
