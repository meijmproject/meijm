import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 打包拿代码工具类
 * @author Administrator
 *
 */
public class DaBaoUtil {	
	/**
	 * 开发svn地址(全拿文件的地址)
	 */
	public static String SOURCE_FILE_PATH="D:/project/DPOS_HSP_SZHP/branch/Dpos_HspSzhp_20170410/";	
	/**
	 * 测试打包svn地址 
	 */
	public static String TARGET_FILE_PATH = "D:/project/DPOS_HSP_SZHP/trunk/";	
	/**
	 * 提交单的路径
	 */
	public static String TIJIAODAN_NAME = 
	"D:/project/DPOS_HSP_SZHP/trunk/test.txt";	
	/**
	 *  子项目名称
	 */
	private static List<String> PROJECT_NAME = new ArrayList<String>();
	static {
		PROJECT_NAME.add("freelance-hspszhp");
		PROJECT_NAME.add("freelance-admin");
		PROJECT_NAME.add("freelance-admin-sao");
		PROJECT_NAME.add("freelance-component");
		PROJECT_NAME.add("freelance-core");
		PROJECT_NAME.add("freelance-hr-biz");
		PROJECT_NAME.add("freelance-hr-component");
		PROJECT_NAME.add("freelance-hr-biz-szchp");
		PROJECT_NAME.add("freelance-hr-info");
		PROJECT_NAME.add("freelance-hr-orghc");
		PROJECT_NAME.add("freelance-hr-report");
		PROJECT_NAME.add("freelance-hr-res");
		PROJECT_NAME.add("freelance-hr-res-sao");
		PROJECT_NAME.add("freelance-hr-worktop");
		PROJECT_NAME.add("freelance-libs");
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = null;		
		String FILE_IN = "D:/project/DPOS_HSP_SZHP/trunk/提交单/";  
        File f = new File(FILE_IN);  
        List<String> txtNames = new ArrayList<String>();  
        txtNames = getFileList(f);
        System.out.println(txtNames.size());         
        for (String txtName : txtNames) {  
        	System.out.println(txtName); 
			try {
				br = new BufferedReader(new FileReader(new File(txtName)));
				String tempLine;
				int num = 0; //记录拷贝文件个数
				try {
					tempLine = br.readLine();				
					tempLine = tempLine.replace("\\", "/");
					while (tempLine != null){	
						for(String temp:PROJECT_NAME){
							if(isFile(tempLine,temp)){								
								String s = tempLine.substring(tempLine.indexOf("/"+temp)+temp.length()+1);
								s = temp+s.trim();
								//构建开发地址
								String s1 = SOURCE_FILE_PATH+s;
								//构建测试地址
								String s2 = TARGET_FILE_PATH+s;								
								if(fileCopy(s1,s2)){
								num++;
								}							
							}
						}						
						tempLine = br.readLine();
					}					
				} catch (IOException e) {
					e.printStackTrace();
					num--;
				}		
				System.out.println("更新了  **** "+num+" ****文件");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }
	}	
	/**
	 * 拷贝文件
	 * @param sourceStr
	 * @param targetStr
	 */
	public static boolean fileCopy(String sourceStr,String targetStr) {		
		boolean flag = false;
		File f1 = new File(sourceStr);		
		//替换文件目录的格式
		String s3 = targetStr.replace("/", "\\");
		//创建文件所在的目录
		String s31 = s3.substring(0,(s3.lastIndexOf("\\")));
		System.out.println(targetStr);
		File f2 = new File(s31);				
		if(!f2.exists()){
			System.out.println("增加了一个新的文件夹==="+s31);
			f2.mkdirs();			
		}		
		File f3 = new File(s3);		
		flag = copyFileUtil(f1,f3);		
		return flag;
	}	

	public static boolean copyFileUtil(File f1, File f2){
		boolean flag = false;
		try {
			FileInputStream fi = new FileInputStream(f1);			
			FileOutputStream fo = new FileOutputStream(f2);			
			byte[] buff = new byte[1024];
			int i = 0;
			try {
				while( (i= fi.read(buff))!= -1){					
					fo.write(buff, 0, i);										
				}
			} catch (IOException e) {
				e.printStackTrace();
			}						
			try {
				fi.close();
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
			flag = true;			
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		return flag;
	}
	
	public static boolean isFile(String tempLine,String temp){
		boolean flag = false;
		List<String > str = new ArrayList<String>();
		if(tempLine.indexOf("\\")>=0){
			String[] strSplit = tempLine.split("/");
			for(int i=0;i<strSplit.length;i++){				
				if(i>4){
					break;
				}
				if(strSplit[i].equals(temp)){
					flag = true;					
				}
			}
		}
		if(tempLine.indexOf("/")>=0){
			String[] strSplit = tempLine.split("/");
			for(int i=0;i<strSplit.length;i++){				
				if(i>4){
					break;
				}
				/**System.out.println(strSplit[i]);**/
				if(strSplit[i].equals(temp)){
					flag = true;					
				}
			}
		}
		return flag;		
	}
	
	public static List<String> getFileList(File file) {  		  
        List<String> result = new ArrayList<String>();    
        if (!file.isDirectory()) {  
            System.out.println(file.getAbsolutePath());  
            result.add(file.getAbsolutePath());  
        } else {  
            File[] directoryList = file.listFiles(new FileFilter() {  
                public boolean accept(File file) {  
                    if (file.isFile() && file.getName().indexOf("txt") > -1) {  
                        return true;  
                    } else {  
                        return false;  
                    }  
                }  
            });  
            for (int i = 0; i < directoryList.length; i++) {  
                result.add(directoryList[i].getPath());  
            }  
        }    
        return result;  
    }
}
