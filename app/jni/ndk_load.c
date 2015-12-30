//
// Created by xgao on 30/12/2015.
//

#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <jni.h>
#include <assert.h>

#include "ndk_load.h"

static JNINativeMethod g_method[] = {
  { "helloWorld", "()Ljava/lang/String;", (void*)native_hello },
  { "setString", "()Ljava/lang/String;", (void*)set_string },
};

JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved) {
  JNIEnv *env = NULL;
  jint result = -1;

  if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_4) != JNI_OK) {
    return result;
  }

  register_ndk_load(env);

  return JNI_VERSION_1_4;
}

int register_ndk_load(JNIEnv *env) {
  return registerNativeMethod(env, JNI_REG_CLASS, g_method, NELEM(g_method));
}

int registerNativeMethod(JNIEnv *env, const char* className,
                         JNINativeMethod* gMethods, int numMethods) {
  jclass clazz;
  clazz = (*env)->FindClass(env, className);
  if (clazz == NULL) {
    return JNI_FALSE;
  }
  if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
    return JNI_FALSE;
  }
  return JNI_TRUE;
}

JNIEXPORT jstring JNICALL native_hello(JNIEnv* env, jclass clazz) {
  return (*env)->NewStringUTF(env, "hello load jni.");
}

JNIEXPORT void JNICALL set_string(JNIEnv* env, jclass clazz) {

}
