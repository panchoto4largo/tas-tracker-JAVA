package bodyProgram;

import java.util.Scanner;

import createJson.JsonEmpty;
import createTask.*;



public class Body {
	private Scanner sc = new Scanner(System.in);

	Body() {
		
	}
	
	public void bodyBool() {
		JsonEmpty jsonEmptiness = new JsonEmpty();
		boolean jsonBoolean = jsonEmptiness.JsonIsEmpty();
		
		if( jsonBoolean == true) {
			new CreateJsonTask();
		}else {
			principal();
		}
	}
	
	private boolean principal() {
	    boolean x = true;
	    while (x) {
	        System.out.println("What do you want to do");
	        System.out.println(" 1. Create a task \n2. Modify a task \n3. Delete a task "
	            + "\n4. Mark in progress \n5. Mark complete"
	            + "\n6. List tasks"
	            + "\n7. Close the program");

	        int option = 0;
	        try {
	            option = Integer.parseInt(sc.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("Por favor, ingresa un número válido.");
	            continue;
	        }

	        switch (option) {
	            case 1:
	                new CreateJsonTask();
	                break;
	            case 2:
	                new ModifyJsonTask();
	                break;
	            case 3:
	                new DeleteJsonTask();
	                break;
	            case 4:
	                new ChangeStatement(4);
	                break;
	            case 5:
	            	new ChangeStatement(5);
	                break;
	            case 6:
	                System.out.println("1. List all tasks \n 2. List done tasks \n 3. List in-progress tasks \n 4. List undone tasks");
	                int list = Integer.parseInt(sc.nextLine());
	                ShowList show = new ShowList(list);
	                break;
	            case 7:
	                x = false;
	                break;
	            default:
	                System.out.println("That is not an option, try again");
	                break;
	        }
	    }
	    return x;
	}
}