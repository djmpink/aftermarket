package cn.no7player.utils;

/**
 * Created by Administrator on 2016/1/20.
 */
public class RandomTextUtils {

    private static String dictionary="0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZ";//随机字典

    /**
     * 创建随机文本
     * @param num 位数
     * @return
     */
    public static String createActivityCode(int num){
        int len=dictionary.length();
        StringBuffer code=new StringBuffer("");
        for (int i = 0; i < num; i++) {
            code.append(dictionary.charAt((int) Math.floor(Math.random()*len)));
        }
        return code.toString();
    }

    /**
     * main 测试
     * @param args
     */
    public static void main (String[] args){
        for (int i = 0; i < 100; i++) {
            String code= RandomTextUtils.createActivityCode(6);
            System.out.println(code);
        }
    }


}
