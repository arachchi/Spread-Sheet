package step1;

public class Main {

        static Cell cell;
        static Number num1=new Number();
        static Number num2=new Number();
        static Number num3=new Number();

        static Text text1=new Text();
        static Text text2=new Text();
        static Text text3=new Text();

    public static void main(String[] args) {
       testNumber();
       testText();
       testHoldDifferentTypes();

    }
   public static void testNumber(){

        num1.input(23);
        System.out.println("The input value for test Number 1 is "+num1.output());
        num2.input(25.897);
        System.out.println("The input value for test Number 2 is "+num2.output());
        num3.input(5.897);
        System.out.println("The input value for test Number 3 is "+num3.output());

        num3.delete();
        System.out.println("test Number value is deleted. the new value : "+num1.output());

        num3.add(num1.output(), num2.output());
        System.out.println("Add : "+num1.output()+" And "+num2.output()+"\tThe answer : "+num3.output());

        num3.subtract(num1.output(), num2.output());
        System.out.println("Subtract : "+num1.output()+" And "+num2.output()+"\tThe answer : "+num3.output());

       num3.multiply(num1.output(), num2.output());
        System.out.println("Multiply : "+num1.output()+" And "+num2.output()+"\tThe answer : "+num3.output());

        num3.devide(num1.output(), num2.output());
        System.out.println("Divide : "+num1.output()+" And "+num2.output()+"\tThe answer : "+num3.output());

    }
    static void testText(){
        text1.input("Application");
        System.out.println("The input value test Text 1 is "+text1.output());
        text2.input("Object");
        System.out.println("The input value test Text 2 is "+text2.output());
        text3.input("Setting");
        System.out.println("The input value test Text 3 is "+text3.output());

        text2.concatenate(" Oriented Programming");
        System.out.println("test Text 2 is concatenated and new value is "+text2.output());
        text3.replace("Development");
        System.out.println("test Text 3 is replaced by "+text3.output());
        text1.delete();
        System.out.println("test Text 1 is deleted ..the new value : "+text1.output());
    }
    static void testHoldDifferentTypes(){
        cell=text2;
        System.out.println("The value of the cell : " + cell.output());
        cell=num2;
        System.out.println("The value of the cell : " + cell.output());
    }

}

abstract class Cell {

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
            public void add(String x,String y){
            value=Double.parseDouble(x)+Double.parseDouble(y);
            }
             public void subtract(String x,String y){
            value=Double.parseDouble(x)-Double.parseDouble(y);
            }
             public void multiply(String x,String y){
            value=Double.parseDouble(x)*Double.parseDouble(y);
            }
             public void devide(String x,String y ){
            value=Double.parseDouble(x)/Double.parseDouble(y);
            }


  @Override public void delete(){ value=(double)0; }


}

