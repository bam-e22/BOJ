class Parent {

    public String str = "Parent.str";

    public String getStr() {

        return str;
    }

    public static void hello() {

        System.out.println("Parent.hello()");
    }
}

class Child extends Parent {

    public String str = "Child.str";

    public String getStr() {

        return str;
    }

    public static void hello() {

        System.out.println("Child.hello()");
    }
}

public class Main {

    public static void main(String[] args) {

        Child child = new Child();
        Parent parent = child;

        System.out.println(child.str);
        System.out.println(child.getStr());
        child.hello();

        System.out.println(parent.str);
        System.out.println(parent.getStr());
        parent.hello();

    }
}