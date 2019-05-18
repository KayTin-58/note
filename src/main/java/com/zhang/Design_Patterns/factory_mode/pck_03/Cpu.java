package com.zhang.Design_Patterns.factory_mode.pck_03;

public interface Cpu {
    void run();

    class Cpu650 implements Cpu {
        @Override
        public void run() {
            //625 也厉害
        }
    }

    class Cpu825 implements Cpu {
        @Override
        public void run() {
            //825 处理更强劲
        }
    }
}