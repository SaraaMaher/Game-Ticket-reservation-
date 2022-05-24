/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameticket;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 *
 * @author Sara-Labtop
 */
/**main call*/
public class GameTicket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //initialize fans and games
         game[] games=new game[5];
         setgames(games);
         ArrayList<fan> fans=new ArrayList<fan>();
         fan f0=new fan();
         fan f1=new fan();
         f0.setId(1);
         f1.setId(2);
         f0.setName("Ali");
         f1.setName("hania");
         f0.setBetwinnings(100);
         f1.setBetwinnings(200);
         fans.add(f0);
         fans.add(f1);
         //user input log in or sign up
        Scanner input=new Scanner(System.in);
        fan currentfan = null;
    while(true){
        System.out.println("1)log in  2)sing up");
       int signchoice=input.nextInt();
       try{
        if(signchoice==1)
        {
            boolean exists=false;
            System.out.println("enter id");
            int id=input.nextInt();
            for(int i=0;i<fans.size();i++)
                if(fans.get(i).getId()==id)
                {
                    exists=true;
                    currentfan=fans.get(i);
                    break;
                }
            if(!exists)
                System.out.println("id doesnt exist");
            else break;
        }
        else if(signchoice==2)
        {
             currentfan=new fan();
             boolean exists=false;
            System.out.println("enter id");
            int id=input.nextInt();
             System.out.println("enter name");
            String name=input.next();
             for(int i=0;i<fans.size();i++)
                if(fans.get(i).getId()==id)
                {
                    exists=true;
                    break;
                }
            if(exists)
                System.out.println("id already exists");
            else 
            {
                currentfan.setId(id);
                currentfan.setName(name);
                fans.add(currentfan);
                break;
            }
        }
        else 
        {
           throw new inputexception("Enter 1 or 2 ");
        }
    }catch(inputexception i)
    {
        System.out.println(i.getMsg());
    }
        }
        //show games
        System.out.println("games :");
        for(int i=0;i<games.length;i++)
        System.out.println(games[i].getCode()+"  "+games[i].gettype()+"  "+games[i].getTeamA()+" "+"VS "+games[i].getTeamB()+" "+"location"+games[i].getLocation());
        System.out.println("welcome");
  
        
//functionalities
   while(true)
   {
       System.out.println("choose what you wish to do 1)reserve ticket 2)cancel ticket 3)make a bet 4)check available seats in a game 5)display all fans  6)quit");
       int choice=input.nextInt();
    try{
      if(choice==1)
      {
        System.out.println("enter game code");
        int gamecode=input.nextInt();
        System.out.println("choose seat category");
        int category=input.nextInt();
           for(int i=0;i<games.length;i++)
           {
               if(games[i].getCode()==gamecode)
               {
                  games[i].checkavailableseat(category);
                  System.out.println("choose seat");
                  
                  int seatnumber=input.nextInt();
                  seat s=games[i].getseat(seatnumber, category);
                  ticket t=currentfan.reserveticket(games[i], s, category);
                  System.out.println("ticket successfully reserved");
                  System.out.println("code"+" "+t.getcode()+" "+"price"+" "+t.getPrice()+" "+"seat number"+" "+t.getseatnumber()+" "+"teams"+" "+t.g.getTeamA()+" "+t.g.getTeamB()+" "+"location"+" "+t.g.getLocation());                
                   if(category!=1 && currentfan.getBetwinnings()>=50)
                   {
                      System.out.println("do you wish to upgrade seat category y/n?");
                      char ch=input.next().charAt(0);
                      if(ch=='y')
                      {
                         ticket t_upgraded=currentfan.upgradecategory(t.getcode());
                          System.out.println("code"+" "+t_upgraded.getcode()+" "+"price"+" "+t_upgraded.getPrice()+" "+"seat number"+" "+t_upgraded.getseatnumber()+" "+"teams"+" "+t_upgraded.g.getTeamA()+" "+t_upgraded.g.getTeamB()+" "+"location"+" "+t_upgraded.g.getLocation());
                      }
                   }
                }
            }
     
        }
        else if(choice==2)
        {
          System.out.println("enter ticket code");
          int ticketcode=input.nextInt();
          currentfan.cancelreservation(ticketcode);
        }
       else if(choice==3)
       {
         System.out.println("enter game code");
         int gamecode=input.nextInt();
         System.out.println("enter your bet "+games[gamecode].getTeamA()+" or "+games[gamecode].getTeamB());
         String team=input.next();
          if(games[gamecode].getwinner().equals(team))
          {
            currentfan.setBetwinnings(100);
            System.out.println("you won 100$");
            System.out.println("your total winnings are: "+currentfan.getBetwinnings());
          }
          else 
         {
            currentfan.setBetwinnings(-50);
            System.out.println("Bad luck you lost 50$");
            System.out.println("your total winnings are: "+currentfan.getBetwinnings());
         }
       }
       else if(choice==4)
       {
        System.out.println("enter game code");
        int gamecode=input.nextInt();
        System.out.println("choose seat category");
        int category=input.nextInt();
        try{
          for(int i=0;i<games.length;i++)
          {
              if(games[i].getCode()==gamecode)
              games[i].checkavailableseat(category);
          }
       }catch(ArrayIndexOutOfBoundsException e)
       {
           System.out.println("ArrayIndexOutOfBoundsException");
       }
       }
       else if(choice==5)
       {
       System.out.println("ID \t Name \t tickets");
        for(int i=0;i<fans.size();i++)
        {
           System.out.print(fans.get(i).getId()+"\t"+fans.get(i).getName());
           for(int j=0;j<fans.get(i).tickets.size();i++)
           {
              System.out.print(fans.get(i).tickets.get(j).getcode()+" ");
           }
            System.out.println(" ");
         }
       }
      else if(choice==6)
       break; 
      else 
       throw new inputexception("please enter a correct number");
   }catch(inputexception i)
   {
       System.out.println(i.getMsg());
   }
   }
}  
    



     //static method
    /**initializes games in game array*/
   public static void setgames(game[] games){
       game g1=new game();
       game g2=new game();
       game g3=new game();
       game g4=new game();
       game g5=new game();
       g1.setlocation();
       g1.settype("football");
       g1.setteams("Ahly","zamalek");
       g1.setdate(3,6,2000);
       g1.setwinner("Ahly");
       games[0]=g1;
       g2.setlocation();
       g2.settype("football");
       g2.setteams("Ahly","ismailia");
       g2.setdate(5,6,2000);
       g2.setwinner("Ahly");
       games[1]=g2;
       g3.setlocation();
       g3.settype("football");
       g3.setteams("zamalek", "ismailia");
       g3.setdate(6,6,2000);
       g3.setwinner("ismailia");
       games[2]=g3;
       g4.setlocation("England");
       g4.settype("basketball");
       g4.setteams("liverbool", "manchester");
       g4.setdate(4, 7, 2000);
       g4.setwinner("manchester");
       games[3]=g4;
       g5.setlocation("England");
       g5.settype("baseball");
       g5.setteams("liverbool", "barshelona");
       g5.setdate(1, 7, 2000);
       g5.setwinner("barshelona");
       games[4]=g5;
   }
}
    
/** game interface for seat functions*/
 interface gameinterface{
     public void reserveseat(int num,int category);
     public void checkavailableseat(int category);   
 }
/** class game represents different games*/
public class  game implements gameinterface{
  //static datamember
  static int count=0;
  private final int code;  
  private String location;
  private date d;
  private String teamA;
  private String teamB;
  private String winner;
  private int numseatsfirst;
  private int numseatssecond;
  private int numseatsthird;
  private boolean national;
  private seat seats[];
  private String type;
        
  public game(){
      count++;
      code=count;
      seats=new seat[30];
      setseats(seats);
      numseatsfirst=10;
      numseatssecond=10;
      numseatsthird=10;
  }
  
  //polymorphism
  /** reserves seat in a game*/
  public void reserveseat(int num,int category){
       
           switch(category){
               case 1: 
                   for(int i=0;i<10;i++){
                if(seats[i] instanceof firstcategoryseat){
                  if(seats[i].getNum()==num&&seats[i].isreservable()){
                     seats[i].reserve();
                     numseatsfirst--;
                   }
                }}
                break;
               case 2:
               for(int i=10;i<20;i++){
                   if(seats[i] instanceof secondcategoryseat){
                  if(seats[i].getNum()==num&&seats[i].isreservable()){
                     seats[i].reserve();
                     numseatssecond--;
                   }
                }}
                   break;
               case 3:
                   for(int i=20;i<30;i++){
                     if(seats[i] instanceof thirdcategoryseat){
                  if(seats[i].getNum()==num&&seats[i].isreservable()){
                     seats[i].reserve();
                     numseatsthird--;
                   }
                }}
                   break;
               default: System.out.println("wrong entry");
            }
        }
  /**prints available seats in a game*/
  public void checkavailableseat(int category){
     System.out.println("seatnum \t seatprice");
      
      switch(category){
          case 1:
              for(int i=0;i<10;i++){
              if(seats[i] instanceof firstcategoryseat){
                  if(seats[i].isreservable())
                  {
                      System.out.println(seats[i].getNum()+"\t"+seats[i].getPrice());
                  }
              }}
              break;
          case 2:
                  for(int i=10;i<20;i++)
                  {
                  if(seats[i] instanceof secondcategoryseat){
                  if(seats[i].isreservable())
                  {
                      System.out.println(seats[i].getNum()+"\t"+seats[i].getPrice());
                  }
              }}
              break;
              
          case 3:
              for(int i=20;i<30;i++){
                  if(seats[i] instanceof thirdcategoryseat){
                  if(seats[i].isreservable())
                  {
                      System.out.println(seats[i].getNum()+"\t"+seats[i].getPrice());
                  }
              }}
              break;
              default: System.out.println("wrong entry");
      }
  }
  /** upgrades seat to first category*/
  public seat upgrade(){
   for(int i=0;i<numseatsfirst;i++){
     if(seats[i] instanceof firstcategoryseat)
     {
         if(seats[i].isreservable())
         {
             seats[i].reserve();
             seats[i].setPrice(100);
             numseatsfirst--;
             return seats[i];
         }
     }    
    }
   return null;
  }
  
  //encapsulation
  /** sets game location for national games*/
  public  void setlocation(){
      location="Egypt";
      national=true;
  }
  /** sets game locations for international games*/
  public void setlocation(String location){
      this.location=location;
      national=false;
  }
  /** returns game location*/
  public String getLocation() {
        return location;
    }
  /** sets teams playing game*/
  public void setteams(String A,String B){
      teamA=A;
      teamB=B;
 }
    /** returns first team*/
  public String getTeamA() {
        return teamA;
  }
  /** returns second team*/
  public String getTeamB() {
        return teamB;
    }
   /**sets game winner*/
  public void setwinner(String winner){
      this.winner=winner;
  }
  /**returns game winner*/
  public String getwinner(){
      return winner;
  }
    /** sets type of game*/
  public void settype(String type){
      this.type=type;
  }
  /** returns game type*/
  public String gettype(){
      return type;
  }
   /** sets game date*/
  public void setdate(int day,int month,int year){
      d=new date(day,month,year);
  }
   /** returns game date*/
  public date getD() {
        return d;
    }
  /** initializes game seats applying factory pattern*/
  public static void setseats(seat seats[]){
        seatfactory sf=new seatfactory();
        for(int i=0;i<10;i++)
        {
            seats[i]=sf.getseat(1);
        }
        for(int i=10;i<20;i++)
        {
            seats[i]=sf.getseat(2);
        }
        for(int i=20;i<30;i++)
        {
            seats[i]=sf.getseat(3);
        }
    }
   /** return game seats*/
  public seat[] getSeats() {
        return seats;
    }
  /**returns a seat by number and category */
  public seat getseat(int num,int category){
      for(int i=0;i<seats.length;i++)
      {
          if(category==1)
          {
              if(seats[i] instanceof firstcategoryseat)
                  if(seats[i].getNum()==num)
                  return seats[i];
          }
          else if(category==2)
          {
              if(seats[i] instanceof secondcategoryseat)
                  if(seats[i].getNum()==num)
                  return seats[i];
          }
          else if(category==3)
          {
              if(seats[i] instanceof thirdcategoryseat)
                  if(seats[i].getNum()==num)
                  return seats[i];
          } 
      }
       return null;
  }
  /** returns game code*/
  public int getCode() {
        return code;
    }
  /** return number of seats in first category*/
  public int getNumseatsfirst() {
        return numseatsfirst;
    }
  /** return number of seats in second category*/
  public int getNumseatssecond() {
        return numseatssecond;
    }
  /** return number of seats in third category*/
  public int getNumseatsthird() {
        return numseatsthird;
    }
  /** returns true if game is national*/
  public boolean isNational() {
        return national;
    }
}
 

/**class date carries day,month and year of the games*/
public class date{
    int day;
    int month;
    int year;
    public date(int d,int m,int y){
        day=d;
        month=m;
        year=y;
    }
}

/**interface for seat reservation functionalities*/
interface reserveseat{
    public boolean isreservable();
    public void reserve();
    public void cancel();
}
/**interface for seat data members*/
interface seatmembers{
    public void calcprice();
    public void calcnum();
}
/** seat is an abstract class for different seats */
public abstract class seat implements seatmembers,reserveseat{
    private int num;
    private float price;
    private boolean isreserved;
    public seat(){
        isreserved=false;
    }
    //interface implementation
    //final methods
    /** returns true if boolean can be reserved*/
    public final boolean isreservable() {
        return !isreserved;
    }
    /** sets isreserved boolean to true  */
    public final void reserve(){
        isreserved =true;
    }
    /** sets isreserved boolean to false */
    public final void cancel(){
        isreserved=false;
    }

    //encapsulation
    /**set seat number*/
    public void setNum(int num) {
        this.num = num;
    }
    /**returns seat number*/
    public int getNum() {
        return num;
    }
    /**set seats price*/
    public void setPrice(float price) {
        this.price = price;
    }
    /**returns seats price*/
    public float getPrice() {
        return price;
    } 
 }

 /** subclass for seat represents first category seats*/
public class firstcategoryseat extends seat{
    static int firstcount=0;
   public firstcategoryseat(){
       super();
       firstcount++;
   }
   public void calcprice(){
       setPrice(120.5f);
   }
   public void calcnum(){
       setNum(firstcount);
   }
}
/** subclass for seat represents second category seats*/
public class secondcategoryseat extends seat{
   static int secondcount=0;
   public secondcategoryseat(){
       super();
       secondcount++;
   }
   public void calcprice(){
       setPrice(90f);
   }
    public void calcnum(){
       setNum(secondcount);
   }
 }
/** subclass for seat represents third category seats*/
public class thirdcategoryseat extends seat{
    static int thirdcount=0;
    public thirdcategoryseat(){
        super();
        thirdcount++;   
    }
    public void calcprice(){
       setPrice(55.75f);
   }
      public void calcnum(){
       setNum(thirdcount);
   }
 }

/**factory for creating different seat objects*/
public class seatfactory{
     seat s;
     /**takes a category and creates a new object based on it */
    public seat getseat(int category){
        if(category==1)
        {
            s=new firstcategoryseat();
            s.calcnum();
            s.calcprice();
        }
        else if(category==2)
        {
            s= new secondcategoryseat();
            s.calcnum();
            s.calcprice();
        }
        else
        {
            s= new thirdcategoryseat();
            s.calcnum();
            s.calcprice();
        }
        return s;
    }
}



/**class represents ticket*/
public class ticket{
   static int count;
   private final int code;
   private float price;
   private int seatnumber;
   private date lastdate;
    seat s;
    game g;
   public ticket(){
       count++;
       code=count;
   }
   /**method to initialize tickets data members*/
   public void createticket(game g,seat s){
      this.s=s;
      price=s.getPrice();
      seatnumber=s.getNum();
      this.g=g;
      date gamedate=g.getD();
      lastdate=new date(gamedate.day-3,gamedate.month,gamedate.year);
   }
   /**compares current date with last date to cancel reservation*/
   public boolean claculatedate(){
         LocalDate currentdate = LocalDate.now();
         int diff=currentdate.getDayOfMonth()-lastdate.day;
         if(diff>3){
             count--;
             return true;
         } 
         return false;
     }
   
   //encapsulation

   /**sets seat*/
   public void setS(seat s) {
        this.s = s;
        this.seatnumber=s.getNum();
    }
   /**returns ticket code*/
   public int getcode(){
       return code;
   }
   /**sets tickets price*/
   public void setPrice(float price) {
        this.price = price;
    }
   /**returns tickets price*/
   public float getPrice() {
        return price;
    }
   /**returns tickets seat number*/
   public int getseatnumber(){
         return seatnumber;
     }
}

/**class fan represents sports fan*/
public class fan{
  private String name;
  private int id;
  ArrayList<ticket> tickets;
  private int betwinnings;
    public fan(){
       tickets=new ArrayList<ticket>();
       betwinnings=0;
    }     
    
    /**calls game function to check available seats */
    public void checkavailableseats(int category,game g){
          g.checkavailableseat(category);
        }
    /**creates new to ticket*/
    public ticket reserveticket(game g,seat s,int category){
        g.reserveseat(s.getNum(),category);
        ticket t=new ticket();
        t.createticket(g, s);
        tickets.add(t);
        return t;
    }
    /**cancels reservation and deletes ticket*/
    public void cancelreservation(int code){
           boolean canceled=false;
           for(int i=0;i<tickets.size();i++){
               if(tickets.get(i).getcode()==code)
               {
                   if(tickets.get(i).claculatedate())
                   {
                       tickets.get(i).s.cancel();
                       tickets.remove(tickets.get(i));
                       canceled=true;
                   }
               }
           }
           if(canceled)
           System.out.println("ticket successfully removed");
           else 
               System.out.println("cant cancel reservation");
       }
    /**upgrades ticket to first category calls game upgrade function*/
    public ticket upgradecategory(int code){
        game g=null;
        float paid=0;
          for(int i=0;i<tickets.size();i++){
               if(tickets.get(i).getcode()==code)
               {
                   g=tickets.get(i).g;
                   paid=tickets.get(i).getPrice();
                   seat s=g.upgrade();
                   tickets.get(i).setPrice(s.getPrice());
                   tickets.get(i).setS(s);
                   betwinnings-=s.getPrice()-paid;
                   return tickets.get(i);
               }
           }
          return null;
    }
    
   //encapsulation
    /**returns bet winnings*/
    public int getBetwinnings() {
        return betwinnings;
    }
    /**sets bet winnings*/
    public void setBetwinnings(int betwinnings) {
        this.betwinnings += betwinnings;
    }
    /**returns fan name*/
    public String getName() {
        return name;
    }
    /** sets fan name*/
    public void setName(String name) {
        this.name = name;
    }
    /**returns fan id*/
    public int getId() {
        return id;
    }
    /**sets fan id*/
    public void setId(int id) {
        this.id = id;
    }
    /**returns a ticket from tickets array list*/
    public ticket getticket(int code){
        
        for(int i=0;i<tickets.size();i++){
            if(tickets.get(i).getcode()==code)
            {
                return tickets.get(i);
            }
       }
        return null;
   
    } 
}

/**user defined exception for wrong input*/
public class inputexception extends Exception{
    String msg;
    public inputexception(String msg){
        super(msg);
        this.msg=msg;
    }
    /**returns exception message*/
    public String getMsg() {
        return msg;
    }
}
