LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE     := ndk_load
LOCAL_SRC_FILES  := ndk_load.c

include $(BUILD_SHARED_LIBRARY)

LOCAL_PATH := $(call my-dir)