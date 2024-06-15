package lazyxu;

public class Singe {
    public static volatile Singe mInstance = null;

    public static Singe getInstance() {
        Action tiger=new Tiger();
        tiger.hunt();

        if (mInstance == null) {
            synchronized (Singe.class){
                if (mInstance==null){
                    mInstance = new Singe();
                }
            }
        }
        return mInstance;
    }
}
