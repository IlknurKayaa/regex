/**
*
* @author Ýlknur KAYA ilknur.kaya3@ogr.sakarya.edu.tr
* @since 18.04.2023
* <p>
* 1. Öðretim A Grubu
* </p>
*/
package cc.program;
import java.util.regex.Pattern;
import cc.program.Dosya;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
public class Hesap {
	//operatorleri tutmasi icin arraylistler tanimlandi.
		ArrayList<String>tekli_yorum=new ArrayList<String>();
		ArrayList<String>coklu_yorum=new ArrayList<String>();
		ArrayList<String>javadoc_yorum=new ArrayList<String>();
		// regex ifadeler ile operator ve yorum satirlari tanimlandi.
  		Pattern tekli=Pattern.compile("^(?!\\*).*//.*", Pattern.MULTILINE);
		Pattern sinif=Pattern.compile( "(?<=((public|private|protected)?\\s?((abstract)?(class)|(interface))\\s))(\\w*)(?=(\\s?[{]))",Pattern.DOTALL);
		Pattern coklu=Pattern.compile("/\\*.*?\\*/|[/][*][ ].*|[/][*]$.*", Pattern.DOTALL);
		Pattern javadoc=Pattern.compile("[/][*][*].*",Pattern.DOTALL);
		Pattern func=Pattern.compile("(?<=((public|private|protected)?\\s(final|synchronized|volatile|transient|native|strictfp|abstract)?)\\s?(void|int|double|float|long|short|byte|boolean|char|String)?\\s?)(?!(class|for|if|else|do|while|switch))(\\w*)(?=(\\s?[(].*?[)]\\s?[{]))",Pattern.DOTALL);
		public void hesap(String dosyaad) throws IOException {
			try {
			File dosya=new File(dosyaad);
			BufferedReader oku=new BufferedReader(new FileReader(dosya));
			Dosya dosya1=new Dosya();
			dosya1.dosyaOlusturAc("teksatir.txt");
			dosya1.dosyaOlusturAc("coksatir.txt");
			dosya1.dosyaOlusturAc("javadoc.txt");
			String satir=null;
			String yedek=null;

			int sayacfunc=0;
			while((satir=oku.readLine())!=null) { 
				// satir satir okuma islemine baslandi
				// satirlardan bosluklar silinerek matcherlar yazildi.
				Matcher sinif_Matcher=sinif.matcher(satir.trim()); 
				Matcher func_Matcher=func.matcher(satir.trim());
				Matcher javadoc_Matcher=javadoc.matcher(satir.trim());
				if (sinif_Matcher.find()) {
					String sinif =sinif_Matcher.group();
					 System.out.println("Sýnýf:"+sinif);
				}
				//javadoc
				while (javadoc_Matcher.find()) {
                    String yorum = javadoc_Matcher.group();
                    if (yorum.startsWith("/**") && !yorum.endsWith("*/")) {
                        String sonrakiSatir = oku.readLine();
                        while (sonrakiSatir != null && !sonrakiSatir.trim().endsWith("*/")) {
                            yorum += "\n" + sonrakiSatir;
                            sonrakiSatir = oku.readLine();
                        }
                        yorum += "\n" + sonrakiSatir;
                    }
                    javadoc_yorum.add(yorum);
                    
                    
                }
				//fonksiyon
				 while (func_Matcher.find()){
					 String func = func_Matcher.group();
					 System.out.println("      Fonksiyon:"+func);
	                 //String listEkle="  Foksiyon:"+func+"\n";   
	                        String sonraki = oku.readLine();
	                        yedek=sonraki;
	                        sayacfunc=sayacfunc+1;
	                        while (sonraki != null && sayacfunc>0) {
	                        	if(sonraki.contains("{")){
	                        		sayacfunc=sayacfunc+1;
	                        	}
	                        	 if (sonraki.contains("}")) {
									sayacfunc=sayacfunc-1;
									if (sayacfunc==0) {
										 System.out.println("          Tek Satýr Yorum Sayýsý:  "+tekli_yorum.size());
										 System.out.println("          Cok Satýr Yorum Sayýsý:  "+coklu_yorum.size());
										 System.out.println("          Javadoc Yorum Sayýsý:    "+javadoc_yorum.size());
										 System.out.println("--------------------------------------");
										 dosya1.dosyaYaz("teksatir.txt",func,tekli_yorum);
										 dosya1.dosyaYaz("coksatir.txt",func,coklu_yorum);
										 dosya1.dosyaYaz("javadoc.txt",func,javadoc_yorum);
										 tekli_yorum.clear();
										 coklu_yorum.clear();
										 javadoc_yorum.clear();
										break;
									}
								}
	                        	Matcher tekli_Matcher=tekli.matcher(sonraki.trim()); 
	            				Matcher coklu_Matcher=coklu.matcher(yedek.trim()); 
	            				Matcher javadoc2_Matcher=javadoc.matcher(yedek.trim());
	                        	//tekli
								if(tekli_Matcher.find()) {
									String yorum1=tekli_Matcher.group();
									if(yorum1.length()!=0) {
										tekli_yorum.add(yorum1);
									}

								}
								//coklu yorum
								 while (coklu_Matcher.find()) {
					                    String yorum = coklu_Matcher.group();
					                    if (yorum.startsWith("/*") && !yorum.endsWith("*/")) {
					                        String sonrakiCok = oku.readLine();
					                        while (sonrakiCok != null && !sonrakiCok.trim().endsWith("*/")) {
					                            yorum += "\n" + sonrakiCok;
					                            sonrakiCok = oku.readLine();
					                        }
					                        yorum += "\n" + sonrakiCok;
					                    }
					                    coklu_yorum.add(yorum);
					                }
								 //javadoc
							 while (javadoc2_Matcher.find()) {
				                    String yorum = javadoc2_Matcher.group();
				                    if (yorum.startsWith("/**") && !yorum.endsWith("*/")) {
				                        String sonraki2 = oku.readLine();
				                        while (sonraki2 != null && !sonraki2.trim().endsWith("*/")) {
				                            yorum += "\n" + sonraki2;
				                            sonraki2 = oku.readLine();
				                        }
				                        yorum += "\n" + sonraki2;
				                    }
				                    javadoc_yorum.add(yorum);
							 }
	                            sonraki = oku.readLine();
	                            yedek=sonraki;
	                        }//while sonu
	                        if(sayacfunc==0){
	                        	break;
	                        }

	                    }
                } //satir oku 
			
				
			//dosya okumayi kapat
			oku.close();
			}

			catch (FileNotFoundException ex) 
	        {
	            System.out.println("Dosya bulunamadi.");
	        }     
			
		}
		

}
