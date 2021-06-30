// importing the required libraries
import java.util.*;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static ArrayList<Record> record_list = new ArrayList<Record>();
    public static ArrayList<users> users = new ArrayList<users>();
    public static ArrayList<users> doc_list = new ArrayList<users>();
    
    public static void register(){
        users doctor1 =  new users();
        doctor1.setPassword("1");
        doctor1.setName("Priyansh");
        users.add(doctor1);
        doc_list.add(doctor1);

        users doctor2 =  new users();
        doctor2.setPassword("2");
        doctor2.setName("Rahul");
        users.add(doctor2);
        doc_list.add(doctor2);

        users doctor3 =  new users();
        doctor3.setPassword("3");
        doctor3.setName("Aman");
        users.add(doctor3);
        doc_list.add(doctor3);

        users doctor4 =  new users();
        doctor4.setPassword("4");
        doctor4.setName("Aakar");
        users.add(doctor4);
        doc_list.add(doctor4);

        users doctor5 =  new users();
        doctor5.setPassword("5");
        doctor5.setName("Sid");
        users.add(doctor5);
        doc_list.add(doctor5);

    }
    // Verifying the chain using hash values
    public static boolean verify_Chain(Block blk)
    {
        if(blockchain.size()>0) 
        {
            if (!(blockchain.get(blockchain.size()-1).getBlockHash() == blk.getPreviousHash())) 
            {
                return false;
            }
    
        }

        for(int i=1;i<blockchain.size();i++)
        {
            if(!(blockchain.get(i).getPreviousHash()==blockchain.get(i-1).getBlockHash()))
            {
                return false;
            }
    
        }
        return true;
    }

    //Create a block and add it to blockchain
    public static String add_Record_To_Block(ArrayList<Record> record,String previousHash,Record data)
    {
        System.out.println("Trying To mineBlock...");
                
        Block blk = new Block(record, previousHash,data);
                
        blk.mineBlock(difficulty_level);
        if (verify_Chain(blk)) 
        {
            blockchain.add(blk);
        }
    
        return blk.getBlockHash();
    }
        

    public static int difficulty_level = 4;
    private static String previousHash ="0";
    // Generator and prime value
    public static int x=1;
    public static int p=11;
    public static int g=2;

    
    public static void main(String[] args) throws IOException
    {
    
        //Registering the doctors with their names and passwords
        //Adding them to the user list and doctor list
        register();
        
        String alternative="";
        do{
            
            try{	
                    System.out.println("view/add/register");
                    Scanner scnr = new Scanner(System.in); 
                    String option = scnr.nextLine();

                    if(option.equals("add"))
                    {
                        System.out.println("Enter name of doctor: ");
                        scnr = new Scanner(System.in); 
                        String doc_name = scnr.nextLine();
    
                        System.out.println("Enter password of doctor: ");
                        scnr = new Scanner(System.in); 
                        String doc_pass = scnr.nextLine();
    
                        System.out.println("Enter name of patient: ");
                        scnr = new Scanner(System.in); 
                        String patient_name = scnr.nextLine();
                        int wai=0;
                        increment(x);
                        for(int j=0;j<users.size();j++)
                        {
                            if(users.get(j).getName().equals(patient_name))
                            {
                                int x=Integer.parseInt(users.get(j).getPassword());
                                wai=calculate_expo_val(g,x,p);
                            }			
                        }
                        if(zkp_discrete_log(wai) == false)
                        {
                            continue;
                        }
                        
                        int flag =0;
                        
                        Record new_record = new Record();
                        new_record.add_Users(doc_name, patient_name);
                        decrement(x);
                        for(int i=0;i<doc_list.size();i++)
                        {
                            if(doc_list.get(i).getName().equals(doc_name) && doc_list.get(i).getPassword().equals(doc_pass))
                            {
                                for(int j=0;i<users.size();j++)
                                {
                                    if(users.get(j).getName().equals(patient_name))
                                    {
                                        while(true)
                                        {
                                            System.out.println("Enter data: y/n");
                                            scnr = new Scanner(System.in);
                                            String input = scnr.nextLine();
                                            if(input.equals("n"))
                                            {
                                                record_list.add(new_record);
                                                previousHash = add_Record_To_Block(record_list,previousHash,new_record);
                                                flag =1;
                                                break;
                                                
                                            }
                                            else
                                            {
                                                scnr = new Scanner(System.in);
                                                String data = scnr.nextLine();
                                                new_record.add_Data(data);
                
                                            }
                                        }
                                        
                                    }
                                    if(flag == 1)
                                        break;
                                }
                                
                            }
                            if(flag == 1)
                                break;
                        }
                        if (flag == 0)
                        {
                            System.out.println("Something is wrong");
                        }
                        
                    }

                    //Code to register new patients for a single transaction
                    else if(option.equals("register"))
                    {
                        users new_patient = new users();
                        System.out.println("Enter name:");
                        scnr = new Scanner(System.in); 
                        String name = scnr.nextLine();
                        new_patient.setName(name);
    
                        System.out.println("Enter pass:(only integer)");
                        scnr = new Scanner(System.in); 
                        String pass = scnr.nextLine();
                        new_patient.setPassword(pass);
    
                        System.out.println("Verify pass:");
                        scnr = new Scanner(System.in); 
                        String verify_pass = scnr.nextLine();
                        if(new_patient.validate(pass,verify_pass))
                            {
                                System.out.println("User Registered Successfully!!!");
                                users.add(new_patient);
                            }
                        else{
                            System.out.println("Passwords do not match");
                        }
                        increment(x);
                    }

                    else if(option.equals("view"))
                    {
                        decrement(x);
                        System.out.println("Are you a doctor or patient? Enter d/p: ");
                        scnr = new Scanner(System.in);
                        String value = scnr.nextLine();
                        if(value.equals("d"))
                        {
                            int flag=0;
                            System.out.println("Enter your name:");
                            scnr = new Scanner(System.in);
                            String doc_name = scnr.nextLine();
                            System.out.println("Enter your pass:");
                            String doc_pass = scnr.nextLine();
                            
                            for(int i=0;i<doc_list.size();i++)
                            {
                                if(doc_list.get(i).getName().equals(doc_name) && doc_list.get(i).getPassword().equals(doc_pass))
                                {
                                    for(int k=0;k<blockchain.size();k++)
                                    {
                                        if(blockchain.get(k).docname().equals(doc_name))
                                        {
                                            flag=1;
                                            System.out.println("Time when data was captured: "+blockchain.get(k).time_stamp_rec);
                                            System.out.println("Doctor name: "+doc_name);
                                            System.out.println("Patient: "+blockchain.get(k).patient_name());
                                            System.out.println("His Medical Data: ");
                                            blockchain.get(k).printData();	
                                            System.out.println();
                                            increment(x);
    
                                        }
                                    }
                                    if(flag==1)
                                        break;
                                }	
                            }
                            if(flag==0)
                                System.out.println("Doctor Not Found in the List");
                        }
    
                        else if(value.equals("p"))
                        {
                            int wai=0;
                            System.out.println("Enter your name: ");
                            scnr = new Scanner(System.in);
                            String patient = scnr.nextLine();
                            
                            for(int j=0;j<users.size();j++)
                            {
                                if(users.get(j).getName().equals(patient))
                                {
                                    int x=Integer.parseInt(users.get(j).getPassword());
                                    wai=calculate_expo_val(g,x,p);
                                }			
                            }
                            if(zkp_discrete_log(wai) == false)
                            {
                                continue;
                            }
                            int flag=0;
                            for(int i=0;i<users.size();i++)
                            {
                                if(users.get(i).getName().equals(patient))
                                {
                                    for(int k=0;k<blockchain.size();k++)
                                    {
                                        if(blockchain.get(k).patient_name().equals(patient))
                                        {
                                            decrement(x);
                                            System.out.println("Time: "+blockchain.get(k).time_stamp_rec);
                                            System.out.println("Doctor: "+blockchain.get(k).docname());
                                            System.out.println("Patient: "+blockchain.get(k).patient_name());
                                            System.out.println("Patient's Medical Data: ");
                                            blockchain.get(k).printData();
                                            System.out.println();
                                            flag=1;
                                        }
                                    }
                                    if(flag==1)
                                        break;
                                }
                                
                            }
                            if(flag==0)
                                System.out.println("Patient Not Found");
                                
                        }
                    
                        else
                        {
                            System.out.println("Invalid Input");
    
                        }
                    }
                    

                }
            catch(Exception e){
                System.out.println("Some error occured");
            }
            System.out.println("Do you want to continue?(yes/no)");
            Scanner scnr = new Scanner(System.in); 
            alternative=scnr.nextLine();
    
        }while(alternative.equals("yes"));
    }
    
    public static int increment(int x){
        return x++;
    }
    public static int decrement(int x){
        return x--;
    }
    public static Boolean zkp_discrete_log(int y1)
        {
    
            
            System.out.println("Zero Knowledge Proof");
            System.out.println("\nKindly verify yourself as a user");
            System.out.println("Choose a random number between 0 and 9(r): ");
            System.out.println("Please compute h=(2^r)(mod 11) and Enter h: ");
            Scanner scnr = new Scanner(System.in);
            int h=scnr.nextInt();
            System.out.println("h is "+ h );
            Random rand = new Random();
            int b=rand.nextInt(2);
            System.out.println("Random bit(b) is: "+b);
            System.out.println("Please compute s=(r+b*x)mod(10).Here x is your password): ");
            int s=scnr.nextInt();
    
            int a1=calculate_expo_val(2,s,11);
            int a2=(h*calculate_expo_val(y1,b,11))%11;
            
    
            if(a1==a2)
            {
                 System.out.println("Zero Knowledge Proof Successful.You are verified as registered user\n");
                 return true;
            }
           
            else
            {
                System.out.println("Zero Knowledge Proof Failed. Please try once more\n");
                return false;
            }
        }
    
     public static int calculate_expo_val(int a,int b,int c)
        {
            int res=1;
            for(int i=0;i<=b-1;i++)
            {
                //using multiplicative modulo property
                res=((res%c)*(a%c))%c;
            }
            return res;
            
        }
        
        public static Boolean ZKP(int x)
        {
            Random rand = new Random();
            int b=rand.nextInt(2);
            int r=rand.nextInt(p-1);
            int y=calculate_expo_val(g,x,p);
            int h=calculate_expo_val(g,r,p);
            
            int s=(r+b*x)%(p-1);
            int a1=calculate_expo_val(g,s,p);
            int a2=(h*calculate_expo_val(y,b,p))%p;
            if(a1==a2)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
       
}