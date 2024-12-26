package com.example.backend.Auth;

public class AuthenticationResponse {

    private String token;

    // Private constructor to enforce builder usage
    private AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Static builder class for AuthenticationResponse
    public static class Builder {
        private String token;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token);
        }
    }

    // Static builder method
    public static Builder builder() {
        return new Builder();
    }
}
