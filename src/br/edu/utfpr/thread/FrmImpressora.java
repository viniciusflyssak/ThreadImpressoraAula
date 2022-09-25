/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.thread;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author andre
 */
public class FrmImpressora extends JFrame {

    private JButton btPausa;
    private JButton btIniciar;
    private JButton btParar;
    private JTextArea taSaida;
    private Thread thread;

    public FrmImpressora() {
        btIniciar = new JButton("Iniciar");
        btPausa = new JButton("Pausar");
        btParar = new JButton("Parar");
        taSaida = new JTextArea();
        JScrollPane sp = new JScrollPane(taSaida);
        JPanel pnBotoes = new JPanel(new GridLayout(1, 1));
        pnBotoes.add(btIniciar);
        pnBotoes.add(btPausa);
        pnBotoes.add(btParar);

        btPausa.setEnabled(false);
        btParar.setEnabled(false);

        this.add(pnBotoes, BorderLayout.NORTH);
        this.add(sp, BorderLayout.CENTER);
        ThreadImpressora threadImpressora = new ThreadImpressora(taSaida);
        
        btIniciar.addActionListener((e) -> {
            threadImpressora.setParar(false);
            threadImpressora.setPausar(false);
            if((thread == null) || (thread.getState() == Thread.State.TERMINATED)){
                thread = new Thread(threadImpressora);
                thread.start();
                btParar.setEnabled(true);
                btPausa.setEnabled(true);
            }else if(thread.isAlive()){
                taSaida.append("Thread já está em execução!!");
            }
        });
        
        btPausa.addActionListener((e) -> {
            if ("Pausar".equals(btPausa.getText())){
                btPausa.setText("Continuar");
                threadImpressora.setPausar(true);
//                thread.suspend();
            }else{
                btPausa.setText("Pausar");   
                threadImpressora.setPausar(false);
//                thread.resume();
            }
        });
        
        btParar.addActionListener((e) -> {
//            thread.stop();
            threadImpressora.setParar(true);
            btPausa.setText("Pausar");
            btPausa.setEnabled(false);
            btParar.setEnabled(false);
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new FrmImpressora();
    }
}
