//1

//class patient{
//    String name = "zainab";
//
//}
//class Doctor {
//    void checkpatient(patient p){
//        System.out.println("dr sara is checking"+ p.name);
//    }
//}
//public class task2{
//    static void main(String[] args) {
//        patient p1 = new patient();
//        Doctor d1 = new Doctor();
//        d1.checkpatient(p1);
//    }
//}

//2

//class player{
//    String name = "zainab";
//
//}
//class coach{
//    void train(player p){
//        System.out.println("coach is training"+p.name);
//
//    }
//}
//public class task2{
//    static void main(String[] args) {
//        player p1 = new player();
//        coach c1 = new coach();
//        c1.train(p1);
//    }
//}

//3

class order {
    int orderno = 104;
}
class customer{
    String name = "zainab";
    void placeorder(order o){
        System.out.println(name+"placed order"+o.orderno);
    }
}
public class task2{
    static void main(String[] args) {
        customer c1 = new customer();
        order o1 = new order();
        c1.placeorder(o1);
    }
}