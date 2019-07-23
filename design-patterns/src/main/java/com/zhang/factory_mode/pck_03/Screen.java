package com.zhang.factory_mode.pck_03;

public interface Screen {


    void size();

    class Screen5 implements Screen {

        @Override
        public void size() {
            //5寸
        }
    }

    class Screen6 implements Screen {

        @Override
        public void size() {
            //6寸
        }
    }
}