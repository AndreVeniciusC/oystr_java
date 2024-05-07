package oystr.java.test;

import oystr.java.test.process.Ad;
import oystr.java.test.process.BotAgrofy;
import oystr.java.test.process.BotMercMaq;
import oystr.java.test.process.BotTratColh;
import oystr.java.test.process.MachineImpl;

/*
 * The application MUST be developed in Java using the concepts of Object Oriented Programming. 
 * You may also use concepts such as Dependency Injection and Inversion of Control.
 * You may modify the main method to better suit your approach, but you MAY NOT change the concept itself.
 */
public class Main {

	public static void main(String[] args) {
		/*
		 * TODO: initialize a bot impl class and decide how it's going to be applied/used here.
		 */
		String sDelim="";
		if(args.length>0) {
			if(args[0].toUpperCase().startsWith("CSV")) {
				sDelim = args[0].substring(args[0].indexOf("=")+1,args[0].indexOf("=")+2);
				processar(sDelim);
			} else if(args[0].toUpperCase().compareTo("JSON")==0) {
				processar("json");				
			} else {
				System.err.println("Execução: [programa] [parametros] onde:\n[programa] é o nome do jar.\n[parametros] pode ser:\n");
				System.err.println("json - para saída em json\ncsv=[D] para saida com caracter delimitador, onde D é o caracter delimitador. Ex.:|\n");
			}
		} else {
			System.err.println("Execução: [programa] [parametros] onde:\n[programa] é o nome do jar.\n[parametros] pode ser:\n");
			System.err.println("json - para saída em json\ncsv=[D] para saida com caracter delimitador, onde D é o caracter delimitador. Ex.:|\n");			
		}
	}
	
	public static void processar(String sDelim) {
		Bot bot;	
		MachineImpl m = new MachineImpl();
		String sAds="",sAux="";
		/*
		 * TODO: If any of these pages does not work, you may look to new ones in the root page for each domain.
		 */
		String [] urls = new String[] {
			"https://www.agrofy.com.br/bh-145-unico-dono.html",
			"https://www.agrofy.com.br/trator-john-deere-5075e-c-kit-frontal-tatu-pca-600.html",
			"https://www.agrofy.com.br/trator-farmall-90-case-ih-trac-o-4x4-cabinado.html",
			"https://www.agrofy.com.br/trator-mahindra-5050-novo-49cv-4x4-4cilindros.html",
			"https://www.agrofy.com.br/trator-mahindra-6075-novo-80cv-4x4-4cilindros.html",
			"https://www.tratoresecolheitadeiras.com.br/veiculo/perdoes/mg/trator/john-deere/john-deere-5090/2016/tracao-4x4/cabine-cabinado/minas-verde---semi-novos/1114441",
			"https://www.tratoresecolheitadeiras.com.br/veiculo/uberlandia/mg/plataforma-colheitadeira/gts/flexer-xs-45/2023/45-pes/draper/triamaq-tratores/1028839",
			"https://www.tratoresecolheitadeiras.com.br/veiculo/uberlandia/mg/plataforma-colheitadeira/gts/produttiva-1250/2022/caracol/12-linhas/triamaq-tratores/994257",
			"https://www.mercadomaquinas.com.br/anuncio/231207-trator-de-esteira-komatsu-d65-1978-criciuma-sc",
			"https://www.mercadomaquinas.com.br/anuncio/218193-escavadeira-caterpillar-320c-2006-aruja-sp",
			"https://www.mercadomaquinas.com.br/anuncio/214554-pa-carregadeira-caterpillar-950h-2012-curitiba-pr"
		};
		
		for(String s : urls) {
			bot = createBot(s);			
			m.getAds().add(bot.fetch(s));			
		}
		if(m.getAds().size()>0) {
			if(sDelim.compareTo("json")==0) {
				for (Ad ad : m.getAds()) {
					sAds += sAux + ad.toJSON();
					sAux=",";
				}
				System.err.println("{\"ads\":[" + sAds + "]}" + "\n");	
			} else if(sDelim.compareTo("")!=0) {
				sAds = Ad.getCabecalho(sDelim);
				sAux = "Erros";							
				for (Ad ad : m.getAds()) {
					//	System.err.println(ad.toJSON());
					if(ad.getTipo()) {
						sAds+="\n" + ad.export(sDelim);
					} else {
						sAux+="\n" + ad.getErro();
					}
				}
				System.err.println(sAds + "\n\n" + sAux);
			}
		}
	}
	
	public static Bot createBot(String sSite) {
		Bot bot = null;
		if(sSite.startsWith("https://www.agrofy.com.br")) {
			bot = new BotAgrofy();
		} else if (sSite.startsWith("https://www.tratoresecolheitadeiras.com.br")) {
			bot = new BotTratColh();
		} else if (sSite.startsWith("https://www.mercadomaquinas.com.br")) {
			bot = new BotMercMaq();
		}
		return bot;
	}
}