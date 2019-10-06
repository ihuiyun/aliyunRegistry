import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Follow {
	public static String[] Wenfa=new String[100];
	public static String[] First=new String[100];
	public static String[] Follow=new String[100];  //待求的follow集
	public static String[] Fuhao=new String[2];
	
	public static String unend;
	public static String end;
	
	//从文件wenfa.txt读入文法函数
	public static void filein(String name,String[] wenfa) {
		File file=new File(name);  
        BufferedReader reader=null;  
        String temp=null;  
        int line=0;  
        try{  
                reader=new BufferedReader(new FileReader(file));  
                while((temp=reader.readLine())!=null){ 
                	wenfa[line]= temp; 
                    line++;  
                }  
        }  
        catch(Exception e){  
            e.printStackTrace();  
        }  
        finally{  
            if(reader!=null){  
                try{  
                    reader.close();  
                }  
                catch(Exception e){  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
	//判断是终结符还是非终结符
	public static boolean is_end(char op) {
		boolean re = false;
		for(int i = 0; i < (end.length() + 1) / 2; i++) {
			if(op == end.charAt(2 * i)) {
				re = true;
			}
		}
		return re;
	}
	//求first
	public static String re_first(String[] first, char op) {
		String re="";
		for(int i = 0; first[i] != null; i++) {
			if(op==first[i].charAt(0)) {
				re = first[i].substring(2, first[i].length());
			}
		}
		return re;
	}
	//查询follow
	public static String re_follow(String[] follow, char op) {
		String re="";
		for(int i = 0; follow[i] != null; i++) {
			if(op==follow[i].charAt(0)) {
				re = follow[i].substring(2, follow[i].length());
			}
		}
		return re;
	}
	//不重复添加follow
		public static String add_follow(String temp, String add) {
			int q = 0;
			for(int k = 0; k < add.length(); k++) {
				q = 0;
				for(int i = 2; i < temp.length(); i++) {
					if(add.charAt(k)==temp.charAt(i)) {
						q = 1;
						break;
					}
				}
				if(q == 0) {
					temp = temp+add.charAt(k);
				}
			}
			return temp;
		}
	//求follow集
	public static void s_follow(String[] follow, char op, int index) {
			String temp = String.valueOf(op) + " ";
			if(op == unend.charAt(0)) {
				temp = temp+ "#";
			}
			for(int j = 0;Wenfa[j]!=null;j++) {
				for(int k = 2; k < Wenfa[j].length(); k++) {
					if(Wenfa[j].charAt(k) == op) {
						if(k == Wenfa[j].length()-1) {
							if(Wenfa[j].charAt(0)== op) {
								break;
							}else if(re_follow(follow,Wenfa[j].charAt(0))!="") {
								temp = add_follow(temp,re_follow(follow,Wenfa[j].charAt(0)));
							}else {
								s_follow(follow,Wenfa[j].charAt(0),index+1);
							}
						}
						else{
							if(is_end(Wenfa[j].charAt(k+1))) {
								temp = temp + Wenfa[j].charAt(k+1);
							}
							else {
								char tp=Wenfa[j].charAt(k+1);
								if(re_first(First,tp).charAt(re_first(First,tp).length()-1)=='ε') {
									String re_f = re_first(First,Wenfa[j].charAt(k+1));
									temp = temp + re_f.substring(0, re_f.length()-1);
									int max = Wenfa[j].length();
									int rt = k+2;
									if(rt<max) {
										char pp = Wenfa[j].charAt(rt);
										while(re_first(First,pp).charAt(re_first(First,pp).length()-1) =='ε'&& rt < max) {
											pp = Wenfa[j].charAt(rt);
											temp = temp + re_first(First,Wenfa[j].charAt(rt)).substring(0, re_first(First,Wenfa[j].charAt(rt)).length()-1);
											rt++;
										}
										if(is_end(pp)) {
											temp = temp + pp;
										}
										else {
											if(rt==max) {
												temp = temp + re_first(First,Wenfa[j].charAt(rt-1)).substring(0, re_first(First,Wenfa[j].charAt(rt-1)).length());
											}
											else {
												temp = temp + re_first(First,Wenfa[j].charAt(rt)).substring(0, re_first(First,Wenfa[j].charAt(rt)).length());
											}
										}
									}
								}
								else {
									temp = temp + re_first(First,Wenfa[j].charAt(k+1));
								}
							}
						}
					}
				}
			}
			Follow[index] = temp;
	}
	
	public static void main(String[] args) {
		filein("D:\\wenfa.txt",Wenfa);
		filein("D:\\fuhao.txt",Fuhao);
		filein("D:\\first.txt",First);
		unend = new String(Fuhao[0]);
		end = new String(Fuhao[1]);
		for(int i = 0; i < (unend.length() + 1) / 2; i++){
			char op = unend.charAt(2 * i);
			s_follow(Follow, op, i);
		}
		for(int i = 0; Follow[i]!=null; i++){
			System.out.println(Follow[i]);
		}
		
		File file = new File("D:\\follow.txt");
        
        try {
            if(!file.exists())
            file.createNewFile();
             
            FileWriter out=new FileWriter (file);
            BufferedWriter bw= new BufferedWriter(out); 
            for(int i = 0; Follow[i]!=null; i++){
            	bw.write(Follow[i]);
            	bw.newLine();
    		}     
            bw.flush();
            bw.close();
                             
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
