import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class Reader implements Runnable {
    private static int now = 0;
    static File file = new File("C://Users//dongf//Desktop//book");
    static RandomAccessFile raf = null;
    final static int len = 256;

    Reader() throws IOException {
        raf = new RandomAccessFile(file, "rw");
    }

    @Override
    public void run() {
        while (true) {
            try {

                synchronized (raf) {
                    byte[] b = new byte[len];
                    raf.seek(now);
                    int temp = raf.read(b);
                    if (temp == -1) {
                        return;
                    }
                    now += temp;
                    System.out.println(new String(b));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[]args)throws IOException{
        Reader run=new Reader();
        new Thread(run).start();
        new Thread(run).start();
        new Thread(run).start();
    }

}