package zhang.com;

/**
 * <p>
 * description
 * </p>
 *
 * @author KayTin 2019/9/27
 */
public class BestMethod {

    public static void main(String[] args) {

        boolean b1 = simpePanDuan(1024);
        System.out.println("刚才输入的数是否为2的n次方所对应的数？ " + b1);
    }

    public static boolean simpePanDuan(int n) {
        if (n < 1) {
            return false;
        }

        if ((n & (n - 1)) == 0) {
            return true;
        }

        return false;
    }
}
