import java.security.*;
import java.util.*;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




public class StringUtil{

	

    // Function to apply SHA-256 to the input and return the encrypted result.
	public static String applySha256(String input){

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			//Inbuilt function to apply SHA-256 

			byte[] hash = digest.digest(input.getBytes("UTF-8"));

			// This will contain hash as hexidecimal
			StringBuffer hexString = new StringBuffer(); 

			for (int i = 0; i < hash.length; i++) {

				String hex = Integer.toHexString(0xff & hash[i]);

				if(hex.length() == 1) hexString.append('0');

				hexString.append(hex);

			}

			return hexString.toString();

		}

		catch(Exception e) {

			throw new RuntimeException(e);

		}

	}

	

	//Apply ECDSA Signature and returns the result.

	

	
	public static String getDificultyString(int difficulty) {

		return new String(new char[difficulty]).replace('\0', '0');

	}
	//Verify String signature 

	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {

		try {

			Signature verify_ECDSA = Signature.getInstance("ECDSA", "BC");

			verify_ECDSA.initVerify(publicKey);

			verify_ECDSA.update(data.getBytes());

			return verify_ECDSA.verify(signature);

		}catch(Exception e) {

			throw new RuntimeException(e);

		}

	}
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) {

		Signature dsa;

		byte[] output = new byte[0];

		try {

			dsa = Signature.getInstance("ECDSA", "BC");

			dsa.initSign(privateKey);

			byte[] strByte = input.getBytes();

			dsa.update(strByte);

			byte[] realSig = dsa.sign();

			output = realSig;

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

		return output;

	}
	

	

	

}