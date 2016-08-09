package com.test;


/**
 *内部类是指在一个外部类的内部再定义一个类。类名不需要和文件夹相同。
 **内部类可以是静态static的，也可用public，default，protected和private修饰。（而外部顶级类即类名和文件名相同的只能使用public和default）。
 * 注意：内部类是一个编译时的概念，一旦编译成功，就会成为完全不同的两类。
 * 对于一个名为outer的外部类和其内部定义的名为inner的内部类。
 * 编译完成后出现outer.class和outer$inner.class两类。所以内部类的成员变量/方法名可以和外部类的相同。
 * 
 * 
 * 二、 作用
 *1.内部类可以很好的实现隐藏
 * 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
 *2．内部类拥有外围类的所有元素的访问权限
 *3.可是实现多重继承
 *4.可以避免修改接口而实现同一个类中两种同名方法的调用。
 * 
 */
public class InnerClass {

	
	public static void main(String[] args) { 
		InnerClass outer = new InnerClass(); 
		//1在成员内部类
		InnerClass.Inner1 inner = outer.new Inner1(); 
        inner.print("Outer.new"); 
        inner = outer.getInner(); 
        inner.print("Outer.get"); 
        
    } 
 
    //1在成员内部类要引用外部类对象时，使用outer.this来表示外部类对象；
    public Inner1 getInner() { 
        return new Inner1(); 
    } 
 
    public class Inner1 { 
        public void print(String str) { 
            System.out.println(str); 
        } 
    } 
	
	//2. 局部内部类是指内部类定义在方法和作用域内
    private void internalTracking(boolean b) { 
        if (b) { 
            class TrackingSlip { 
                private String id; 
                TrackingSlip(String s) { 
                    id = s; 
                } 
                String getSlip() { 
                    return id; 
                } 
            } 
            TrackingSlip ts = new TrackingSlip("slip"); 
            String s = ts.getSlip(); 
        } 
    } 
 
    public void track() { 
        internalTracking(true); 
    } 
    
    //3. 嵌套内部类
    
    //4. 匿名内部类
    public Inner getInner(final String name, String city) { 
        return new Inner() { 
            private String nameStr = name; 
 
            public String getName() { 
                return nameStr; 
            } 
        }; 
    } 
    
    interface Inner { 
        String getName(); 
    }
	
}
