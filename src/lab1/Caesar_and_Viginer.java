package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


class Caesar{
	private String text="";
	private int shift;
	public void check(String[] args) throws IOException{
		if (!args[1].equals("-f")){
			console_encode(args);
		}
		else{
			file_encode(args);
		}
		
	}
	
	private void encode(){
		String newText="";
		char ch;
		for (int i=0;i<text.length();i++){
			ch=text.charAt(i);
			if ((ch>64) && (ch<91)){
				ch=(char) (ch+shift);
				if (ch>90)
					ch=(char) (ch-26);
			}
			if ((ch>96) && (ch<123)){
				ch=(char) (ch+shift);
				if (ch>122)
					ch=(char) (ch-26);
			}
			newText+=ch;
		}
		text=newText;
	}
	
	private void file_encode(String[] args) throws IOException{
		String line;
		if (args.length<5){
			System.out.println("Incorect params. Please read README");
			return;
		}
		shift=Integer.parseInt(args[4])%26;
		BufferedReader reader = new BufferedReader(new FileReader(args[2]));
		Writer output = new BufferedWriter(new FileWriter(args[3]));
		while ((line = reader.readLine()) != null) {
		    text=line;
		    encode();
		    output.write(text+"\r\n");
		}
		reader.close();		
		output.close();
		System.out.println("COMPLETE");
	}
	
	private void console_encode(String[] args) {
		text=args[1];
		shift=Integer.parseInt(args[2])%26;
		encode();
		System.out.println("Encode text:");
		System.out.println(text);	
	}
	
	public void setText(String newtext){
		text=newtext;
	}
	
	public String getText(){
		return text;
	}
	
	public void setShift(int newShift){
		shift=newShift;;
	}
	
	public int getShift(){
		return shift;
	}
	
}

class Viginer{
	private String text="";
	private String shift;
	public void check(String[] args) throws IOException{
		if (!args[1].equals("-f")){
			console_encode(args);
		}
		else{
			file_encode(args);
		}
		
	}
	
	private void encode(){
		String newText="";
		char ch,ch2;
		int j=0;
		for (int i=0;i<text.length();i++){
			ch=text.charAt(i);
			ch2=shift.charAt(j);
			j++;
			if (j==shift.length())
				j=0;
			if ((ch2>64) && (ch2<91)){
				ch2-=64;
			}
			if ((ch2>96) && (ch2<123)){
				ch2-=96;
			}
			if (ch2>27)
				ch2=0;		
			if ((ch>64) && (ch<91)){
				ch=(char) (ch+ch2);
				if (ch>90)
					ch=(char) (ch-26);
			}
			if ((ch>96) && (ch<123)){
				ch=(char) (ch+ch2);
				if (ch>122)
					ch=(char) (ch-26);
			}
			newText+=ch;
		}
		text=newText;
	}
	
	private void file_encode(String[] args) throws IOException{
		String line;
		if (args.length<5){
			System.out.println("Incorect params. Please read README");
			return;
		}
		shift=args[4];
		BufferedReader reader = new BufferedReader(new FileReader(args[2]));
		Writer output = new BufferedWriter(new FileWriter(args[3]));
		while ((line = reader.readLine()) != null) {
		    text=line;
		    encode();
		    output.write(text+"\r\n");
		}
		reader.close();		
		output.close();
		System.out.println("COMPLETE");
	}
	
	private void console_encode(String[] args) {
		text=args[1];
		shift=args[2];
		encode();
		System.out.println("Encode text:");
		System.out.println(text);	
	}
	
	public void setText(String newtext){
		text=newtext;
	}
	
	public String getText(){
		return text;
	}
	
	public void setShift(String newShift){
		shift=newShift;;
	}
	
	public String getShift(){
		return shift;
	}
}


public class Caesar_and_Viginer {
	
	public static void main(String[] args) {
		if (args.length<3){
			System.out.println("Incorect params. Please read README");
			return;
		}
		if (args[0].equals("caesar")){
			try {
				Caesar caesar=new Caesar();
				caesar.check(args);
			} catch (IOException e) {
				System.out.println("I/O error.");
			}
			return;
		}
		if (args[0].equals("viginer")){
			try {
				Viginer viginer=new Viginer();
				viginer.check(args);
			} catch (IOException e) {
				System.out.println("I/O error.");
			}
			return;
		}
		System.out.println("Incorect params. Please read README");
	}

}
