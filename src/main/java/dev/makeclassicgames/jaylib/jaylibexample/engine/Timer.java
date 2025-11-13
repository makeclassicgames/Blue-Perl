package dev.makeclassicgames.jaylib.jaylibexample.engine;

public class Timer {
    private long maxTime;
    private long currentTime;
    private boolean started;
    private boolean loop;
    private TimerEvent event;

    public Timer(long maxTime,boolean loop, boolean autoStart,TimerEvent event ){
        this.maxTime= maxTime;
        this.currentTime=0;
        this.started= autoStart;
        this.loop= loop;
        this.event=event;
    }

    public void start(){
        this.started=true;
        this.currentTime=0;
    }

    public void stop(){
        this.started=false;
        this.currentTime=0;
    }

    public void pause(){
        this.started=!this.started;
    }

    public void update(){
        if(started){
            this.currentTime++;
            if(currentTime>=maxTime){
                event.runEvent();
                if(loop){
                    this.currentTime=0;
                }else{
                    this.started=false;
                }
            }
        }
    }

}
