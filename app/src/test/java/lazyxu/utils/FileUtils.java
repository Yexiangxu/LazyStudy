package lazyxu.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
    private static boolean hasHighVersion(String before, String after) {
        return sdkVersion(before)<sdkVersion(after);
    }
    private static int sdkVersion(String sdkdependencies){
        String sdkversion = sdkdependencies.substring(sdkdependencies.lastIndexOf(":") + 1);//去除最后一个字符 : 后字符串
        //取出版本号里面的数字用来做比较（存在上一次版本号为1.0.12，下一次为1.1.0，用该方法比较会出现1.1.0版本号比1.0.12小，但忽略）
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sdkversion);
        return Integer.parseInt(m.replaceAll("").trim());
    }
    public static void replaceLine(File file,String beforeContent,String replacedContent)throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));//读文件
        StringBuffer bf = new StringBuffer();
        String rl;//临时的每行数据
        while ((rl = br.readLine()) != null) {
            System.out.println(rl.indexOf(beforeContent));//打印该字符串是否在此行，是则输出0，否则输出-1
            if (rl.indexOf(beforeContent) == 0) { //或者!r1.startsWith(indexstr)
                if (hasHighVersion(rl,replacedContent)){
                    bf.append(replacedContent).append("\r\n");
                }else {
                    bf.append(rl).append("\r\n");
                }
            }else {
                bf.append(rl).append("\r\n");
            }
        }
        br.close();

        BufferedWriter out = new BufferedWriter(new FileWriter(file));//写入文件
        out.write(bf.toString());//把bf写入文件
        out.flush();//一定要flush才能写入完成
        out.close();//关闭
    }

    public static void writeString(File file, String content, boolean append) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, append);
            fileWriter.write(content);
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
    public static String readString(File file) throws IOException {
        if (file == null || !file.isFile()) {
            return null;
        }
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        InputStreamReader is =null;
        try {
            is=new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (is!=null){
                is.close();
            }
        }
        return fileContent.toString();
    }
}
