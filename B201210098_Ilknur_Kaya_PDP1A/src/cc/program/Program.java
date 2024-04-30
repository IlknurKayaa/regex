/**
*
* @author Ýlknur KAYA ilknur.kaya3@ogr.sakarya.edu.tr
* @since 18.04.2023
* <p>
* 1. Öðretim A Grubu
* </p>
*/
package cc.program;

import java.io.IOException;

public class Program {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String dosyaadi="Motor.java";
		String dosyaadi=args[0];
		Hesap l=new Hesap(); // Lexical sinifindan nesne olusturuldu.
		l.hesap(dosyaadi); // komut satirindan alinan dosya adý parametresi hesap fonksiyonuna gönderildi ve hesap fonksiyonu cagirildi.

	}

}
