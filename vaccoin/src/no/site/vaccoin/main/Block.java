package no.site.vaccoin.main;

import java.util.ArrayList;

public class Block {
	public String hash;
	public String previousHash;
	public String merkleRoot;
	public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message.
	private long ts;
	
	private int nonce;
	
	public Block(String prevHash){
		this.previousHash = prevHash;
		this.ts = System.currentTimeMillis();
		this.hash = calculateHash();
	}
	
	
	public String calculateHash() {
		String calculatedhash = HashUtil.applySHA256( 
				previousHash +
				Long.toString(ts) +
				Integer.toString(nonce) +
				merkleRoot 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) {
		long now = System.currentTimeMillis();
		merkleRoot = HashUtil.getMerkleRoot(transactions);
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
//			System.out.println(hash);
		}
		System.out.println("Block Mined!!! : " + hash);
		System.out.println("Mined for "+(System.currentTimeMillis() - now)/1000+" sec");
	}
	
	//Add transactions to this block
	public boolean addTransaction(Transaction transaction) {
		//process transaction and check if valid, unless block is genesis block then ignore.
		if(transaction == null) {
			System.out.println("Transaction has not added to Block. Discarded.");
			return false;		
		}
//		if((previousHash != "0")) {
			if((transaction.processTransaction() != true)) {
				System.out.println("Transaction failed to process. Discarded.");
				return false;
			}
//		}
		transactions.add(transaction);
		System.out.println("Transaction Successfully added to Block");
		return true;
	}
}
