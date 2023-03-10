package emu;

import chip.Chip;

public class Main extends Thread {

    private Chip chip8;
    private ChipFrame frame;

    public Main() {
        chip8 = new Chip();
        chip8.init();
//        chip8.loadProgram("./pong2.c8");
        chip8.loadProgram("./invaders.c8");
        frame = new ChipFrame(chip8);
    }

    public void run() {
        // basic loop of a thread
        // 60 Hz, 60 updates per second
        while(true) {
            chip8.setKeyBuffer(frame.getKeyBuffer()); // send keys
            chip8.run();
            if(chip8.needsRedraw()) {
                frame.repaint();
                chip8.removeDrawFlag();
            }
            try {
                Thread.sleep(8); // speed of application, lower -> faster
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}
