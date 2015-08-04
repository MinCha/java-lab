package refactoring.day1.practice01.longMethod.case2.extractMethod;

import java.util.*;

public class PrintInfo {

    String name;
    List<Order> orders = new ArrayList<Order>();
    
    public PrintInfo (){
        this.name = "Guest";
        orders.add( new Order(3) ); // 3개 주문
        orders.add( new Order(4) ); // 4개 주문
        orders.add( new Order(5) ); // 5개 주문
    }

    public static void main( String [] args){
        PrintInfo info = new PrintInfo();
        info.printOwing();
    }
    
    void printOwing(){
        Iterator<Order> order = orders.iterator();  // Enumeration : java3_02.ppt 6장 10절
        double outstanding = 0.0;
        
        System.out.println("*************************");
        System.out.println("***** Customer Owes *****");
        System.out.println("*************************");
        
        //outstanding 계산
        while (order.hasNext()) {
            Order each = (Order) order.next();
            outstanding += each.getAmount();
        }
        
        System.out.println("name: " + this.name);
        System.out.println("amount: " + outstanding);
    }
    
    public class Order {
        int amount;
        
        public Order(int amount){
            this.amount = amount;
        }
        
        public int getAmount(){
            return this.amount;
        }
    }
}

