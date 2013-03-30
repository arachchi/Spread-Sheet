/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package step6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Main {
static Excel project=new Excel();

static CellAddress temp1=new CellAddress();static CellAddress temp4=new CellAddress();
static CellAddress temp2=new CellAddress();static CellAddress temp5=new CellAddress();
static CellAddress temp3=new CellAddress();static CellAddress temp6=new CellAddress();
    public static void main(String[] args) {
         project.createNew();

        project.sp1[0].sheet[0].Acolumn[0]=new Number();project.sp1[0].sheet[1].Acolumn[0]=new Number();
        project.sp1[0].sheet[0].Acolumn[1]=new Number();project.sp1[0].sheet[1].Acolumn[1]=new Number();
        project.sp1[0].sheet[0].Acolumn[2]=new Number();project.sp1[0].sheet[1].Acolumn[2]=new Number();
        project.sp1[0].sheet[0].Acolumn[3]=new Number();project.sp1[0].sheet[1].Acolumn[3]=new Number();
        project.sp1[0].sheet[0].Acolumn[4]=new Number();project.sp1[0].sheet[1].Acolumn[4]=new Number();

        project.sp1[0].sheet[0].Acolumn[0].input(234.758);project.sp1[0].sheet[1].Acolumn[0].input(234.758);
        project.sp1[0].sheet[0].Acolumn[1].input(234.758);project.sp1[0].sheet[1].Acolumn[1].input(234.758);
        project.sp1[0].sheet[0].Acolumn[2].input(234.758);project.sp1[0].sheet[1].Acolumn[2].input(234.758);
        
        temp1.setAddress(4,"A");temp2.setAddress(4,"B");temp3.setAddress(4,"A");temp4.setAddress(4,"A");
                 printTheGrid();
        EnterEquation();
                 printTheGrid();
        ChangeTheValueOfTheAppropiateCells();
                 printTheGrid();

    }
        public static void EnterEquation(){
        project.sp1[0].input("=A1", temp3);
        project.sp1[0].input("=B1", temp2);
        }
        public static void ChangeTheValueOfTheAppropiateCells(){
        System.out.println("Chane The value in A1");
        project.sp1[0].sheet[0].Acolumn[0].input(888);
        System.out.println("Chane The value in B1");
        project.sp1[0].sheet[1].Acolumn[0].input(111);
        }

        public static void printTheGrid(){
        for(int i=-1;i<5;i++){

            for(int j=-1;j<2 ;j++){
                if(i==-1){
                 if (j==-1){System.out.print("\t  ");
                 continue;
                 }
                    System.out.printf("\t%c\t",65+j);
                }
                else if(j==-1)
                    System.out.print((i+1) + "\t");
                else
                    System.out.print("\t"+ project.sp1[0].sheet[j].Acolumn[i].output()+"\t");
            }
System.out.println();
        }
    }

}
class Excel{
        public Region[] sp1;
        static int num;

    public Excel(){
        num=0;
        sp1=new Region[5];

    }
    public void createNew(){
        try{
        sp1[num]=new Region(5,2);
        }catch(NullPointerException e){
        }
        num++;
        System.out.println("New SpreadSheet Created");
    }
    public void load(){
try{
        try{
        FileInputStream file = new FileInputStream("Excell.doc");
        ObjectInputStream input=new ObjectInputStream(file);

        for(int i=0;i<num;i++){
        sp1[i]=(Region)input.readObject();
        }
        file.close();
        }catch(IOException e){
        System.out.println("Input output error");
        }
        }catch(ClassNotFoundException a){
         System.out.println("Class not found exception");
        }


    }
    public void store() {
        try{
        FileOutputStream  file=new FileOutputStream("Excell.doc");
        ObjectOutputStream output=new ObjectOutputStream(file);
        for(int i=0;i<num;i++){
            output.writeObject(sp1[i]);
        }
            file.close();
        }catch(IOException a){
            System.out.println("Input output error");
        }
    }


}
class Region implements Serializable{
    int limit;
    public Column[] sheet;

    public Region(int rows,int columns){
    limit=columns;
    sheet=new Column[columns];
    for(int i=0;i<columns;i++){
        sheet[i]=new Column(rows,sheet);
    }

    }
    public void input(String value,CellAddress address){
            sheet[address.Column()].input(value, address);
    }

    double rangeOfValues(CellAddress address1,CellAddress address2){
          double max,min;
          max=max(address1,address2);
          min=min(address1,address2);
            return (max-min);
        }
        double max(CellAddress address1,CellAddress address2){

             int startRaw,startColumn,endRaw,endColumn;
            double max=0;
            startRaw=address1.Raw()-1;
            startColumn=address1.Column();
            endRaw=address2.Raw()-1;
            endColumn=address2.Column();
            for(int i=startColumn;i<=endColumn;i++){
                for(int j=startRaw;j<=endRaw;j++){
                    if(Double.parseDouble(sheet[i].Acolumn[j].output())>max){
                        max=Double.parseDouble(sheet[i].Acolumn[j].output());
                    }

                }
            }
            return max;
        }
        double min(CellAddress address1,CellAddress address2){
            int startRaw,startColumn,endRaw,endColumn;
            double min=1000000000;

            startRaw=address1.Raw()-1;
            startColumn=address1.Column();
            endRaw=address2.Raw()-1;
            endColumn=address2.Column();
            for(int i=startColumn;i<=endColumn;i++){
                for(int j=startRaw;j<=endRaw;j++){
                    if(Double.parseDouble(sheet[i].Acolumn[j].output())<min){
                        min=Double.parseDouble(sheet[i].Acolumn[j].output());
                    }
                }
            }
            return min;
        }
        double total(CellAddress address1,CellAddress address2){
             int startRaw,startColumn,endRaw,endColumn;
            double total=0;

            startRaw=address1.Raw()-1;
            startColumn=address1.Column();
            endRaw=address2.Raw()-1;
            endColumn=address2.Column();
            for(int i=startColumn;i<=endColumn;i++){
                for(int j=startRaw;j<=endRaw;j++){
               total+=Double.parseDouble(sheet[i].Acolumn[j].output());
                }
            }
            return total;
        }
        double average(CellAddress address1,CellAddress address2){
         int startRaw,startColumn,endRaw,endColumn;
            double total=0,average;

            startRaw=address1.Raw()-1;
            startColumn=address1.Column();
            endRaw=address2.Raw()-1;
            endColumn=address2.Column();
        total=total(address1,address2);
        int num=(endRaw-startRaw+1)*(endColumn-startColumn+1);
        average=total/num;
        return average;

        }
        void copy(CellAddress address1,CellAddress address2,CellAddress TOaddress){

            int startRaw,startColumn,endRaw,endColumn,toRaw,toColumn;
            int rawStep=0,columnStep=0;
            startRaw=address1.Raw()-1;
            startColumn=address1.Column();
            endRaw=address2.Raw()-1;
            endColumn=address2.Column();
            toRaw=TOaddress.Raw()-1;
            toColumn=TOaddress.Column();
            rawStep=endRaw-startRaw;
            columnStep=endColumn-startColumn;

            String full=new String();
            String  sub=new String();
           for(int i=0;i<=columnStep;i++){
                for(int j=0;j<=rawStep;j++){
                    full=sheet[startColumn+i].Acolumn[startRaw+j].getClass().getName();
                    sub=full.substring(6);

                    if(sub.compareTo("Number")==0)
                        sheet[toColumn+i].Acolumn[toRaw+j].input(Double.parseDouble(sheet[startColumn+i].Acolumn[startRaw+j].output()));
                    else
                        sheet[toColumn+i].Acolumn[toRaw+j].input(sheet[startColumn+j].Acolumn[startRaw+i].output());


                }
            }
        }
        void cutPasteTO(CellAddress address1,CellAddress address2,CellAddress TOaddress){
            copy(address1,address2,TOaddress);
            delete(address1,address2);
        }
        void delete(CellAddress address1,CellAddress address2){
            int startRaw,startColumn,endRaw,endColumn;
            startRaw=address1.Raw()-1;
            startColumn=address1.Column();
            endRaw=address2.Raw()-1;
            endColumn=address2.Column();

                    String full=new String();
                    String  sub=new String();



            for(int i=startColumn;i<=endColumn;i++){

                for(int j=startRaw;j<=endRaw;j++){
                 full=sheet[i].Acolumn[j].getClass().getName();
                    sub=full.substring(6);

                    if(sub.compareTo("Number")==0)
                        sheet[i].Acolumn[j].input(0);
                    else
                        sheet[i].Acolumn[j].input("");
                }
            }

}
}
 class Column implements Serializable{
    private  String inputBar;
    Column[] spreadSheet;
    private int limit,prevIndex,lastIndex,j;
    private  double val,previousVal=0;
    public  Cell[] Acolumn;

    public Column(int no,Column[] spreadSheet){
        limit=no;
        inputBar=new String();
        Acolumn=new Cell[limit];
        this.spreadSheet=spreadSheet;
    }
    void input(String value,CellAddress address ){
        inputBar=value;
        int raw;
        int inputRaw;

         inputRaw=address.Raw()-1;
         String temp;
         int start,putColumn=0;

        if(inputBar.charAt(0)=='='){            //to manipulate functions in the spreadsheet
            prevIndex=0;
        for(int i=1;i<value.length();i++){
            if(inputBar.charAt(i)>64){
                prevIndex=i;
               
                putColumn=((int)inputBar.charAt(i)-65);
                    for( j=i+1;j<value.length();j++){
                        i++;
                    if(((int)inputBar.charAt(i))>47&((int)inputBar.charAt(i))<58){}
                    else break;
                    }
                lastIndex=j;
                temp=inputBar.substring(prevIndex+1,lastIndex);
                raw=Integer.parseInt(temp)-1;
                Acolumn[inputRaw].input(Double.parseDouble(Acolumn[raw].output()));
                spreadSheet[putColumn].Acolumn[raw].addObserver(Acolumn[inputRaw]);
                previousVal=Double.parseDouble(Acolumn[raw].output());
                if(lastIndex+1>value.length())
                    break;
            }
            if(((int)inputBar.charAt(i))>47&((int)inputBar.charAt(i))<58){
                start=i;
                for(;i<inputBar.length();i++){ if(inputBar.charAt(i)=='+'| inputBar.charAt(i)=='-'|inputBar.charAt(i)=='/'|inputBar.charAt(i)=='*'){break;}}
                temp=inputBar.substring(start, i);
                Acolumn[inputRaw].input(Double.parseDouble(temp));
                i--;
                previousVal=Double.parseDouble(temp);
            }
            else if(inputBar.charAt(i)=='+'){
                prevIndex=i;
                 start=++i;
                for(;i<inputBar.length();i++){
                    if(inputBar.charAt(i)=='+'| inputBar.charAt(i)=='-'|inputBar.charAt(i)=='/'|inputBar.charAt(i)=='*'){ break;    }           }
                        temp=inputBar.substring(start,i);
                        val=Double.parseDouble(Acolumn[inputRaw].output());
                        val=val+Double.parseDouble(temp);
                        previousVal=Double.parseDouble(temp);
                        Acolumn[inputRaw].input(val);
                        i--;        }
                    else if(inputBar.charAt(i)=='-'){
                        prevIndex=i;
                        start=++i;
                    for(;i<inputBar.length();i++){
                        if(inputBar.charAt(i)=='+'| inputBar.charAt(i)=='-'|inputBar.charAt(i)=='/'|inputBar.charAt(i)=='*'){break;            }
                        }
                    temp=inputBar.substring(start,i);
                    val =Double.parseDouble(Acolumn[inputRaw].output());
                    val =val-Double.parseDouble(temp);
                    Acolumn[inputRaw].input(val);
                    i--;
                    previousVal=Double.parseDouble(temp);
             }
             else if(inputBar.charAt(i)=='*'){
                 double tmp;
                    start=++i;
                for(;i<inputBar.length();i++){
                    if(inputBar.charAt(i)=='+'| inputBar.charAt(i)=='-'|inputBar.charAt(i)=='/'|inputBar.charAt(i)=='*'){
                    break;
                    }
                }
                    temp=inputBar.substring(start,i);
                    val=Double.parseDouble(Acolumn[inputRaw].output());
                    if(inputBar.charAt(prevIndex)=='+')
                        val-=previousVal;
                    else if (inputBar.charAt(prevIndex) == '-')
                        val+=previousVal;

                   tmp=previousVal*Double.parseDouble(temp);
                     if(inputBar.charAt(prevIndex)=='-')
                        val-=tmp;
                     else if (inputBar.charAt(prevIndex) == '+')
                        val+=tmp;
                     else
                         val=tmp;
                  Acolumn[inputRaw].input(val);
                  i--;
                  previousVal=val;
             }
             else if(inputBar.charAt(i)=='/'){
                    double tmp;
                    start=++i;
                for(;i<inputBar.length();i++){
                    if(inputBar.charAt(i)=='+'| inputBar.charAt(i)=='-'|inputBar.charAt(i)=='/'|inputBar.charAt(i)=='*'){
                    break;
                    }
                }
                    temp=inputBar.substring(start,i);
                    val=Double.parseDouble(Acolumn[inputRaw].output());
                    if(inputBar.charAt(prevIndex)=='+')
                        val-=previousVal;
                    else if (inputBar.charAt(prevIndex) == '-')
                        val+=previousVal;
                   tmp=previousVal/Double.parseDouble(temp);
                     if(inputBar.charAt(prevIndex)=='-')
                        val-=tmp;
                     else if (inputBar.charAt(prevIndex) == '+')
                        val+=tmp;
                     else
                         val=tmp;
                  Acolumn[inputRaw].input(val);
                  i--;
                  previousVal=val;
             }
            }
        }
        else{//To manipulate text
                inputBar=value;
                Text textVal=new Text();
                textVal.input(inputBar);
                Acolumn[address.Raw()]=textVal;
            }
     }

}
abstract class Cell extends Observable implements Serializable, Observer{
    CellAddress address=new CellAddress();
    public  void delete(){};
    public void input(double no){};
    public void input(String a){};
     public String output(){
     String temp=new String();
     return temp;
     }
    public void update(Observable o,Object arg){


    String full=new String();
    String sub=new String();

    full=arg.getClass().getName();
    sub=full.substring(10);
    if(sub.compareTo("Double")==0){
       double no=Double.parseDouble(arg.toString());
       input(no);
        }
    else{
        input(arg.toString());
        }
    }

}

class Text extends Cell {
    private String value;

@Override public void input(String val)            { value=val;
                        this.setChanged();
                        this.notifyObservers(value);
                }
          public void concatenate(String addString){
              value = value.concat(addString);
              setChanged();
              notifyObservers(value);
          }
          public void replace(String newString)    {
              value=newString;
              setChanged();
              notifyObservers(value);
          }
          public String getValue()                 { return value;                        }
@Override public void delete()                     {
            value=" ";
            setChanged();
            notifyObservers(value);
            }
@Override public String output()                   { return value;                        }


}
 class Number extends Cell{
  private double value;
            public void input(int value){ 
                this.value=value;
                setChanged();
                notifyObservers(value);
            }
  @Override public void input(double value){  
      this.value=(double)value;
      setChanged();
      notifyObservers(value);
  }
  @Override public String output(){  String temp;
                                        temp=""+value;
                                        return temp;}

  @Override public void delete(){ 
      
      value=(double)0;
      setChanged();
      notifyObservers(value);
  }


}
class CellAddress implements Serializable{
   private int raw;
   private String column;
   public int Raw(){
   return raw+1;
   }
   public int Column(){
       System.out.println(column);
   return (int)column.charAt(0)-65;
   
   }
   public void setAddress(int num,String word){
   raw=num-1;
   column=word;
   }
}

