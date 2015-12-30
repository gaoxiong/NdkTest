//
// Created by xgao on 30/12/2015.
//

#ifndef NDKTEST_NDK_LOAD_H
#define NDKTEST_NDK_LOAD_H

#define NELEM(x) ((int) (sizeof(x)) / sizeof((x)[0]))

#define JNI_REG_CLASS "com/snaptube/ndktest/MainActivity"

JNIEXPORT jstring JNICALL native_hello(JNIEnv* env, jclass clazz);

#endif //NDKTEST_NDK_LOAD_H
