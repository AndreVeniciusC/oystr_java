package oystr.java.test.process;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import oystr.java.test.Bot;

class BotAgrofyTest {

	@Test
	void testFetch() {
		Bot bot;
		bot = new BotAgrofy();
		Ad ad = bot.fetch("hts://www.agrofy.com.br/trator-john-deere-5075e-c-kit-frontal-tatu-pca-600.html");		
		if(ad.getTipo()) {
			return;
		}
			fail(ad.getErro());
	}

}
