/**
 * @(#)Person.java
 *
 *
 * @author 	Goh Kok Dong
 * @		Superclass for Hoomans
 * @version 1.00 2019/8/8
 */


public abstract class Person implements PersonName{
	
	protected Name name;
	
    public Person() {
    	name = new Name();
    }
    
    public Person(Name name) {
    	this.name = name;
    }
    
    public void rename(Name name) {
    	this.name = name;
    }
    
    public String getName() {
    	return String.format("%-25s", name.getName());
    }
    	
    
    public String toString() {
    	return String.format("%-40s", name);
    }
}