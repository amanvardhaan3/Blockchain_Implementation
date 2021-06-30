import java.util.*;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class users {
	
	private String password;
	private String name;
	
	//setters and getters of both instance variables

	String getName()
	{
		return this.name ;
	}
	void setName(String name)
	{
		this.name = name;
	}
	//Function to validate equality of two strings
	boolean validate(String s1,String s2)
	{
		if(s1.equals(s2))
			return true;
		else
			return false;
	}
	String getPassword()
	{
		return this.password ;
	}
	void setPassword(String pwd)
	{
		this.password = pwd;
	}
	
	
}
