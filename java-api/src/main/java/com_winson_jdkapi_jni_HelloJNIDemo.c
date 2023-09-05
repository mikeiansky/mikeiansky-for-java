#include <stdio.h>
#include "com_winson_jdkapi_jni_HelloJNIDemo.h"

JNIEXPORT void JNICALL Java_com_winson_jdkapi_jni_HelloJNIDemo_sayHello(JNIEnv *env, jobject obj) {
    printf("Hello from JNI!\n");
}
