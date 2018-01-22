#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_zmy_laosiji_jni_JNI_stringFromJNI(JNIEnv *env, jclass type) {

    // TODO

    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}