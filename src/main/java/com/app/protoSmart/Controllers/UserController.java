package com.app.protoSmart.Controllers;


import com.app.protoSmart.Entities.User;
import com.app.protoSmart.Repositories.UserRepository;
import com.app.protoSmart.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/api/userData/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/change-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestBody ChangePasswordRequest request, Principal principal) {

        String userName = principal.getName();

        User user = null;
        Optional<User> userOpt = userRepository.findByUserName(userName);
        ChangePasswordResponse response = new ChangePasswordResponse();

        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else {
            response.setError(true);
            response.setMessage("User Not found in Session.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            response.setError(true);
            response.setMessage("Current password does not match our records.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        String newToken = jwtService.generateToken(user);
        long expiresIn = jwtService.getExpirationTime();


        response.setUser(user);
        response.setToken(newToken);
        response.setExpiresIn(expiresIn);
        response.setMessage("Password changed Successfully");
        response.setError(false);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    static class ChangePasswordRequest {

        private String oldPassword;
        private String newPassword;

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    static class ChangePasswordResponse {
        private String token;

        private long expiresIn;

        private User user;

        private String message;

        private boolean error;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }
    }
}
