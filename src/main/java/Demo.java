
public class Demo {
public static void main(String[] args) {
	int count = 0;
	int [] arr1 = {1,3,5,7,9,11,13,15};
	int [] arr2 = {1,3,5,7,9,11,13,15};
	int [] arr3 = {1,3,5,7,9,11,13,15};
	
	for(int i = 0; i<arr1.length; i++){
		for(int j = 0; j<arr2.length; j++){
			for(int k = 0; k<arr3.length; k++){
				count++;
			if((arr1[i]+arr2[j]+arr3[k]) == 30){
				System.out.println("1. '" +arr1[i]+"'\2. '" +arr2[j]+ "'\n3. '"+arr3[k]+"'");
				break;
			}
			}
			}
			}
	System.out.println(count);
				
}
}
