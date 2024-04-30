/**
*
* @author Ýlknur KAYA ilknur.kaya3@ogr.sakarya.edu.tr
* @since 18.04.2023
* <p>
* 1. Öðretim A Grubu
* </p>
*/
package cc.program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Dosya {
	public void dosyaOlusturAc(String dosyaAd) throws IOException{
		File f =new File(dosyaAd);
		if(f.exists()){}
		else{
			f.createNewFile();
		}
	}
	public void dosyaYaz(String dosyaAd,String func,ArrayList<String> list) throws IOException{
		 try {
	            FileWriter writer = new FileWriter(dosyaAd,true);
	            writer.write("Foksiyon:"+func+"\n");
	            for (String str : list) {
	                writer.write(str);
	            }
	            writer.write("\n"+"---------------"+"\n");
	            writer.close();
	        } catch (IOException e) {
	            System.out.println("Dosyaya yazdirma hatasi olustu.");
	            e.printStackTrace();
	        }
	}
	

}
