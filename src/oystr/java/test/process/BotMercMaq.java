package oystr.java.test.process;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import oystr.java.test.Bot;

public class BotMercMaq implements Bot {
	@Override
	public Ad fetch(String sURL) {
		URL url;
		String sBuffer=null;
		InputStream is;
		String sAux;
		Ad ad = new Ad();
		ad.setTipo(false);
		try {
			//is = url.openStream();
			url = new URL(sURL);
			URLConnection urlCon = url.openConnection();
			urlCon.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");			
		    is = urlCon.getInputStream();
			try {
				sBuffer = new String(is.readAllBytes(),StandardCharsets.UTF_8);				
			} catch (IOException e) {
				ad.setErro(sURL + " - " + e.getClass().getName() + " - " + e.getMessage());
			}
			if(sBuffer!=null) {
				sAux = Mining.MercMaq.getTag(sBuffer,"image");
				if(sAux.compareTo("")!=0) {
					ad.setPictureURL(sAux);
					ad.setMake(Mining.MercMaq.getTag(sBuffer,"brand"));
					ad.setModel(Mining.MercMaq.getTag(sBuffer,"model"));
					ad.setYear(Mining.MercMaq.getTag(sBuffer,"Ano Fabricação"));
					ad.setCity(Mining.MercMaq.getTag(sBuffer,"City"));
					ad.setPrice(Mining.MercMaq.getTag(sBuffer,"price"));
					ad.setWorkHours(Mining.MercMaq.getTag(sBuffer, "Horas trabalhadas"));
					ad.setContract("Venda");					
					ad.setTipo(true);
				} else {
					ad.setErro(sURL + " - " + sErroAnuncio);
				}
			}
		} catch (IOException e) {
			ad.setErro(sURL + " - " + e.getClass().getName() + " - " + e.getMessage());
		}	
		return ad;
	}
}
