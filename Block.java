 
import java.util.Date;
import java.util.ArrayList;
import java.lang.*;
import java.util.*;
import java.util.Random;
import java.io.IOException;
import java.util.Scanner;


public class Block {

    private String block_hash;

    public long time_stamp_rec;

    private Record curr_medical_data;

    private String previous_Hash_val;
    
    public int nonce;
    public String DES_hash;


    public Block(ArrayList<Record> record, String previousHash,Record curr_med_data)

    {
        this.block_hash=calculateHash();
        this.DES_hash=DES.calc_DES(block_hash);
    	this.curr_medical_data = curr_med_data;
        this.time_stamp_rec = new Date().getTime();
        this.previous_Hash_val=previousHash;
    }

    //Calculate new hash based on blocks contents

    public String calculateHash() {

        String calculatedhash = StringUtil.applySha256(previous_Hash_val +Integer.toString(nonce));

        return calculatedhash;

    }

    

    
    public String UpperCase(String DES_hash){
        char arr[]=DES_hash.toCharArray();
        for(int i=0;i<DES_hash.length();i++){
            if(arr[i]>='a' && arr[i]<='z'){
                Character.toUpperCase(arr[i]);
            }
        }
        DES_hash = String.valueOf(arr);
        return DES_hash;
    }


    public void mineBlock(int difficulty) {

        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"

        if(!block_hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            block_hash = calculateHash();
            String s1 = new String(block_hash);
            DES_hash=DES.calc_DES(s1);
        }
        // char arr[]=DES_hash.toCharArray();
        // for(int i=0;i<DES_hash.length();i++){
        //     if(arr[i]>='a' && arr[i]<='z'){
        //         Character.toUpperCase(arr[i]);
        //     }
        // }
        // DES_hash = String.valueOf(arr);
        System.out.println("Block Mined!!! : " + DES_hash.toUpperCase());
    }

    

    public String patient_name()
    {
    	return this.curr_medical_data.patient;
    }

    public String getBlockHash() {

        return block_hash;

    }

    public void setBlockHash(String blockHash) {

        this.block_hash = blockHash;
        this.DES_hash=DES.calc_DES(block_hash);

    }

    public String docname()
    {
    	return this.curr_medical_data.doctor;
    }

    public String getPreviousHash() {

        return previous_Hash_val;

    }
    
    public void setPreviousHash(String previousHash) {

        this.previous_Hash_val = previousHash;

    }
    
    public void printData()
    {
    	curr_medical_data.print_Data();
    }

}