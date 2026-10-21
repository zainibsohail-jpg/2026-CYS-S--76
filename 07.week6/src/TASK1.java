

class met {
    void n (){
        System.out.println( "zainab");

    }
}
class sou{
    void name(met m){
        m.n();
    }

}

public class TASK1{
    static void main(String[] args) {
        met m1 = new met();
        sou m2 = new sou();
        m2.name(m1);
    }
}