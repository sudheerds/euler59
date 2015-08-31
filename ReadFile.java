package eulers59;
//import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;


class ReadFile{
	
	static String source = "/home/sudheerds/Desktop/Java/cipher.txt";
	static String dest = "/home/sudheerds/Desktop/Java/encryptedCipher.txt";
	static int[] key = new int[3];
	static int[] num;
	
	public static void main(String [] args) throws IOException{		
		
		FileReader fr = new FileReader(source);
		FileWriter fw = new FileWriter(dest);
		BufferedReader r = new BufferedReader(fr);
		BufferedWriter w = new BufferedWriter(fw);
		
		//read the entire file by truncating ','
		String[] str = r.readLine().split(",");
		char[] cipher = new char[str.length];
		num = new int[str.length];
		
		//file parsed to extract only ints
		for(int j=0;j<str.length;j++)	{		
			num[j]=Integer.parseInt(str[j]);
			cipher[j]=(char)num[j];
			w.append(cipher[j]);
			}
		
		r.close();
		w.close();
		System.out.println("done file processing");
		
		//System.out.println(cipher);
		// split according to key size
		int l = num.length;
		int[] k1 = new int[l/3];
		int[] k2 = new int[l/3];
		int[] k3 = new int[l/3];
		
		
		for(int k=0,j=0; k<l-3;k+=3,j++)
		{
			k1[j]=num[k];
			k2[j]=num[k+1];
			k3[j]=num[k+2];
			
		}
		
		
		
		
		key[0]=FrequecyAnalyzer(l,k1) ^ (int)'e';
		//System.out.println("-----------------------");
		key[1]='o'; //FrequecyAnalyzer(l,k2) ^ (int)'e';
		//System.out.println("-----------------------");
		key[2]=FrequecyAnalyzer(l,k3) ^ (int)'e';
		
		
		Decrypt();
		
		
	}
	
	
	static int FrequecyAnalyzer(int l, int[]k){
		
		
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		
	
		for(int m=0;m<l/3;m++){
			
			int count=0;
			int cc;
			if (k[m]==0)
				continue;
			else
				cc=k[m];
			
		
			
		for(int n=m+1;n<l/3;n++){
			
			if(cc == k[n] && k[n]!=0){
				count++;
				k[n]=0;
							
		}
			
		}
		
			map.put(count, cc);	
			
					
			//System.out.print(cc + " "+ count + " " );
			//System.out.println((char)(cc ^ (int)'e'));
			//System.out.println("");
		}
		       
		
		
		        int z = (int)(map.values().toArray()[map.size()-2]);
		        return (z);
		        //System.out.println(z ^ (int)'e');
		        
		/*
		        z = (int)(map.values().toArray()[map.size()-1]);
		        System.out.println(z ^ (int)'e');
		        z = (int)(map.values().toArray()[map.size()-2]);
		        System.out.println(z ^ (int)'e');
		        z = (int)(map.values().toArray()[map.size()-3]);
		        System.out.println(z ^ (int)'e');
		        */		
	}
	
	static void Decrypt(){
		
		int sum=0;
		for(int i=0;i<num.length;i++){
			
			num[i]=num[i] ^ key[i % 3];
			sum+=num[i];
			System.out.print((char)num[i]);		
			
		}
		System.out.println();
		System.out.println();
		System.out.println("The sum of ascii values in the original text is " + sum);
		
		
	}
	
}

