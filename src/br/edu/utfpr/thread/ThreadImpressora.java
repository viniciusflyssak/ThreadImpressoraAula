package br.edu.utfpr.thread;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ThreadImpressora implements Runnable{
    
    private JTextArea taSaida;  
    private boolean pausar;
    private boolean parar;

    public boolean isParar() {
        return parar;
    }

    public void setParar(boolean parar) {
        this.parar = parar;
    }

    public boolean getPausar() {
        return pausar;
    }

    public synchronized void pausar(){
        while(pausar){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
    public synchronized void setPausar(boolean pausar) {
        this.pausar = pausar;
        if(!this.pausar){
            notify();
        }        
    }
    
    public ThreadImpressora(JTextArea taSaida){
        this.taSaida = taSaida; 
        this.pausar = false;
        this.parar = false;
    }
    
    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            try{
                pausar();
                Thread.sleep(500);
                taSaida.append("Linha" +i + "\n");
                if(parar){
                    return;
                }
            } catch (InterruptedException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
    
}
