package advanced.a_companion;

public class JavaClass {

    public int javaNum = 100;
    public void javaMethod(){
        System.out.println("java Method from javaClass");
    }

    public static int javaNum2 = 200;

    public static void main(String[] args) {
        KotlinClass t1 = new KotlinClass();
        System.out.println("t1.getA1() = " + t1.getA1());
        t1.testFun1();
        System.out.println();

        /**
         * Kotlin Static members are in Companion Object.
         */
//        System.out.println("KotlinClass.Companion.getA2() = " + KotlinClass.getA2());
        System.out.println("KotlinClass.Companion.getA2() = " + KotlinClass.Companion.getA2());
        KotlinClass.Companion.companionFunction();
        System.out.println();
        System.out.println("KotlinClass.getA3() = " + KotlinClass.getA3());
        KotlinClass.jvmStaticMethod();
    }

    public static void javaMethod2(){
        System.out.println("java Method2 from javaClass");
    }

}
