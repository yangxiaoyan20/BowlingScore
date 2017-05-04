package main.java;
public class BowlingGame {
    public int getBowlingScore(String bowlingCode) {
	    String[] first=bowlingCode.split("\\|\\|");
        String[] second = first[0].split("\\|"); //String[] aa = "aaa|bbb|ccc".split("\\|"); �������ܵõ���ȷ�Ľ��
		int[][] scores = new int[11][4];
        scores=parseInputString(first,second);
        /*����������ַ����������ά������----end**/
        int result=getScore(scores);
        System.out.println(result + "\t");
		return result;
	}
	
	 public static int getScore(int[][] scores){
        int res=0;
        for (int i = 0; i < scores.length-1; i++) {
		          //ǰ11��
            if (scores[i][0] == 10 && i != 9) {
                if (scores[i + 1][0] == 10 && i < 8){ 
				     //ǰ8�֣�һ��10�� �ͼǺ���2Ͷ��ķ���
                    scores[i][2] = scores[i][0] + scores[i + 1][0]  + scores[i + 2][0];
                }else if (scores[i + 1][0] != 10 || i == 8){
				        //��9��10�� �ǵ�10��2�εķ���
                    scores[i][2] = scores[i][0]  + scores[i + 1][0]  + scores[i + 1][1];
                }
            }else{              
                if (scores[i][0] + scores[i][1] == 10 && i < 9){
				       //ǰ9�� ����10�� �������ֵ�һ�εķ���
                    scores[i][2] = scores[i][0] + scores[i][1] + scores[i + 1][0];
                }else{
				         //2�β���10�� �ͼ���ô��
                    scores[i][2] = scores[i][0] + scores[i][1];
                }
            }
        }
        scores[0][3] = scores[0][2];
        for(int i = 1; i < scores.length;i++ ){
                scores[i][3]=scores[i][2] + scores[i - 1][3];
        }
        
        res=scores[scores.length-1][3];
        return res;
    }
    

    private static int[][] parseInputString(String[] first, String[] aa) {
        // TODO Auto-generated method stub
        int[][] scores = new int[11][4];
        for (int i = 0 ; i <aa.length ; i++ ) {                  
            if(aa[i].indexOf("X")!=-1){
                scores[i][0]=10;
                scores[i][1]=0;               
            }
            if(aa[i].indexOf("/")!=-1){
                scores[i][0]=Integer.parseInt(aa[i].split("/")[0]);
                scores[i][1]=10- scores[i][0];  
            }
               if(aa[i].indexOf("-")!=-1){             
                         if(aa[i].indexOf("-")==0){
                             scores[i][0]=0;                        
                             scores[i][1]= Integer.parseInt(aa[i].split("-")[1]);                        
                            }
                         else if(aa[i].indexOf("-")>=1){                     
                             scores[i][0]=Integer.parseInt(aa[i].split("-")[0]);   
                             scores[i][1]=0;   
                            }                
                }
                if(aa[i].equals("")){                       
                    i=i-1;
                    continue;
                }                 
        }
    //����|| ֮����ַ���ʾ���һ��Ķ������
            if(first.length>=2){
                    if(first[1].length()==1){
                      //����||����ΪX
                       if(first[1].equals("X")==true){
                           scores[10][0] =10;
                           scores[10][2] =10;
                        }
                     //����||����Ϊ5
                       if(Integer.valueOf(first[1])<=10){
                           scores[10][0] =Integer.valueOf(first[1]);
                           scores[10][2] =Integer.valueOf(first[1]);
                       }                
                    }
                    else  if(first[1].length()==2){   
                     //����||����ΪXX
                     if(first[1].equals("XX")){ 
                         scores[9][1]=10;
                         scores[10][0]=10;
                         scores[10][2]=10;
                     }
                     if(first[1].equals("XX")!=true  && first[1].indexOf("X")!=-1){
                         //����||����X5
                         if(first[1].indexOf("X")==0){
                             scores[9][1]=10;
                             scores[10][0]=Integer.parseInt(first[1].substring(1,first[1].length()));
                             scores[10][2]=scores[10][0];
                         }
                         //����||����Ϊ5X
                         else if(first[1].indexOf("X")>=1){
                             scores[9][1]=Integer.parseInt(first[1].substring(0,first[1].indexOf("X")));
                             scores[10][0]=10;
                             scores[10][2]=scores[10][0];
                         }
                        
                        } 
                     //����||����Ϊ81
                      if(first[1].equals("XX")!=true  &&first[1].indexOf("X")==-1 && Integer.valueOf(first[1])>=10){
                        scores[9][1]=Integer.parseInt(first[1].substring(0,1));
                        scores[10][0]=Integer.parseInt(first[1].substring(1,2));
                        scores[10][2]=scores[10][0];
                       }                 
                    }
            }
            return scores;
	 }
}
