package framwork.mybatis;
import java.util.concurrent.RecursiveTask;
 
@SuppressWarnings("serial")
public class MyTask extends RecursiveTask<Integer> {
    int i;
 
    public MyTask(int i) {
        this.i = i;
    }
 
    @Override
    protected Integer compute() {
        if (i >= 100) {
            return i * i;
        }
 
        MyTask newTask2 = new MyTask(i + 1);
        newTask2.fork();
 
        return i*i + newTask2.join();
 
    }
 
}