package com.example.miji.classsave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> temp = new ArrayList<String>();
    ArrayList<Integer> result = new ArrayList<Integer>();

    //클래스 노드 객체 생성
    ArrayList<ClassNode> c = new ArrayList<ClassNode>();

    //hashMap
    Map<String,Map<String,Double>> cityMap = new HashMap<>();
    String prevKeyName = null;

    int classTime;
    int destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillClassNode();
        fillHashMap();

    }

    public void fillClassNode(){
        try {
            InputStream is = getApplicationContext().getResources().getAssets().open("class.xls");
            Workbook wb = Workbook.getWorkbook(is);
            if(wb != null){
                Sheet sheet  = wb.getSheet(0); //시트 불러오기
                if(sheet != null){

                    int colTotal = sheet.getColumns(); //전체컬럼
                    int rowIndexStart = 1; //row인덱스 시작
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    StringBuilder sb;

                    for(int row=rowIndexStart;row<rowTotal;row++){
                        sb = new StringBuilder();
                        for(int col=0;col<colTotal;col++){
                            String contents = sheet.getCell(col,row).getContents();
                            temp.add(contents);
                            sb.append("col"+col+" : "+contents+" , ");
                        }
                        Log.i("test",sb.toString());
                        ClassNode classnode = new ClassNode();
                        classnode.setClassName(temp.get(0));

                        for(int i=0;i<9;i++) {
                            if(temp.get(1+i) != "") {
                                result.add(Integer.parseInt(temp.get(1+i)));
                            } else {
                                result.add(0);
                            }

                        }
                        classnode.setMonday(result);
                        result.clear();

                        for(int i=0;i<9;i++) {
                            if(temp.get(10+i) != "") {
                                result.add(Integer.parseInt(temp.get(10+i)));
                            } else {
                                result.add(0);
                            }
                        }
                        classnode.setTuesday(result);
                        result.clear();

                        for(int i=0;i<9;i++) {
                            if(temp.get(19+i) != "") {
                                result.add(Integer.parseInt(temp.get(19+i)));
                            } else {
                                result.add(0);
                            }
                        }
                        classnode.setWednesday(result);
                        result.clear();

                        for(int i=0;i<9;i++) {
                            if(temp.get(28+i) != "") {
                                result.add(Integer.parseInt(temp.get(28+i)));
                            } else {
                                result.add(0);
                            }
                        }
                        classnode.setThursday(result);
                        result.clear();

                        for(int i=0;i<9;i++) {
                            if(temp.get(37+i) != "") {
                                result.add(Integer.parseInt(temp.get(37+i)));
                            } else {
                                result.add(0);
                            }
                        }
                        classnode.setFriday(result);
                        result.clear();
                        classnode.setNearbyElevator(temp.get(46));

                        c.add(classnode);
                        Log.d("Check",c.get(row-1).getClassName());

                        temp.clear();


                    }

                }
            }

        } catch(IOException e){
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public void fillHashMap(){
        try {
            Log.i("DataSet","DataSet호출 try시작");
            InputStream is = getApplicationContext().getResources().getAssets().open("test.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Log.d("wb",wb.toString());
            if(wb != null){
                Sheet sheet  = wb.getSheet(0); //시트 불러오기 지4, 7 층 데이터
                if(sheet != null) {
                    int colTotal = sheet.getColumns();
                    int rowindexStart = 1;  //row인덱스 시작
                    int rowTotal = sheet.getRows();
                    Log.i("rowTotal",String.valueOf(rowTotal));
                    Log.i("DataSet", "DataSet호출 if문시작");
                    StringBuilder sb;
                    for (int row = rowindexStart; row < rowTotal; row++) {
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
            Log.i("Map값",cityMap.get("A").get("G").toString());
            Log.i("Map값전체",cityMap.values().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
