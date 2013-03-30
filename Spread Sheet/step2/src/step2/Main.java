
package step2;



public class Main {
static Column A=new Column(5);
static CellAddress temp1=new CellAddress();
static CellAddress temp2=new CellAddress();
static CellAddress temp3=new CellAddress();

    public static void main(String[] args) {
    A.Acolumn[0]=new Number();A.Acolumn[1]=new Number();A.Acolumn[2]=new Number();A.Acolumn[3]=new Number(); A.Acolumn[4]=new Text();
        temp1.setAddress(1,"A");temp2.setAddress(2,"A");temp3.setAddress(3,"A");

      testTextValue();
      testEnterValue();
      testEquations();

    }
public static void testTextValue(){
        
        A.Acolumn[4].input("Example 2");
        System.out.println("Value of A5 : "+A.Acolumn[4].output());
}
public static void testEnterValue(){
        A.Acolumn[2].input(34.0);
        System.out.println("Entered the value to the A3 : "+A.Acolumn[2].output());
        A.Acolumn[0].input(59.0);
        System.out.println("Entered the value to the A3 : "+A.Acolumn[0].output());
}
public static void testEquations(){
        A.input("=A1", temp2);
        System.out.println("Equation : =A1 Value : " + A.Acolumn[1].output());
        A.input("=A3+10", temp2);
        System.out.println("Equation : =A3+10 Value : " + A.Acolumn[1].output());
        A.input("=A3-10", temp2);
        System.out.println("Equation : =A3-10 Value : " + A.Acolumn[1].output());
        A.input("=A3*10", temp2);
        System.out.println("Equation : =A3*10 Value : " + A.Acolumn[1].output());
        A.input("=A3/10", temp2);
        System.out.println("Equation : =A3/10 Value : " + A.Acolumn[1].output());


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
      // System.out.println("I came inside");
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
