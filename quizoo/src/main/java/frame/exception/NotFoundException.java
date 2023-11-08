package frame.exception;
public class NotFoundException extends Exception{
	NotFoundException(){
		super(); 
	}
	NotFoundException(String msg){
		super(msg);
	}
}
