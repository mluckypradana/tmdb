#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_luc_base_core_helper_JNIUtil_apiEndpoint(JNIEnv *env, jobject) {
    return env->NewStringUTF(
#ifdef MOCK
            "https://api.themoviedb.org/3/"
#endif
#ifdef DEV
            "https://api.themoviedb.org/3/"
#endif
#ifdef PROD
            "https://api.themoviedb.org/3/"
#endif
    );
}
