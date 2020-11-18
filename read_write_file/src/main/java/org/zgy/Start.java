package org.zgy;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Start {
    public static void main(String[] args) {
        File filein = new File("F:/zhangggengyu/upLine-initTable/broadcasting_center/offline_package_list_vod_all.sql");
//        File fileout = new File("F:/zhangggengyu/upLine-initTable/broadcasting_center/offline_package_list_vod.sql");
        FileOutputStream fout = null;
        StringBuffer sb = new StringBuffer();
        long count = 0;
        try {
            FileInputStream fis = new FileInputStream(filein);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
//            fout = new FileOutputStream(fileout);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("7888080573215744")) {
//                    System.out.println(line);
                    line = getString(line);
                    sb.append(line + "\r\n");
                    ++count;
                    System.out.println(line);
                }
            }
//            OutputStreamWriter osw = new OutputStreamWriter(fout);
//            BufferedWriter bw = new BufferedWriter(osw);
//            bw.write(sb.toString());
//            bw.flush();

//            bw.close();
//            osw.close();
            isr.close();
            br.close();
            fis.close();
//            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static String getIdByOfflinepackagelist(String line) {
        int index = getFromIndex(line, ",", 1);
        line = line.substring(44, index - 1);
        return line;
    }

    private static String getString(String line) {
        int i1 = getFromIndex(line, "'", 5);
        int i2 = getFromIndex(line, "'", 6);
        line = line.substring(i1, i2 + 1);
        return line;
    }

    private static String addTableName(String line) {
        line = line.substring(0, 13) + "code" + line.substring(13); // 21209
        return line;
    }

    private static String getBusinessIdOfOfflinePackageList(String line) {
        StringBuffer reverse = new StringBuffer(line).reverse();
        int i_2 = reverse.indexOf("'");
        int i2 = line.length() - 1 - i_2;
        int i_1 = getFromIndex(reverse.toString(), "'", 2);
        int i1 = line.length() - 1 - i_1;
        line = line.substring(i1 +1, i2);
        return line;
    }

    //子字符串modelStr在字符串str中第count次出现时的下标
    private static int getFromIndex(String str, String modelStr, Integer count) {
        //对子字符串进行匹配
        Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
        int index = 0;
        //matcher.find();尝试查找与该模式匹配的输入序列的下一个子序列
        while(slashMatcher.find()) {
            index++;
            //当modelStr字符第count次出现的位置
            if(index == count){
                break;
            }
        }
        //matcher.start();返回以前匹配的初始索引。
        return slashMatcher.start();
    }

    public static String filterCtrlChars(String source) {
        StringBuffer sf = new StringBuffer();
        for (char c : source.toCharArray()) {
            if (Character.isISOControl(c)) {
                sf.append("\\")
                        .append(Integer.toOctalString(c));
            } else {
                sf.append(c);
            }
        }
        return sf.toString();

    }


///////////////////
    public void compareFile(){
        File file = new File("F:/1.txt");
        File file2 = new File("F:/2.txt");
        Set fileTextSet = new HashSet();
        Set file2TextSet = new HashSet();
        try {
            getText(file,fileTextSet);
            getText(file2,file2TextSet);
            compareSet(fileTextSet, file2TextSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void compareSet(Set textSet,Set StextSet2){
        int numOfCommon=0,numOfBase=0,numOfAdap=0;
        for (Iterator iterator = textSet.iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            if(StextSet2.contains(name)){
                System.out.println("common:"+name);
                numOfCommon++;
            }else{
                System.out.println("1:"+name);
            }
            numOfBase++;
        }
        for (Iterator iterator = StextSet2.iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
//            if(!baitextSet.contains(name)){
//                System.out.println("2"+name);
//            }
            numOfAdap++;
        }
        System.out.println("Common: " + numOfCommon);
        System.out.println("1: " + numOfBase);
        System.out.println("2: " + numOfAdap);
    }
    private void getText(File file,Set textSet) throws IOException {
        BufferedReader br = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String lineStr = null;
            while ((lineStr = br.readLine())!=null) {
                String text = lineStr.substring(lineStr.indexOf(":"));//按照du需求切分
                textSet.add(text);
            }
            if(br!=null){
                br.close();
            }
            if(is!=null){
                is.close();
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(br!=null){
                br.close();
            }
            if(is!=null){
                is.close();
            }
        }
    }
}