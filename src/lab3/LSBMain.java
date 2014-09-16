package lab3;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.sql.Savepoint;
import java.util.Locale;

import com.sun.imageio.*;
import com.sun.imageio.plugins.jpeg.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

class LSB{
	
	String containerName;
	String hideName;
	
	LSB(){
		containerName="";
		hideName="";
	}
	
	LSB(String name1, String name2){
		containerName=name1;
		hideName=name2;
	}
	
	LSB(String name1){
		containerName=name1;
	}
	
	public String getContainerName() {
		return containerName;
	}
	
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	
	public String getHideName() {
		return hideName;
	}
	
	public void setHideName(String hideName) {
		this.hideName = hideName;
	}
	
	private boolean checkLength() throws IOException{
		RandomAccessFile container = new RandomAccessFile(containerName, "rw");
		RandomAccessFile secret = new RandomAccessFile(hideName, "r");
		long contLen=container.length();
		long secretLen=secret.length();
		long needLen=secretLen*8+4*8+54; //содержимое секрет, + его длина, + хедер BMP
		secret.close();
		container.close();
		if (needLen<contLen)
			return true;
		else
			return false;
	}
	
	private String addZeros32(String str){
		int k=32-str.length();
		String fulled="";
		for (int i=0;i<k;i++)
			fulled+="0";
		fulled+=str;
		return fulled;
		}
	
	private String addZeros8(String str){
		int k=8-str.length();
		String fulled="";
		for (int i=0;i<k;i++)
			fulled+="0";
		fulled+=str;
		return fulled;
		}

	public int inside() throws IOException{
		if (!checkLength()){
			return -1;
		}
		RandomAccessFile container = new RandomAccessFile(containerName, "rw");
		RandomAccessFile secret = new RandomAccessFile(hideName, "r");
		String binSecLen=addZeros32(Long.toBinaryString(secret.length()));
		container.skipBytes(54);
		int count=54;
		byte once,entity;
		
		for (int i=0;i<binSecLen.length();i++){
			once=container.readByte();
			if (binSecLen.charAt(i)=='0'){
				once=(byte) (once&254);
			}
			else{
				once= (byte) (once|1);
			}
			container.seek(count);
			container.write(once);
			count++;
		}
		
		for (int i=0;i<secret.length();i++){
			entity=secret.readByte();
			binSecLen=addZeros8(Integer.toBinaryString(entity));
			if (binSecLen.length() !=8)
				binSecLen=binSecLen.substring(24,32);
			for (int j=0;j<binSecLen.length();j++){
				once=container.readByte();
				if (binSecLen.charAt(j)=='0'){
					once=(byte) (once&254);
				}
				else{
					once= (byte) (once|1);
				}
				container.seek(count);
				container.write(once);
				count++;
			}
		}
		secret.close();
		container.close();
		return 0;
	}
	
	
	public void outside() throws IOException{
		RandomAccessFile container = new RandomAccessFile(containerName, "r");
		OutputStream secret = new BufferedOutputStream(new FileOutputStream(new File(hideName)));
		container.skipBytes(54);
		byte once;
		String decode="";
		for (int i=0;i<32;i++){
			once=container.readByte();
			if (once%2==0)
				decode+="0";
			else
				decode+="1";
		}
		int length=Integer.parseInt(decode, 2);
		for (int i=0;i<length;i++){
			decode="";
			for (int j=0;j<8;j++){
				once=container.readByte();
				if (once%2==0)
					decode+="0";
				else
					decode+="1";
			}
			int k=Integer.parseInt(decode, 2)&255;
			secret.write(k);
		}
		secret.close();
	}
	
}

public class LSBMain {

	public static void main(String[] args) throws IOException {
		if (args.length<3){
			System.out.println("Incorrect parameters");
			return;
		}
		LSB test = new LSB(args[1],args[2]);
		if (args[0].equals("inside")){
			if (test.inside() == -1)
				System.out.println("Lenght of container is low");
		}
		else
			test.outside();

	}

}
