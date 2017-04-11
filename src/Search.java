import java.util.ArrayList;

public class Search {
	
	//interpolation search
	//array a must be sorted
	//returns the index of the key, or -1 if not found
	public static int interpolate(int[] a, int key){
		//minimum and maximum values
		int min=0;
		int max=a.length-1;
		
		while(max>=min){
			if(key>a[max]||key<a[min]){	
				return -1; //if the key is out of bounds, return -1
			}
			
			double distance = key-a[min]*1.0;//the distance of the key from the minimum
			int valueRange = a[max]-a[min];//the range of the value
			int indexRange = max-min;//the range of the indices
			
			int index= (int) (distance/valueRange*indexRange)+min;//estimate of the index
		
			if(key>a[index]){
				min=index+1;	//if greater, the lowest index is set to one greater than mid
			}
			else if(key<a[index]){//if less, the highest index is set to one less than mid
				max=index-1;
			}
			else{//if the values are equal, return the current index
				return index;
			}
		}
		return -1;//if no matches, return -1
	}
	
	//binary search
	//array a must be sorted
	//returns the index of the key, or -1 if not found
	public static int binary(ArrayList<String> stateIndex, String key){
		//the array, the length of the sorted array, the key
		//returns the index where the key should be inserted
		int high = stateIndex.size()-1;	//the last value of the sorted array
		int low= 0;		//the first value of the sorted array (always zero)
		
		while(high>=low){
			int mid = (high+low)/2;	//the middle index of the array
			if(key.compareTo(stateIndex.get(mid))>0){
				low=mid+1;	//if greater, the lowest index is set to one greater than mid
			}
			else if(key.compareTo(stateIndex.get(mid))<0){//if less, the highest index is set to one less than mid
				high=mid-1;
			}
			else{//if the values are equal, return the current index
				return mid;
			}
		}
		return -1;//if no matches, return -1
	}
	
	/*public static void main(String[] args) {
		ArrayList<Comparable> a= new ArrayList<Comparable>();	//test array
		a.add(-11);
		a.add(2);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		a.add(12);
		a.add(48);
		a.add(50);
		a.add(54);
		
		/*System.out.println("Interpolation Search ");
		for(int c=-20; c<80; c++){
			int x= interpolate(a, c);
			if(x!=-1){
				System.out.println("value "+c+" index "+x);
			}
		}*/
		
		/*System.out.println("\nBinary Search");
		for(int c=-20; c<80; c++){
			int x= binary(a, c);
			if(x!=-1){
				System.out.println("value "+c+" index "+x);
			}
		}

	}*/

}






