package oystr.java.test.process;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import oystr.java.test.Bot;

public class BotAgrofy implements Bot {
	
	@Override
	public Ad fetch(String sURL) {		
		URL url;
		String sBuffer=null;
		String sAux;
		Ad ad = new Ad();
		ad.setTipo(false);
		try {
			url = new URL(sURL);
			InputStream is;
			try {
				is = url.openStream();
				try {
					sBuffer = new String(is.readAllBytes(),StandardCharsets.UTF_8);
				} catch (IOException e) {
					ad.setErro(sURL + " - " + e.getClass().getName() + " - " + e.getMessage());
				}
				if(sBuffer!=null) {
					sAux=Mining.Agrofy.getPictureURL(sBuffer);					
					if(sAux.compareTo("")!=0) {						
						ad.setPictureURL(sAux);					
						ad.setMake(Mining.Agrofy.getTag(sBuffer,"Marca"));
						ad.setModel(Mining.Agrofy.getTag(sBuffer,"Modelo"));
						ad.setYear(Mining.Agrofy.getTag(sBuffer,"Ano de fabricação"));
						ad.setCity(Mining.Agrofy.getTag(sBuffer,"Cidade / Estado"));
						ad.setPrice(Mining.Agrofy.getPrice(sBuffer));
						ad.setWorkHours(Mining.Agrofy.getTag(sBuffer, "Horas de Uso"));
						ad.setContract(Mining.Agrofy.getTag(sBuffer, "Tipo de Operação"));
						ad.setTipo(true);
					} else {
						ad.setErro(sURL + " - " + sErroAnuncio);
					}
				}
			} catch (IOException e) {
				ad.setErro(sURL + " - " + e.getClass().getName() + " - " + e.getMessage());
			}			
		} catch (MalformedURLException e) {
			ad.setErro(sURL + " - " + e.getClass().getName() + " - " + e.getMessage());
		}		
		return ad;
	}
}
