package com.example.leey.datastructure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Number;


public class MainActivity extends AppCompatActivity {

    Map<String,Map<String,Double>> cityMap = new HashMap<>();

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
                if(sheet != null) {
                    int colTotal = sheet.getColumns();
                    int rowindexStart = 1;  //row인덱스 시작
                    int rowTotal = 47;

                    Log.i("DataSet", "DataSet호출 if문시작");
                    StringBuilder sb;
                    for (int row = rowindexStart; row < 47; row++) {
                        String srcNode = sheet.getCell(0, row).getContents();
                        String destNode = sheet.getCell(1, row).getContents();
                        //String tmpWeight = sheet.getCell(2, row).getContents();
                        Cell temp = sheet.getCell(2, row);
                        NumberCell nc = (NumberCell) temp;
                        double numberTemp = nc.getValue();
                      //  int weight = Integer.parseInt(tmpWeight);

                        if (prevKeyName != null && prevKeyName.equals(srcNode)) {
                        cityMap.get(srcNode).put(destNode, numberTemp);
                        } else {
                            cityMap.put(srcNode, new HashMap<String, Double>());
                          cityMap.get(srcNode).put(destNode, numberTemp);
                        }
                        prevKeyName = srcNode;
                    }
                }
            }
            Log.i("DataSet","값 입력 완료");
            Log.i("Map값",cityMap.get("D").get("310강의실").toString());
            Log.i("Map값전체",cityMap.values().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}

