package no.site.vaccoin.main;

import java.security.PublicKey;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.gson.*;

public class Vacchain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>(); //list of all unspent transactions.
	public static int difficulty = 5;
	protected static PublicKey coinbasePublicKey;
	public static float minimumTransaction = 0.1f;
//	public static Transaction genesisTransaction;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		blockchain.add(new Block("There is some data for our block", "0")); 
		System.out.println("Hash for block "+blockchain.size()+" : "+blockchain.get(blockchain.size()-1).hash);
		System.out.println("prevHash for block "+blockchain.size()+" : "+blockchain.get(blockchain.size()-1).previousHash);
		blockchain.get(0).mineBlock(difficulty);
//		
//		blockchain.add(new Block("There is some data for our SECOND block", blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Hash for block "+blockchain.size()+" : "+blockchain.get(blockchain.size()-1).hash);
//		System.out.println("prevHash for block "+blockchain.size()+" : "+blockchain.get(blockchain.size()-1).previousHash);
//		blockchain.get(1).mineBlock(difficulty);
//		
//		blockchain.add(new Block("There is some data for our THIRD block", blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Hash for block "+blockchain.size()+" : "+blockchain.get(blockchain.size()-1).hash);
//		System.out.println("prevHash for block "+blockchain.size()+" : "+blockchain.get(blockchain.size()-1).previousHash);
//		blockchain.get(2).mineBlock(difficulty);
//		
		System.out.println("\n\nBlockchain is Valid: " + isChainValid());
//		
		String blockchainJson = new GsonBuilder().create().toJson(blockchain.get(0));		
		System.out.println(blockchainJson);
		*/
		//Setup Bouncey castle as a Security Provider
		Security.addProvider(new BouncyCastleProvider()); 
		//Create the new wallets
		Wallet walletA = new Wallet();
		Wallet walletB = new Wallet();
		Wallet walletC = new Wallet();
		Wallet coinbase = new Wallet();
		coinbasePublicKey = coinbase.publicKey;
		String genesisId = "0";
		
//		coinbase.UTXOs.put(genesisOutputId, new TransactionOutput(coinbase.publicKey, 100f, genesisOutputId));
//		UTXOs.put(coinbase.UTXOs.get(genesisOutputId).id, coinbase.UTXOs.get(genesisOutputId));
		//base release
		TransactionOutput baseOutput1 = new TransactionOutput(coinbase.publicKey, 100f, genesisId);
		UTXOs.put(baseOutput1.id, baseOutput1);
//		TransactionOutput baseOutput2 = new TransactionOutput(walletA.publicKey, 50f, genesisId);
//		UTXOs.put(baseOutput2.id, baseOutput2);
		//check
//		System.out.println(UTXOs.toString());
//		System.out.println("\nWalletB's balance is: " + walletB.getBalance());
//		System.out.println("WalletA's balance is: " + walletA.getBalance());
//		System.out.println("coinbase balance is: " + coinbase.getBalance());
//		System.out.println("walletA utxo: "+walletA.UTXOs.toString());
//		System.out.println("walletB utxo: "+walletB.UTXOs.toString());
//		System.out.println("coinbase utxo: "+coinbase.UTXOs.toString());
		//testing
		Block block1 = new Block(genesisId);
		System.out.println("\ncoinbase is Attempting to send funds (50) to WalletA...");
		block1.addTransaction(coinbase.sendFunds(walletA.publicKey, 50f));
//		System.out.println(UTXOs.toString());
//		System.out.println("\nWalletB's balance is: " + walletB.getBalance());
//		System.out.println("WalletA's balance is: " + walletA.getBalance());
//		System.out.println("coinbase balance is: " + coinbase.getBalance());
//		System.out.println("walletA utxo: "+walletA.UTXOs.toString());
//		System.out.println("walletB utxo: "+walletB.UTXOs.toString());
//		System.out.println("coinbase utxo: "+coinbase.UTXOs.toString());
		System.out.println("\ncoinbase is Attempting to send funds (50) to WalletB...");
		block1.addTransaction(coinbase.sendFunds(walletB.publicKey, 50f));	
		addBlock(block1);
//		System.out.println(UTXOs.toString());
//		System.out.println("\nWalletB's balance is: " + walletB.getBalance());
//		System.out.println("WalletA's balance is: " + walletA.getBalance());
//		System.out.println("walletA utxo: "+walletA.UTXOs.toString());
//		System.out.println("walletB utxo: "+walletB.UTXOs.toString());
//		System.out.println("coinbase utxo: "+coinbase.UTXOs.toString());
		
		Block block2 = new Block(blockchain.get(blockchain.size()-1).hash);
		System.out.println("WalletB is Attempting to send funds (30) to WalletC...");
		block2.addTransaction(walletB.sendFunds(walletC.publicKey, 30f));

//		System.out.println("\nWalletB's balance is: " + walletB.getBalance());
//		System.out.println("WalletA's balance is: " + walletA.getBalance());
		
//		System.out.println(UTXOs.toString());
		
//		Block block3 = new Block(blockchain.get(blockchain.size()-1).hash);
		System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
		block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000f));
		
		
		System.out.println("\nWalletA Attempting to send funds (10) to coinbase...(restricted!!!)");
		block2.addTransaction(walletA.sendFunds(coinbase.publicKey, 10f));
//		addBlock(block3);
//		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
//		System.out.println("WalletB's balance is: " + walletB.getBalance());
//		System.out.println("walletA utxo: "+walletA.UTXOs.toString());
//		System.out.println("walletB utxo: "+walletB.UTXOs.toString());
//		System.out.println("coinbase utxo: "+coinbase.UTXOs.toString());
		
//		Block block4 = new Block(blockchain.get(blockchain.size()-1).hash);
		System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
		block2.addTransaction(walletB.sendFunds( walletA.publicKey, 20)); 
		System.out.println("\nWalletA's balance is: " + walletA.getBalance());
		System.out.println("WalletB's balance is: " + walletB.getBalance());
		System.out.println("WalletC's balance is: " + walletC.getBalance());
		addBlock(block2);
		System.out.println("\nStart validating...");
		isChainValid();
	}
	
	public static boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>();//a temporary working list of unspent transactions at a given block state.
		
		for (Transaction trx : blockchain.get(0).transactions) 
			for (TransactionOutput trxoutput : trx.outputs) if (!trxoutput.reciepient.equals(coinbasePublicKey)) tempUTXOs.put(trxoutput.id, trxoutput);
		
		System.out.println(tempUTXOs.toString());
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			if (!currentBlock.previousHash.equals(previousBlock.hash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
			//loop thru blockchains transactions:
			TransactionOutput tempOutput;
			for(int t=0; t <currentBlock.transactions.size(); t++) {
				Transaction currentTransaction = currentBlock.transactions.get(t);
				
				if(!currentTransaction.verifiySignature()) {
					System.out.println("#Signature on Transaction(" + t + ") is Invalid");
					return false; 
				}
				if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
					System.out.println("#Inputs are note equal to outputs on Transaction(" + t + ")");
					return false; 
				}
				for(TransactionInput input: currentTransaction.inputs) {	

					tempOutput = tempUTXOs.get(input.transactionOutputId);
					
					if(tempOutput == null) {
						System.out.println("#Referenced input on Transaction(" + t + ") is Missing");
						return false;
					}
					
					if(input.UTXO.value != tempOutput.value) {
						System.out.println("#Referenced input Transaction(" + t + ") value is Invalid");
						return false;
					}
					
					tempUTXOs.remove(input.transactionOutputId);
				}
				
				for(TransactionOutput output: currentTransaction.outputs) {
					tempUTXOs.put(output.id, output);
				}

				if( currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
					System.out.println("#Transaction(" + t + ") output reciepient is not who it should be");
					return false;
				}
				
				if (currentTransaction.value != currentTransaction.getInputsValue()) {//exclude 0f leftover cases
					if( currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
						System.out.println("#Transaction(" + t + ") output 'change' is not sender.");
						return false;
					}
				}
			}
			
		}
		System.out.println("Blockchain is valid");
		return true;
		}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
