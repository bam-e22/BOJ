class Main {

    public static void main(String[] args) {

        Person person = new Person();
        Person person2 = new Tom();

        System.out.println(person.name);
        System.out.println(person.getName());

        System.out.println(person2.name);
        System.out.println(person2.getName());

    }

}

class Tom extends Person {

    String name = "Tom";

    @Override
    public String getName() {

        return this.name;
    }
}

class Person {

    String name = "Person";

    public String getName() {

        return name;
    }
}