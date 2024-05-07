package oystr.java.test.process;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import oystr.java.test.Bot;

public class BotTratColh implements Bot {

	@Override
	public Ad fetch(String sURL) {		
		URL url;
		String sBuffer=null;
		String sAux;
		InputStream is;
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
				sAux = Mining.TratoresColheitadeiras.getPictureURL(sBuffer);
				if(sAux.compareTo("")!=0) {					
					ad.setPictureURL(sAux);
					ad.setMake(Mining.TratoresColheitadeiras.getTag(sBuffer,"Marca"));
					ad.setModel(Mining.TratoresColheitadeiras.getTag(sBuffer,"Modelo"));
					ad.setYear(Mining.TratoresColheitadeiras.getTag(sBuffer,"Ano de Fabricação"));
					ad.setCity(Mining.TratoresColheitadeiras.getLocalizacao(sBuffer));
					ad.setPrice(Mining.TratoresColheitadeiras.getTag(sBuffer,"Preço"));
					ad.setWorkHours(Mining.TratoresColheitadeiras.getTag(sBuffer, "Horas"));
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
