package tlgrm.bot.vacchain;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class BotMain {
	public static void main (String []args) {
		Security.addProvider(new BouncyCastleProvider()); 
		
	}
}
