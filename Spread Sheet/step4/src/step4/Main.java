/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package step4;


public class Main {

static Region spreadSheet = new Region(2,5);
static CellAddress temp1=new CellAddress();static CellAddress temp4=new CellAddress();
static CellAddress temp2=new CellAddress();static CellAddress temp5=new CellAddress();
static CellAddress temp3=new CellAddress();static CellAddress temp6=new CellAddress();

    public static void main(String[] args) {
        spreadSheet.sheet[0].Acolumn[0]=new Number();spreadSheet.sheet[1].Acolumn[0]=new Number();
        spreadSheet.sheet[0].Acolumn[1]=new Number();spreadSheet.sheet[1].Acolumn[1]=new Number();
        spreadSheet.sheet[0].Acolumn[2]=new Number();spreadSheet.sheet[1].Acolumn[2]=new Number();
        spreadSheet.sheet[0].Acolumn[3]=new Number();spreadSheet.sheet[1].Acolumn[3]=new Number();
        spreadSheet.sheet[0].Acolumn[4]=new Number();spreadSheet.sheet[1].Acolumn[4]=new Number();

        spreadSheet.sheet[0].Acolumn[0].input(1);spreadSheet.sheet[1].Acolumn[0].input(4);
        spreadSheet.sheet[0].Acolumn[1].input(2);spreadSheet.sheet[1].Acolumn[1].input(5);
        spreadSheet.sheet[0].Acolumn[2].input(3);spreadSheet.sheet[1].Acolumn[2].input(6);



        temp1.setAddress(1,"A");temp2.setAddress(2,"B");temp3.setAddress(4,"A");temp4.setAddress(4,"A");
        printTheGrid();
        TestCopyAndPaste();
        printTheGrid();
        TestDelete();
        printTheGrid();
        TestCutAndPaste();
        printTheGrid();



    }
   
    public static void TestCutAndPaste(){
        System.out.println("Value cut and pasted from "+ temp1.column+""+(temp1.raw+1) + " - "+ temp2.column+""+(temp2.raw+1)+ " to " + temp3.column+""+(temp3.raw+1) );
        spreadSheet.cutPasteTO(temp1, temp2, temp3);
    }
    public static void TestCopyAndPaste(){
        System.out.println("Value copied and pasted from "+ temp1.column+""+(temp1.raw+1) + " - "+ temp2.column+""+(temp2.raw+1)+ " to " + temp3.column+""+(temp3.raw+1) );
        spreadSheet.copy(temp1, temp2, temp3);
    }
    public static void TestDelete(){
        System.out.println("Value Deleted in the range : "+ temp1.column+""+(temp2.raw+1));
        spreadSheet.delete(temp1, temp2);
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
                    System.out.print("\t"+ spreadSheet.sheet[j].Acolumn[i].output()+"\t");
            }
System.out.println();
        }
    }
}
class Region{
    int limit;
    public Column[] sheet;

    public Region(int rows,int columns){
    limit=columns;
    sheet=new Column[columns];
    for(int i=0;i<columns;i++){
        sheet[i]=new Column(rows);
    }
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
            startRaw=address1.raw;
            startColumn=(int)address1.column.charAt(0)-65;
            endRaw=address2.raw;
            endColumn=address2.column.charAt(0)-65;
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

            startRaw=address1.raw;
            startColumn=address1.column.charAt(0)-65;
            endRaw=address2.raw;
            endColumn=address2.column.charAt(0)-65;
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

            startRaw=address1.raw;
            startColumn=(int)address1.column.charAt(0)-65;
            endRaw=address2.raw;
            endColumn=address2.column.charAt(0)-65;
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

            startRaw=address1.raw;
            startColumn=address1.column.charAt(0)-65;
            endRaw=address2.raw;
            endColumn=address2.column.charAt(0)-65;
        total=total(address1,address2);
        int num=(endRaw-startRaw+1)*(endColumn-startColumn+1);
        average=total/num;
        return average;

        }
        void copy(CellAddress address1,CellAddress address2,CellAddress TOaddress){

            int startRaw,startColumn,endRaw,endColumn,toRaw,toColumn;
            int rawStep=0,columnStep=0;
            startRaw=address1.raw;
            startColumn=address1.column.charAt(0)-65;
            endRaw=address2.raw;
            endColumn=address2.column.charAt(0)-65;
            toRaw=TOaddress.raw;
            toColumn=TOaddress.column.charAt(0)-65;
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
            startRaw=address1.raw;
            startColumn=address1.column.charAt(0)-65;
            endRaw=address2.raw;
            endColumn=address2.column.charAt(0)-65;

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
 class Column{
    private  String inputBar;
    private int limit,prevIndex,lastIndex,j;
    private  double val,previousVal=0;
    public  Cell[] Acolumn;

    public Column(int no){
        limit=no;
        inputBar=new String();
        Acolumn=new Cell[limit];
    }

    void input(String value,CellAddress address ){
        inputBar=value;
        int raw;
        int inputRaw;

         inputRaw=address.raw;
         String temp;
         int start;

        if(inputBar.charAt(0)=='='){            //to manipulate functions in the spreadsheet
            prevIndex=0;
        for(int i=1;i<value.length();i++){
            if(inputBar.charAt(i)>64){
                prevIndex=i;
                    for( j=i+1;j<value.length();j++){
                        i++;
                    if(((int)inputBar.charAt(i))>47&((int)inputBar.charAt(i))<58){}
                    else break;
                    }
                lastIndex=j;
                temp=inputBar.substring(prevIndex+1,lastIndex);
                raw=Integer.parseInt(temp)-1;
                Acolumn[inputRaw].input(Double.parseDouble(Acolumn[raw].output()));
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
                Acolumn[address.raw]=textVal;
            }
     }
}
abstract class Cell {
    CellAddress address=new CellAddress();
    public  void delete(){};
    public void input(double no){};
    public void input(String a){};
     public String output(){
     String temp=new String();
     return temp;
     }



}
class Text extends Cell {
    private String value;

@Override public void input(String val)            { value=val;                           }
          public void concatenate(String addString){ value = value.concat(addString);     }
          public void replace(String newString)    { value=newString;                     }
          public String getValue()                 { return value;                        }
@Override public void delete()                     { value=" ";                           }
@Override public String output()                   { return value;                        }
}
 class Number extends Cell{
  private double value;
            public void input(int value){ this.value=value;  }
  @Override public void input(double value){  this.value=(double)value;  }
  @Override public String output(){  String temp;
                                        temp=""+value;
                                        return temp;}

  @Override public void delete(){ value=(double)0; }


}
class CellAddress{
   public int raw;
   public String column;

   public void setAddress(int num,String word){
   raw=num-1;
   column=word;
   }
}


