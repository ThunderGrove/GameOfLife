
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Controller{
    private ArrayList<ArrayList<Circle>>cellsGUI=new ArrayList<ArrayList<Circle>>();
    private boolean updateInProcess=false;//Used to lock a method from run multiple threads in parallel.
    private boolean[][]cells={
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
    };
    private ArrayList<String>logList=new ArrayList<>();
    private int logCount=0;
    private Random r=new Random();
    private int threadSpeed=1000;

    private Task cellsThread;
    private Task logUpdateThread;
    private boolean cellsThreadRunning=false;

    //The below links elements with fx:id in the window.fxml to this controller.
    @FXML private Pane lifeZone;
    @FXML private MenuItem conStart;
    @FXML private MenuItem conPause;
    @FXML private MenuItem speedOne;
    @FXML private MenuItem speedTwo;
    @FXML private MenuItem speedThree;
    @FXML private MenuItem speedFour;
    @FXML private MenuItem speedFive;
    @FXML private ListView lv;


    @FXML
    public void initialize(){
        for (int ia=0;ia<32;ia++){
            cellsGUI.add(new ArrayList<Circle>());
            for(int ib=0;ib<32;ib++) {
                cellsGUI.get(ia).add(new Circle(10+(ia*20),10+(ib*20),9,Color.CRIMSON));
                lifeZone.getChildren().add(cellsGUI.get(ia).get(ib));
            }
        }
        randomCellState();

        logUpdateThread=new Task<Void>(){
            @Override
            public Void call() throws Exception{
                while(true){
                    Platform.runLater(()->updateLog());
                    Thread.sleep(100);
                }
            }
        };
        new Thread(logUpdateThread).start();

        conStart.setOnAction(event->startCycle());
        conPause.setOnAction(event->pauseCycle());
        speedOne.setOnAction(event->setThreadSpeed(1));
        speedTwo.setOnAction(event->setThreadSpeed(2));
        speedThree.setOnAction(event->setThreadSpeed(3));
        speedFour.setOnAction(event->setThreadSpeed(4));
        speedFive.setOnAction(event->setThreadSpeed(5));
    }

    private void startCycle(){
        if(!cellsThreadRunning){
            cellsThreadRunning=true;
            cellsThread=new Task<Void>(){
                @Override
                public Void call()throws Exception{
                    while(cellsThreadRunning){
                        Platform.runLater(()->updateCellState());
                        Thread.sleep((threadSpeed));
                    }
                    return null;
                }
            };
            new Thread(cellsThread).start();
        }else{
            System.out.println("Cells Thread already running.");
        }
    }

    private void pauseCycle(){
        cellsThreadRunning=false;
    }

    private void setThreadSpeed(int step){
        switch (step){
            case 1:
                threadSpeed=1000;
                break;
            case 2:
                threadSpeed=250;
                break;
            case 3:
                threadSpeed=100;
                break;
            case 4:
                threadSpeed=40;
                break;
            case 5:
                threadSpeed=20;
                break;
            default:
                threadSpeed=200;
        }
    }

    private void randomCellState(){
        for(int ia=0;ia<32;ia++){
            for(int ib=0;ib<32;ib++){
                if(r.nextBoolean()){
                    cells[ia][ib] = r.nextBoolean();
                }
                if(cells[ia][ib]){
                    cellsGUI.get(ia).get(ib).setFill(Color.DARKSEAGREEN);
                }else{
                    cellsGUI.get(ia).get(ib).setFill(Color.CRIMSON);
                }
            }
        }
    }

    private void updateCellState(){
        if(updateInProcess){
            System.out.println("Thread lock active");
            return;
        }
        updateInProcess=true;
        boolean[][]newCells=cells;
        int count=0;
        for (int ia=0;ia<32;ia++){
            for(int ib=0;ib<32;ib++){

                if(ia-1!=-1){
                    if (ib-1!=-1){
                        if(cells[ia-1][ib-1]){
                            count++;
                        }
                    }
                    if (cells[ia - 1][ib]) {
                        count++;
                    }
                    if(ib+1!=32){
                        if (cells[ia-1][ib+1]){
                            count++;
                        }
                    }
                }

                if(ib-1!=-1){
                    if (cells[ia][ib-1]){
                        count++;
                    }
                }
                if (ib+1!=32){
                    if(cells[ia][ib+1]){
                        count++;
                    }
                }

                if(ia+1!=32){
                    if(ib-1!=-1){
                        if(cells[ia+1][ib-1]){
                            count++;
                        }
                    }
                    if(cells[ia+1][ib]){
                        count++;
                    }
                    if(ib+1!=32){
                        if (cells[ia+1][ib+1]){
                            count++;
                        }
                    }
                }

                if(cells[ia][ib]){
                    if(count==2||count==3){
                        newCells[ia][ib]=true;
                        logList.add("Cell:"+ia+"-"+ib+" did not die."+count);
                    }else{
                        newCells[ia][ib]=false;
                        logList.add("Cell:"+ia+"-"+ib+" have died."+count);
                    }
                }else{
                    if(count==3){
                        newCells[ia][ib]=true;
                        logList.add("Cell:"+ia+"-"+ib+" is born."+count);
                    }
                }

                count=0;
            }
        }

        cells=newCells;

        for(int ia=0;ia<32;ia++){
            for(int ib=0;ib<32;ib++){
                if(cells[ia][ib]){
                    cellsGUI.get(ia).get(ib).setFill(Color.DARKSEAGREEN);
                }else{
                    cellsGUI.get(ia).get(ib).setFill(Color.CRIMSON);
                }
            }

        }
        updateInProcess=false;
    }

    private void updateLog(){
        for(int i=lv.getItems().size();i<logList.size();i++){
            lv.getItems().add(logList.get(i));
            lv.scrollTo(lv.getItems().size());
        }
    }
}