/**
 * AWT Sample application
 *
 * @author 	Goh Kok Dong
 * @ 		Driver Program
 * @version 1.00 19/08/08
 */
 
 import java.util.Scanner;
 import java.text.ParseException;
 import java.lang.String;
 

 
public class Salon_POS {
    
    // Data for calculation
    static int [] qty = new int[10000];
    static double[] salesAmt = new double[10000];
    
    public static void main(String[] args) throws ParseException {
    	Scanner in = new Scanner(System.in);
    	
    	// Declaration
    	Person[] p = new Person[10000];
    	Service[] s = new Service[7];
    	Product[] prod = new Product[10000];
    	Transaction[] tReceived = new Transaction[10000];
    	
    	Transaction trans = new Transaction();
    	Transaction calculated = new Transaction();
    	Report r = new Report();
    	
    	char confirm, isMem, nextCust = 'Y', paymentMethod, proceed;
    	int custNo = 1, memberIndex, staffIndex, choice, choice2, choice3, qtyIn, purchaseLimit = 0, prodIndex = 4;
    	double amtPay, totalBank = 0, totalRM = 0;
    	
    	boolean validManager = false;
    	String memberID = "", managerID, managerPass;
		
    	p[0] = new Manager("Sunny", "Chan", "123456"); //Manager ID: A01
    	p[1] = new Manager("Wendy", "Ng", "abcxyz"); //Manager ID: A02
    	p[2] = new Staff("Mary", "Hong");
    	p[3] = new Member("Eric", "Goh");
    	p[4] = new Staff("Desmond", "Wong");
    	p[5] = new Member("King Hong", "Lee");
    	p[6] = new Member("Kenny", "Lim");
    	p[7] = new Member("Sammy", "Wong");
    	p[8] = new Staff("Anson", "Lim");
    	p[9] = new Staff("Elena", "Lim");	
    	
    	prod[0] = new Product("Oribe Dry Texturing Spray", 60);
    	prod[1] = new Product("Moroccanoil Curl Defining Cream", 80);
    	prod[2] = new Product("Oscar Blandi Hear Protectant Spray", 100);
    	prod[3] = new Product("Living Proof Dry Shampoo", 55);
    	prod[4] = new Product("OUAI Repair Shampoo", 70);
    	
    	s[0] = new HairCare("HAIR COLOURING", 30.00);
    	s[1] = new HairCare("CUT & STYLE", 25.00);
    	s[2] = new HairCare("SHAMPOO & STYLE", 20.00);
    	s[3] = new HairCare("KIDDIE CUT", 15.00);
    	s[4] = new SkinCare("DEAD CELLS REMOVAL TREATMENT");
    	s[5] = new SkinCare("CELL REJUVENATING TREATMENT");
    	s[6] = new SkinCare("PIMPLE AND SCAR TREATMENT");
    	
        logo();
       	
        do {
        	// Display menu for customer
        	displayMenu();
        	
        	System.out.print("Select your option > ");
        	choice = in.nextInt();
        	
        	System.out.println();
        	
        	switch(choice) {
        		case 1: displayServiceMenu(); purchaseLimit = 7; break;
        		case 2: 
        			displayProductMenu(prod); 
        			for (int i = 0; i < prod.length; i++) {
        				if (prod[i] == null) {
        					purchaseLimit = i;
        					break;
        				}
        			}
        			break;
        		case 3: validManager = login(p); break;
        		case 4: System.exit(1);
        		default: System.out.println("Error");
        	}
        	
        	System.out.println();
        	
        	if (choice == 1 || choice == 2) {
	        		
	        	// Display the number of customer
	        	System.out.println("Customer No. " + custNo++);
	        	System.out.println("--------------");
	        	
	        	tReceived[custNo - 2] = new Transaction();
	        	
	        	do {
		        	// Read the product selected by customer
		        	System.out.print("Select your option > ");
		        	choice2 = in.nextInt();
		        		
		        	// To confirm the customer wants to buy or not
					if (choice2 > 0 && choice2 <= purchaseLimit) {
						// Read the quantity
						System.out.print("Enter quantity > ");
						qtyIn = in.nextInt();
						
						System.out.println();
						
						if (choice == 1)
							calculated = calTotalPayment(choice2, qtyIn, s);
						else if (choice == 2)
							calculated = calTotalPayment(choice2, qtyIn, prod);
						
						trans.addTotalPayment(calculated.getTotalPayment());
						trans.addTotalCharges(calculated.getTotalCharges());
					}
					
	       		} while(choice2 > 0 && choice2 <= purchaseLimit);
	        
				// Calculate SST Charges
		       	trans.setSSTCharges(calSSTCharges(trans.getTotalCharges()));
		       	
		        // Member card discount
		        System.out.print("\nDo you has a member card (Y = Yes) > ");
		        isMem = in.next().charAt(0);
		        in.nextLine();
		        isMem = Character.toUpperCase(isMem);
		        
		        if (isMem == 'Y') {
			        do { 
				        System.out.print("\nEnter Member's ID (N = Cancel) > ");
				        memberID = in.nextLine();
				        
				        if (memberID.equals("N") || memberID.equals("n"))
				        	break;
				        else if (!validateMemberID(p, memberID))
				        	System.out.println("Invalid member ID! Please enter again..");
				        else
				        	System.out.println("You are a MEMBER (5% Discount Given)");
				        	
				       	System.out.println();
			        } while (!validateMemberID(p, memberID));
		        }
		        
		        // Discount 5%
		        if (validateMemberID(p, memberID) == true) {
		        	trans.setDiscount(calDiscount(trans.getTotalCharges()));
		        }
		        
		        // Payment methods (Cash or Credit Card)
		        System.out.print("Pay with card (Y = Yes) > ");
		        paymentMethod = in.next().charAt(0);
		       	in.nextLine();
		        paymentMethod = Character.toUpperCase(paymentMethod);
		        
				// Calculate total discount given to customer (Daily Sales Report)
				r.addTotalDiscount(trans.getDiscount());
		        
		       	// Calculate amount for customer to pay
		       	trans.setTotalAmtPay(calTotalPayAmt(trans));
		       	
		       	if (trans.getTotalAmtPay() == 0) {
		       		trans.deductTransNo();
		       	}
		       	
		       	// Display total Charges for customer
		       	displayReceipt(trans);
		       	
		       	do {
		       		// Input Amount paid by customer
		       		amtPay = in.nextDouble();
		       		
		       		// Calculate changes for customer
			       	trans.setChanges(calChanges(trans.getTotalAmtPay(), amtPay));
			       	
		       		if (trans.getChanges() < 0) {
		       			System.out.println("\nInsufficient money! Please request more from customer..");
		       			System.out.printf("%26s %10s %4s", "Ammount Paid", "= RM", "");
		       		}
		       	} while (trans.getChanges() < 0);
		       	
		       	// Calculate total SST charges
		       	r.addTotalSSTCharges(trans.getSSTCharges());
		       	
		       	if (paymentMethod == 'Y')
		       		r.addTotalBank(trans.getTotalCharges());
		       	else
		       		r.addTotalRM(trans.getTotalCharges());
		       		
		       	// Display the change due
		       	System.out.printf("%7s %15s %9.2f", "Changes", "= RM", trans.getChanges());
		       	
		       	System.out.println("\nTHANK YOU, HAVE A NICE DAY!!!\n");
		       	
		       	// Ask user wants to continue with the next customer or not
				System.out.print("Next Customer (Y = yes) > ");
				nextCust = in.next().charAt(0);
				in.nextLine();
				nextCust = Character.toUpperCase(nextCust);
				
				System.out.println();
				
		       	// Store the data into the array for daily sales report
		       	tReceived[custNo - 2] = trans;
		       	
		       	// Reset values to 0
		       	memberID = "";
		       	trans.setTotalPayment(0);
		       	trans.setTotalCharges(0);
		       	trans.setSSTCharges(0);
		       	trans.setTotalAmtPay(0);
		       	amtPay = 0;
		       	trans.setChanges(0);
		       	trans.setDiscount(0);
		       	
        	}
        	else if (choice == 3) {
        		if (validManager) {
		        	displayMngAdmtMenu();
		        	
		        	do {
			        	System.out.print("Select your choice > ");
			        	choice3 = in.nextInt();
			        	System.out.println();
			        			
			        	switch(choice3) {
			        		case 1:
			        			searchService(s);
			        			break;
			        		case 2:
			        			prod = addProduct(prod);
			        			break;
			        		case 3:
			        			prod = modifyProduct(prod);
			        			break;
			        		case 4:
			        			searchProduct(prod);
			        			break;
			        		case 5:
			        			displayStaff(p);
			        			break;
			        		case 6:
			        			displayManager(p);
			        			break;
			        		default:
			        			System.out.println("Error");
			        	}
			        	
			        	System.out.print("\nProceed (Y = Yes) > ");
			        	proceed = in.next().charAt(0);
			        	in.nextLine();
			        	proceed = Character.toUpperCase(proceed);
			        	
			        	System.out.println();
		        	} while (proceed == 'Y'); 
	        	}
        	}
        } while (nextCust == 'Y');
        
        // Report
        displayReport(r, tReceived, s, prod);
    }
  	
  	// Menu
    public static void displayMenu() {
    	System.out.println("Salon Point-Of-Sales System");
    	System.out.println("============================");
    	System.out.println("1. Service");
    	System.out.println("2. Product");
    	System.out.println("3. Manager Administration");
    	System.out.println("4. Exit");
    	System.out.println("============================");
    }
    public static void displayServiceMenu() {
    	System.out.println("Hair Care Services");
    	System.out.println("=====================");
    	System.out.println("1. HAIR COLOURING");
    	System.out.println("2. CUT & STYLE");
    	System.out.println("3. SHAMPOO & STYLE");
    	System.out.println("4. KIDDIE CUT");
    	System.out.println("=====================");
    	
    	System.out.println("Skin Care Services");
    	System.out.println("===============================");
    	System.out.println("5. DEAD CELLS REMOVAL TREATMENT");
    	System.out.println("6. CELL RESTORING TREATMENT");
    	System.out.println("7. PIMPLE AND SCAR TREATMENT");
    	System.out.println("===============================");
    	
    	System.out.println("Others. End transaction");
    }
    public static void displayProductMenu(Product[] p) {
    	System.out.println("Products");
    	System.out.println("=====================================");
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] != null) 
   				System.out.printf("%d. %s\n", i + 1, p[i].getDesc());
    	}
    	System.out.println("=====================================");
    	
    	System.out.println("Others. End transaction");
    }
    
    // Login
    public static boolean login(Person[] p) {
    	Scanner in = new Scanner(System.in);
    	
    	boolean boo = false;
  		String pass, managerID;
  		
  		do{
  			System.out.print("Enter your Manager ID (-1 to Cancel) > ");
  			managerID = in.nextLine();
			
			System.out.println();
			
			if (managerID.equals(String.format("%d", -1))) {
  				return boo;
  			}
  			
  			System.out.print("Enter your Manager Password > ");
  			pass = in.nextLine();
  			
  			System.out.println();
  			
  			boo = validateManagerID(p, managerID, pass);
  			
  			if (boo == true) {
  				System.out.println("Login Successfully");
  			}
  			else {
  				System.out.println("Invalid ID or Password Please Login Again !!!");
  			}
  		}while(!boo);
  		
  		return boo;
    }
    
    // Calculation
    public static Transaction calTotalPayment(int choice, int qty1, Service[] s) {
    	
    	double totalPayment = 0, totalCharges = 0, price;
    	
    	switch(choice) {
        	case 1: price = s[0].getPrice(); break;
        	case 2: price = s[1].getPrice(); break;
        	case 3: price = s[2].getPrice(); break;
        	case 4: price = s[3].getPrice(); break;
        	case 5: price = s[4].getPrice(); break;
        	case 6: price = s[5].getPrice(); break;
        	case 7: price = s[6].getPrice(); break;
        	default: price = 0; break;
       	}
       	
       	// Calculate total price
        	totalPayment += price * qty1;
        		
        // Apply values for daily sales report
        	totalCharges += totalPayment;
        		
       	// Calculate total quantity, total sales for each services
        switch(choice) {
        	case 1: qty[0] += qty1; salesAmt[0] += totalPayment; break;
        	case 2: qty[1] += qty1; salesAmt[1] += totalPayment; break;
        	case 3: qty[2] += qty1; salesAmt[2] += totalPayment; break;
        	case 4: qty[3] += qty1; salesAmt[3] += totalPayment; break;
        	case 5: qty[4] += qty1; salesAmt[4] += totalPayment; break;
        	case 6: qty[5] += qty1; salesAmt[5] += totalPayment; break;
        	case 7: qty[6] += qty1; salesAmt[6] += totalPayment; break;
        	default:  break;
        }
        
        Transaction trans = new Transaction(totalPayment, totalCharges);
        return trans;
    }
    public static Transaction calTotalPayment(int choice, int qty1, Product[] prod) {
    	
    	double totalPayment = 0, totalCharges = 0, price = 0;
       	
       	for (int i = 0; i < prod.length; i++) {
       		if (choice - 1 == i) {
       			price = prod[i].getPrice();
       			break;
       		}
       	}
       	// Calculate total price
        	totalPayment += price * qty1;
        		
        // Apply values for daily sales report
        	totalCharges += totalPayment;
        
       	// Calculate total quantity, total sales for each services
        for (int i = 0; i < prod.length; i++) {
        	if (choice - 1 == i) {
        		qty[i + 7] += qty1;
        		salesAmt[i + 7] += totalPayment;
        		break;
        	}
        }
        
        Transaction trans = new Transaction(totalPayment, totalCharges);
        return trans;
    }
    
    public static double calSSTCharges(double totalCharges) {
    	return totalCharges * 0.1;
    } 
    public static double calDiscount(double totalCharges) {
    	return totalCharges * 0.05;
    }
    public static double calTotalPayAmt(Transaction p) {
    	return p.getTotalCharges() + p.getSSTCharges() - p.getDiscount();
    }
    public static double calChanges(double totalCharges, double amtPaid) {
    	double changes;
    	changes = amtPaid - totalCharges;
    	return changes;
    }
    
    // Validation
    public static boolean validateMemberID(Person[] p, String memberID) {
    	boolean valid = false;
    	String memID;
    	for (int i = 0; i < p.length; i++) {
    		if (p[i] != null && p[i] instanceof Member) {
    			Member member = (Member) p[i];
    			memID = member.getMemberID();
    			
    			if (memberID.equals(memID)) {
    				valid = true;
    				break;
    			}
    		}
    	}
    	
    	return valid;
    }
    public static boolean validateManagerID(Person[] p, String managerID, String managerPass){
    	boolean valid = false;
    	String cmanagerID, cmanagerPass;
    	for(int i = 0; i < p.length; i++) {
    		if(p[i] != null && p[i] instanceof Manager) {
    			Manager manager = (Manager) p[i];
    			cmanagerID = manager.getManagerID();
    			cmanagerPass = manager.getPassword();
    			if(managerID.equals(cmanagerID) && managerPass.equals(cmanagerPass))
    				valid = true;
    		}
    	}
    	return valid;
    }
    public static boolean ansValidation (char data) {
    	boolean b = true;
    	
    	if (data != 'Y' && data != 'N')
    		System.out.println("Invalid Answer");
    	else
    		b = false;
    	
    	return b;
    }
    
    // Display receipt
    public static void displayReceipt(Transaction t) {
    	System.out.printf("%27s %9s %9.2f\n", "Charges", "= RM", t.getTotalCharges());
    	System.out.printf("%25s %11s %9.2f\n", "Add 10% SST", "= RM", t.getSSTCharges());
    	System.out.printf("%28s %8s %9.2f\n", "Discount Given", "= RM", t.getDiscount());
    	System.out.printf("%26s %10s %9.2f\n", "TOTAL TO PAY", "= RM", t.getTotalAmtPay());
    	
    	System.out.printf("%26s %10s %4s", "Ammount Paid", "= RM", "");
    }
    
    // Daily sales report
  	public static void displayReport(Report r, Transaction[] tReceived, Service[] s, Product[] prod) {
  		
  		int totalQty = 0, totalQty2 = 0, totalTrans = 0;
  		double totalSales = 0, totalSales2 = 0;
  		
  		for (int i = 0; i < tReceived.length; i++) {
  			if (tReceived[i] == null) {
  				totalTrans = tReceived[i - 1].getTransNo() - 3;
  			}
  		}
  		
  		System.out.println("DAILY SALES REPORT");
  		System.out.println("==================");
  		System.out.println("Date = " + r + "\n");
  		System.out.printf("%-37s   %20s   %12s   \n", "Service Description", "Quantity Sold", "Sales Amount");
  		System.out.printf("%-37s   %20s   %12s   \n", "=====================================", "=============", "============");
  		
  		for (int i = 0; i < s.length; i++) {
  			if (s[i] != null) {
  				System.out.printf("%-37s   %15d   %13.2f\n", s[i].getDesc(), qty[i], salesAmt[i]);
  				totalQty += qty[i];
  				totalSales += salesAmt[i];
  			}
  		}
  		System.out.printf("%37s   %17s   %13s\n","======", "=====", "========");
  		System.out.printf("%37s   %15d   %13.2f\n\n", "TOTALS", totalQty, totalSales);
  		
  		System.out.printf("%-37s   %20s   %12s   \n", "Product Description", "Quantity Sold", "Sales Amount");
  		System.out.printf("%-37s   %20s   %12s   \n", "=====================================", "=============", "============");
  		
  		for (int i = 7; i < (prod.length + 7); i++) {
  			if (prod[i - 7] != null) {
	  			System.out.printf("%-37s   %15d   %13.2f\n", prod[i - 7].getDesc(), qty[i], salesAmt[i]);
	  			totalQty2 += qty[i];
	  			totalSales2 += salesAmt[i];
  			}
  		}
		
  		System.out.printf("%37s   %17s   %13s\n","======", "=====", "========");
  		System.out.printf("%37s   %15d   %13.2f\n\n", "TOTALS", totalQty2, totalSales2);
  		
  		System.out.printf("%23s = %11d\n", "TOTAL Transaction", totalTrans);
  		// Display Total of SST Charges
		System.out.printf("%23s = %11.2f\n", "TOTAL SST charges", r.getTotalSSTCharges());
		// Display Total of discount allowed to customer
		System.out.printf("%23s = %11.2f\n", "TOTAL Discount allowed", r.getTotalDiscount());
		// Display Total of cash in the cash drawer
		System.out.printf("%23s = %11.2f\n", "TOTAL RM collected", r.getTotalRM());
		// Display Total of money received from customer pay with credit card
		System.out.printf("%23s = %11.2f\n", "TOTAL Bank Transactions", r.getTotalBank());
		// Display Total income for the end of the day
		System.out.printf("%23s = %11.2f\n\n", "Income", r.getTotalRM() + r.getTotalBank());
		// Display Balance in the shop
		System.out.printf("%26s = %8.2f\n\n", "Balance(Discount deducted)", r.getTotalRM() + r.getTotalBank() - r.getTotalDiscount());
  	}
    
    // Logo
    public static void logo() {
    	System.out.print(",---.  .-. .-.   .---. .-. .-.,-..-. .-.  ,--,       .---.  .--.  ,-.    .---.  .-. .-. \n| .-.\\ | | | |  ( .-._)| | | ||(||  \\| |.' .'       ( .-._)/ /\\ \\ | |   / .-. ) |  \\| | \n| `-'/ | | | | (_) \\   | `-' |(_)|   | ||  |  __   (_) \\  / /__\\ \\| |   | | |(_)|   | | \n|   (  | | | | _  \\ \\  | .-. || || |\\  |\\  \\ ( _)  _  \\ \\ |  __  || |   | | | | | |\\  | \n| |\\ \\ | `-')|( `-'  ) | | |)|| || | |)| \\  `-) ) ( `-'  )| |  |)|| `--.\\ `-' / | | |)| \n|_| \\)\\`---(_) `----'  /(  (_)`-'/(  (_) )\\____/   `----' |_|  (_)|( __.')---'  /(  (_) \n    (__)              (__)      (__)    (__)                      (_)   (_)    (__)     \n\n");
    }
    
    
    // ** Manager Administration ** //
    
    // Menu
    public static void displayMngAdmtMenu() {
    	System.out.println("\nManager Administration Menu");
        System.out.println("---------------------------");
       	System.out.println("1. Search Service");
       	System.out.println("2. Add Product");
       	System.out.println("3. Modify Product");
       	System.out.println("4. Search Product");
       	System.out.println("5. List Staffs' Details");
       	System.out.println("6. List Managers' Details");
       	System.out.println("---------------------------");
    }
   	
   	// Displaying person details
   	public static void displayStaff(Person[] p) {
   		
   		System.out.printf("%-16s%-21s%-20s%s\n", "Staff ID", "First Name", "Last Name", "Hired Date");
   		System.out.printf("%-16s%-21s%-20s%s\n", "========", "==========", "=========", "==========");
   		for(int i = 0; i < p.length; i++) {
    		if(p[i] != null && p[i] instanceof Staff) {
    			Staff staff = (Staff) p[i];
    			System.out.println(staff);
    		}
    	}
   	}
   	public static void displayManager(Person[] p) {
   		
   		System.out.printf("%-16s%-21s%-20s\n", "Manager ID", "First Name", "Last Name");
   		System.out.printf("%-16s%-21s%-20s\n", "==========", "==========", "=========");
   		for(int i = 0; i < p.length; i++) {
    		if(p[i] != null && p[i] instanceof Manager) {
    			Manager manager = (Manager) p[i];
    			System.out.println(manager);
    		}
    	}
   	}
   	
   	// For Service
   	public static void searchService(Service[] s) {
   		Scanner in = new Scanner(System.in);
   		
   		char type, ans;
   		String treatmentID, sTreatmentID;
   		
   		do {
	   		System.out.print("Type of service (H = HairCare, S = SkinCare) > ");
	   		type = in.next().charAt(0);
	   		in.nextLine();
	   		type = Character.toUpperCase(type);
	   		
	   		System.out.print("\nTreatment ID > ");
	   		treatmentID = in.nextLine();
	   		
	   		System.out.println();
	   		
	   		for (int i = 0; i < s.length; i++) {
	   			
	   			if (s[i] != null) {
	   				if (type == 'H' && s[i] instanceof HairCare) {
	   					HairCare hc = (HairCare) s[i];
	   					sTreatmentID = hc.getTreatmentID();
	   				
	   					if (treatmentID.equals(sTreatmentID)) {
	   						System.out.printf("%-16s%-30s%-8s\n", "Treatment ID", "Description", "Price");
	   						System.out.printf("%-16s%-30s%-8s\n", "============", "===========", "=====");
	   						System.out.println(hc.toString() + "\n");
	   					}
	   				}
	   				else if (type == 'S' && s[i] instanceof SkinCare) {
	   					SkinCare sc = (SkinCare) s[i];
	   					sTreatmentID = sc.getTreatmentID();
	   				
	   					if (treatmentID.equals(sTreatmentID)) {
	   						System.out.printf("%-16s%-30s%-8s\n", "Treatment ID", "Description", "Price");
	   						System.out.printf("%-16s%-30s%-8s\n", "============", "===========", "=====");
	   						System.out.println(sc.toString() + "\n");
	   					}
	   				}
	   			}
	   		}
	   		System.out.print("Continue to search (Y = Yes) > ");
	   		ans = in.next().charAt(0);
	   		in.nextLine();
	   		ans = Character.toUpperCase(ans);
	   		
	   		System.out.println();
	   		
   		} while (ans == 'Y');
   	}
   	
   	// For Product
   	public static Product[] addProduct(Product[] p) {
   		Scanner in = new Scanner(System.in);
   		
   		char ans;
   		String desc;
   		double price;
   		Product prod;
   		
   		do {
   			
	   		for (int i = 0; i < p.length; i++) {
	   			if (p[i] == null) {
	   				System.out.print("Product's Description > ");
	   				desc = in.nextLine();
	   				
	   				System.out.print("Product's Price > ");
	   				price = in.nextDouble();
	   				
	   				p[i] = new Product(desc, price);
	   				
	   				break;
	   			}
	   		}
	   		
	   		System.out.print("\nContinue to add Product (Y = Yes) > ");
	   		ans = in.next().charAt(0);
	   		in.nextLine();
	   		ans = Character.toUpperCase(ans);
	   		
	   		System.out.println();
	   		
   		} while (ans == 'Y');
   		
   		return p;
   	}
   	public static Product[] modifyProduct(Product[] p) {
   		Scanner in = new Scanner(System.in);
   		
   		char ans, ans2;
   		String prodID, sProdID, desc;
   		int option, index = 0;
   		double price;
   		
   		do {
	   		System.out.print("Product ID > ");
	   		prodID = in.nextLine();
	   		
	   		do {
	   			
		   		for (int i = 0; i < p.length; i++) {
		   			if (p[i] != null) {
		   				Product prod =  p[i];
		   				sProdID = prod.getProdID();
		   				
		   				if (prodID.equals(sProdID)) {
		   					System.out.printf("\n%-16s%-45s%-8s\n", "Treatment ID", "Description", "Price");
			   				System.out.printf("%-16s%-45s%-8s\n", "============", "===========", "=====");
		   					System.out.println(prod.toString());
		   					
		   					System.out.println("\nModify?");
		   					System.out.println("================");
		   					System.out.println("1. Description");
					   		System.out.println("2. Price");
		   					System.out.println("================");
		   		
		   					System.out.print("Enter your choice > ");
		   					option = in.nextInt();
		   					in.nextLine();
		   			
							switch(option) {
		   						case 1:
		   							System.out.print("Product's New Description > ");
		   							p[i].setDesc(in.nextLine());
		   							break;
		   						case 2:
		   							System.out.print("Product's New Price > ");
		   							p[i].setPrice(in.nextDouble());
		   							break;
		   						default:
		   							System.out.println("\nERROR");
		   					}
		   				}
		   			}
		   		}
		   		
		   		
	   			
	   			System.out.print("\nContinue to modify on the same product (Y = Yes) > ");
	   			ans = in.next().charAt(0);
	   			in.nextLine();
	   			ans = Character.toUpperCase(ans);
   			} while (ans == 'Y');
   			
   			System.out.print("\nContinue to modify (Y = Yes) > ");
   			ans2 = in.next().charAt(0);
   			in.nextLine();
   			ans2 = Character.toUpperCase(ans2);
   			
   			System.out.println();
   			
   		} while (ans2 == 'Y');
   		
   		return p;
   	}
   	public static void searchProduct(Product[] p) {
   		Scanner in = new Scanner(System.in);
   		
   		char ans;
   		String prodID, sProdID;
   		
   		do {
	   		System.out.print("Product ID > ");
	   		prodID = in.nextLine();
	   		
	   		for (int i = 0; i < p.length; i++) {
	   			if (p[i] != null) {
	   				Product prod =  p[i];
	   				sProdID = prod.getProdID();
	   				
	   				if (prodID.equals(sProdID)) {
	   					System.out.printf("\n%-16s%-45s%-8s\n", "Treatment ID", "Description", "Price");
		   				System.out.printf("%-16s%-45s%-8s\n", "============", "===========", "=====");
	   					System.out.println(prod.toString());
	   				}
	   			}
	   		}
   		
   			System.out.print("\nContinue to search (Y = Yes) > ");
	   		ans = in.next().charAt(0);
	   		in.nextLine();
	   		ans = Character.toUpperCase(ans);
	   		
	   		System.out.println();
	   		
   		} while (ans == 'Y');
   	}
}