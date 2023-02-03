// package Practice;

// class Person {
//     String name;
//     int age;
//     int height;
//     int weight;

//     Person() {}
//     Person(String name, int age, int height, int weight) {
//         this.name = name;
//         this.age = age;
//         this.height = height;
//         this.weight = weight;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public int getAge(){return age;}
//     public void setAge(int age){this.age = age;}

//     public int getHeight(){return height;}
//     public void setHeight(int height){this.height = height;} 

//     public int getWeight(){return weight;}
//     public void setWeight(int weight){this.weight = weight;} 

//     public void move() {
//         System.out.println("이동중...");
//     }
// }

// class Villain extends Person {
//     private String unique_key; // 다른 곳에서 값을 바꿀 수 없다.
//     private int weapon;
//     private double power;

//     Villain(String name, int age, int height, int weight, String unique_key, int weapon, double power) {
//         this.unique_key = unique_key;
//         this.weapon = weapon;
//         this.power = power;
//     }

//     public String getUnique_key(){return unique_key;}
//     public void setUnique_key(String unique_key) {this.unique_key = unique_key;}

//     public int getWeapon(){return weapon;}
//     public void setWeapon(int weapon){this.weapon = weapon;}

//     public double getPower(){return power;}
//     public void setPower(int power){this.power = power;}

//     public void printPerson() {
//         System.out.println("악당 이름 : " + getName());
//         System.out.println("악당 나이 : " + getAge());
//         System.out.println("악당 키 : " + getHeight());
//         System.out.println("악당 몸무게 : " + getWeight());
//         System.out.println("악당 넘버 : " + getUnique_key());
//         System.out.println("악당 무기 : " + getWeapon());
//         System.out.println("악당 파워 : " + getPower());

//     }
// }

// class Hero extends Person {

// }

// public class getter {
//     public static void main(String[] args) {
//         Villain v1 = new Villain("좀비", 20, 180, 80, "15001253", 1, 99.5);
//         v1.printPerson();
//     }
// }

// /*
// 빌런이나 히어로나 둘다 이름 나이 키 몸무게의 속성은 공통으로 가질테니 person이라는 
// 부모클래스에 적어둔다.

// private 되어있는 곳은 getter, setter 매서드를 사용하지 않으면 가져올 수 없다.
// private 가져오려면 v1.weapon 하는 것이 아닌 v1.getWeapon() 해야한다는 의미.
//  */