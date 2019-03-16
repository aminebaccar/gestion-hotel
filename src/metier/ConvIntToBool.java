package metier;

public class ConvIntToBool {

	public static boolean boolToInt(int a){
		if(a==0)
			return false;
		else
			return true;
	}
	
	public static int intToBool(boolean x){
		if(x==true)
			return 1;
		else
			return 0;
	}
}