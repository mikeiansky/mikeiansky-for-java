package com.winson.jdkapi.generic.v2;

/**
 * @author winson
 * @date 2022/6/8
 **/
public class GenericExtendDemoV1 {

    public interface Action {

    }

    public interface ActionOne extends Action {
        void doOneThing();
    }

    public interface ActionTwo extends Action {
        void doTwoThing();
    }

    public interface ActionListener<A extends Action> {
        void doAction(A action);
    }

    public interface GenericOneActionListener extends ActionListener<ActionOne> {
        GenericOneActionListener instance = new GenericOneActionListener() {
            @Override
            public void doAction(ActionOne action) {
                System.out.println("action one ready ... ");
                action.doOneThing();
            }
        };
    }

    public static class AllHolder {

        ActionListener<?> listener;

        public void setListener(ActionListener<? extends Action> listener){
            this.listener = listener;
        }

        public void notifyListener(ActionOne actionOne) {
            notifyListenerReal(actionOne, listener);
        }

        public void notifyListenerReal(Action action, ActionListener listener){
            listener.doAction(action);
        }

    }

    public static void main(String[] args) {
        AllHolder allHolder = new AllHolder();
        allHolder.setListener(GenericOneActionListener.instance);
        allHolder.notifyListener(new ActionOne() {
            @Override
            public void doOneThing() {
                System.out.println("im action one");
            }
        });

        // 这里是错误的，实际参数不匹配，泛型擦除的问题
        allHolder.notifyListenerReal(new ActionTwo() {
            @Override
            public void doTwoThing() {
                System.out.println("two thing dow ... ");
            }
        }, GenericOneActionListener.instance);

    }

}
