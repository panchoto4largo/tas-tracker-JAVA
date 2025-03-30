package bodyProgram;

public class ShowList {
    private int list;


    ShowList(int list) {
        this.list = list;
        listToShow(this.list);  
    }
    
    private void listToShow(int list) {
    	System.out.print(list);
    }
}