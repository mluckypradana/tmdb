#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_id_co_tmdb_core_helper_JNIUtil_apiEndpoint(JNIEnv *env, jobject) {
    return env->NewStringUTF("https://api.themoviedb.org/3/" );
}
extern "C" JNIEXPORT jstring JNICALL
Java_id_co_tmdb_core_helper_JNIUtil_apiKey(JNIEnv *env, jobject) {
    return env->NewStringUTF("b97bf80c40219ed9d2196c1309587621");
}
