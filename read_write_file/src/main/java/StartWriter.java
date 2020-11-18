import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class StartWriter {
    public static void main(String[] args) {
        File fileId = new File("F:/zhangggengyu/upLine-initTable/broadcasting_center/offline_package_list_vod_id.sql");
        File fileVod = new File("F:/zhangggengyu/upLine-initTable/broadcasting_center/offline_package_list_vod_all.sql");
        File fileout = new File("F:/zhangggengyu/upLine-initTable/broadcasting_center/offline_package_list_vod_old_list2.sql");
        FileOutputStream fout = null;
        StringBuffer sb = new StringBuffer();
        long i = 0L;
        long j = 0L;
        String brIdStr = null;
        String brVodStr = null;
        Set set = new HashSet();
        try {
            BufferedReader brId = new BufferedReader(new InputStreamReader(new FileInputStream(fileId)));
            BufferedReader brVod = new BufferedReader(new InputStreamReader(new FileInputStream(fileVod)));
//            fout = new FileOutputStream(fileout);
            while ((brIdStr = brId.readLine()) != null) {
                set.add(brIdStr);
                ++i;
            }
            while ((brVodStr = brVod.readLine()) != null) {
                while (set.iterator().hasNext()) {
                    ++j;
                }
            }

//            sb.append("UPDATE offline_package_list SET list = " + brListStr + " where id = " + brIdStr + "\r\n");

//            OutputStreamWriter osw = new OutputStreamWriter(fout);
//            BufferedWriter bw = new BufferedWriter(osw);
//            bw.write(sb.toString());
//            bw.flush();
//            bw.close();
//            osw.close();
            brVod.close();
            brId.close();
            System.out.println(j);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
