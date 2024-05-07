package oystr.java.test;

import oystr.java.test.process.Ad;

public interface Bot {	
	String sErroAnuncio = "Anuncio Finalizado ou Pagina com formato diferente do previsto.";
	Ad fetch(String s);
}
