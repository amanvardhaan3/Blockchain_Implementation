
import java.util.*;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Record 

{

    private ArrayList<String> data = new ArrayList<String>();
    String patient;
    String doctor;
    
    void print_Data()
    {
    	for(int i=0;i<data.size();i++)
    	{
    		System.out.println(data.get(i));
    	}
    }
    void add_Users(String doctor,String patient)
    {
    	this.doctor = doctor;
    	this.patient = patient;
    }

    void add_Data(String medData)
    {
    	this.data.add(medData);
    }

    
    
    
}