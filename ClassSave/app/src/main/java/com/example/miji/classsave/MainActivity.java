package com.example.miji.classsave;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    //같은 층, 같은 구역의 배열 번호가 몇번인지 저장할 array
    ArrayList<Integer> forSameElevatorIndex = new ArrayList<Integer>();

    //hashMap
    Map<String,Map<String,Double>> cityMap = new HashMap<>();
    String prevKeyName = null;

    int classTime;
    String destination;
    int destinationIndex; //몇번째 index에 해당강의실 정보가 있는지 저장
    int destinationStair;
    int nearPeople=0; // 같은 구역에 있는 사람 수
    String destinationNearbyElevator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillClassNode();
        fillHashMap();
        classTime = 5; // 해당교시
        destination="727"; //해당강의실

        //해당 강의실이 몇번째 인덱스에 존재하는지 찾기위해서
        for(int i =0;i<c.size();i++){
            if (destination.equals(c.get(i).getClassName()))  {
                destinationIndex = i;
            }
        }
        destinationNearbyElevator = c.get(destinationIndex).getNearbyElevator(); //그 강의실의 해당구역

        //같은층, 같은구역에 있는 강의실들의 인덱스를 조사하기 위해서
        for(int i =0;i<c.size();i++){
            if (c.get(destinationIndex).getStair().equals(c.get(i).getStair()))  { //층수가 같은 것들 중에서
                if(c.get(destinationIndex).getNearbyElevator().equals(c.get(i).getNearbyElevator())){ //구역이같은곳
                    forSameElevatorIndex.add(i);

                }
            }
        }

        for(int i=0;i<forSameElevatorIndex.size();i++){
            int temp = forSameElevatorIndex.get(i); //같은 구역의 강의실 인덱스를 데려옴
            ArrayList<Integer> tempFri = new ArrayList<>();
            tempFri=c.get(temp).getFriday(); //그 인덱스의 금요일 시간표를 tempFri에 접근
///////////인덱스 저장된것까진 확인했는데 금요일 배열이 안불려져와서 접근이 불가능함!!!

            Log.d("fri", tempFri.toString());
//          nearPeople += tempFri.get(classTime-1); // 지정해둔 classTime의 인원을 nearPeople(전체 명수)에 더함

        }
        String nn = Integer.toString(nearPeople);
        Log.d("근처", nn);



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

                    for(int row=rowIndexStart;row<rowTotal;row++){
                        for(int col=0;col<colTotal;col++){
                            String contents = sheet.getCell(col,row).getContents();
                            temp.add(contents);
                        }

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
                        classnode.setStair(temp.get(47));

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

//    int dijkstra(String startNode, String endNode, HashMap<String, HashMap<String, Integer>> wholeGraph) {
//        int n = wholeGraph.size();     //Node의 수
//        int e = 0;                     //Edge의 수
//        e = wholeGraph.keySet().stream().map((key) -> wholeGraph.get(key).size()).reduce(e, Integer::sum); //Edge의 수를 계산
//
//        Map<String, Integer> keyToInteger = new HashMap<>(); // 노드들에 int index부여. 노드이름으로 index를 찾을수 있는 Map
//        int keyIndex = 0;
//        for (Object key : wholeGraph.keySet()) {
//            keyToInteger.put(key.toString(), keyIndex);
//            keyIndex++;
//        }
//        Map<Integer, String> integerToKey = new HashMap<>(); // index로 노드이름을 찾을수 있는 Map
//        keyToInteger.keySet().forEach((key) -> {
//            integerToKey.put((int) keyToInteger.get(key), key.toString());
//        });
//
//        int distance[] = new int[n];          //최단 거리를 저장할 변수
//        boolean[] check = new boolean[n];     //해당 노드를 방문했는지 체크할 변수
//        int maps[][] = new int[n][n];         //노드 간의 거리 배열
//        wholeGraph.keySet().forEach((i) -> {  //노드 간의 거리 계산 및 저장
//            wholeGraph.get(i).keySet().forEach((j) -> {
//                maps[keyToInteger.get(i)][keyToInteger.get(j)] = wholeGraph.get(i).get(j);
//            });
//        });
//
//        int v = keyToInteger.get(startNode);
//
//        //distance값 초기화.
//        for (int i = 0; i < n; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
//
//        //시작노드값 초기화.
//        distance[v] = 0;
//        check[v] = true;
//
//        //연결노드 distance갱신
//        for (int i = 0; i < n; i++) {
//            if (!check[i] && maps[v][i] != 0) {
//                distance[i] = maps[v][i];
//            }
//        }
//
//        Map<Integer, Integer> temp = new LinkedHashMap<>();
//        int tempp = 0;
//        int minDistance = 0
//
//        for (int a = 0; a < n - 1; a++) {
//            //원래는 모든 노드가 true될때까지 인데
//            //노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
//            //원하지 않으면 각각의 노드가 모두 true인지 확인하는 식으로 구현해도 된다.
//            int min = Integer.MAX_VALUE;
//            int min_index = -1;
//
//            //최소값 찾기
//            for (int i = 0; i < n; i++) { //기준 노드에서 인접 노드중 가장 가까운 노드 선택
//                if (!check[i] && distance[i] != Integer.MAX_VALUE) { //체크안되있고 거리가 무한이 아닌 노드
//                    if (distance[i] < min) {
//                        min = distance[i];
//                        min_index = i;
//                        tempp = i;
//                    }
//                }
//            }
//            check[min_index] = true;
//            for (int i = 0; i < n; i++) {
//                if (!check[i] && maps[min_index][i] != 0) {
//                    if (distance[i] > distance[min_index] + maps[min_index][i]) {
//                        distance[i] = distance[min_index] + maps[min_index][i];
//                    }
//                    if (i == keyToInteger.get(endNode)) {
//                        temp.put(tempp, distance[i]);
//                        minDistance = distance[i];
//                    }
//                }
//            }
//        }
//        return minDistance;
//    }
}
