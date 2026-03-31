package com.project.fitness.Service;

import com.project.fitness.Security.JwtUtils;
import com.project.fitness.DTO.LoginRequest;
import com.project.fitness.DTO.LoginResponse;
import com.project.fitness.DTO.RegisterRequest;
import com.project.fitness.DTO.UserResponse;
import com.project.fitness.Entity.User;
import com.project.fitness.Entity.type.RoleType;
import com.project.fitness.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public UserResponse register(RegisterRequest request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(RoleType.USER)
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {

        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(passwordEncoder.encode(savedUser.getPassword()));
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }

    public @Nullable LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        ); // check the Authenticate or not

        //you need to convert user
        User user = (User) authentication.getPrincipal();

        String token = jwtUtils.generateToken(user);
        return new LoginResponse(token, user.getId(), user.getEmail());







//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getEmail(),
//                            loginRequest.getPassword()
//                    )
//            );
//
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//            User user = (User) userRepository.findByEmail(userDetails.getUsername())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            String token = jwtUtils.generateToken(
//                    user.getId().toString(), // ✅ convert to String
//                    user.getRoles().name()
//            );
//
//            return new LoginResponse(
//                    token,
//                    user.getId().toString(),
//                    user.getEmail()
//            );
//
//        } catch (BadCredentialsException e) {
//            throw new IllegalArgumentException("Invalid email or password!");
//        }
    }
}