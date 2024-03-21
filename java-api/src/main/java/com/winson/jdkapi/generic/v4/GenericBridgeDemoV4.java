package com.winson.jdkapi.generic.v4;

/**
 * @author mike ian
 * @date 2024/3/21
 * @desc
 **/
public class GenericBridgeDemoV4 {

    public static class Customer{

    }

    public static class Vip extends Customer{

    }

    public static class Merchant<T extends Customer> {

        public void action(T t){
            System.out.println("merchant action customer : " + t);
        }

    }

    public static class VipMerchant extends Merchant<Vip>{

        @Override
        public void action(Vip vip) {
            System.out.println("vip merchant action vip : " + vip);
        }
    }


    public static void main(String[] args) {
//        Merchant<Customer> customerMerchant = new Merchant<>();
//        customerMerchant.action(new Customer());

        Merchant<Vip> vipMerchant = new VipMerchant();
        vipMerchant.action(new Vip());
    }

}
