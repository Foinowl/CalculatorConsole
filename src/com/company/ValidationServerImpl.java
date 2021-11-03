package com.company;

public class ValidationServerImpl implements ValidationServer{
    @Override
    public boolean canBuildNumber(String token) {
        return token.matches("\\d*\\d+\\.?\\d*");
    }
}
