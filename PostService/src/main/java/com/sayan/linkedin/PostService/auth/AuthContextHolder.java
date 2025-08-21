package com.sayan.linkedin.PostService.auth;

public class AuthContextHolder {
    private static final ThreadLocal<Long> currentUserId = new ThreadLocal<>();

    public static Long getCurrentUserId(){
        return currentUserId.get();
    }
    static void setCurrentuserId(Long userId){
        currentUserId.set(userId);
    }
    static void clear(){
        currentUserId.remove();
    }
}
