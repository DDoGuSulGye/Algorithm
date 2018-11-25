package com.example.leey.datastructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class MainActivity extends AppCompatActivity {

    Map<String,Map<String,Integer>> cityMap = new HashMap<>();

     int getID;
     int getData;

    public void GetData(int id, int data){
        this.getID= id;
        this.getData = data;
    }
    public int GetData(){
        return getData;
    }
    public int GetID(){
        return getID;
    }
    //지3-> 엘리베이터 -> 6층 (나머지 데이터 x)
    // 층별로 나누려고 한거고
    //
    //1-D
    //2-D

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String prevKeyName = null;

        try {
            Log.i("DataSet","DataSet호출 try시작");
            InputStream is = getApplicationContext().getResources().getAssets().open("test.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Log.d("wb",wb.toString());
            if(wb != null){
                Sheet sheet  = wb.getSheet(0); //시트 불러오기
                if(sheet != null){
                    int colTotal = sheet.getColumns();
                    int rowindexStart = 1;  //row인덱스 시작
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    Log.i("DataSet","DataSet호출 if문시작");
                    StringBuilder sb;
                    for(int row = rowindexStart;row<rowTotal;row++){
                        sb = new StringBuilder();

                            String srcNode =  sheet.getCell(0,row).getContents();
                            String destNode = sheet.getCell(1,row).getContents();
                            int weight = Integer.parseInt(sheet.getCell(2,row).getContents().toString());
                                if(prevKeyName != null && prevKeyName.equals(srcNode)){
                                    cityMap.get(srcNode).put(destNode,weight);
                                }else{
                                    cityMap.put(srcNode,new HashMap<String, Integer>());
                                    cityMap.get(srcNode).put(destNode,weight);
                                }
                        prevKeyName = srcNode;
                            }
                          //  Log.i("Contents",contents);
                           // sb.append("col"+col+":"+contents+",");
                        }
                    }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}

