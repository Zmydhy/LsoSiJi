#include <jni.h>
#include <string>

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_zmy_laosiji_jni_JNIT_stringFromTEST1(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "Test1 from C++";
    return env->NewStringUTF(hello.c_str());
}
JNIEXPORT jstring JNICALL
Java_com_zmy_laosiji_jni_JNIT_stringFromTEST2(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "Test2 from C++";
    return env->NewStringUTF(hello.c_str());
}
}

