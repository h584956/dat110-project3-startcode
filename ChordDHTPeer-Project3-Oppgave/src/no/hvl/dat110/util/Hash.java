package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	
	public static BigInteger hashOf(String entity) {		
				
		// we use MD5 with 128 bits digest
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// compute the hash of the input 'entity'
		byte[] b = md.digest(entity.getBytes());
		
		// convert the hash into hex format
		String hex = toHex(b);
		
		// convert the hex into BigInteger
		hashint = new BigInteger(hex, 16);
		
		// return the BigInteger
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		int numberOfBits = bitSize();
		
		// compute the address size = 2 ^ number of bits
		BigInteger adrSize = BigInteger.valueOf(2).pow(numberOfBits);
		
		// return the address size
		return adrSize;
	}
	
	public static int bitSize() {
		
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// find the digest length
		int digestlen = md.getDigestLength();
		
		return digestlen*8;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
