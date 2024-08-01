package com.proyect.authAndUserModule.user.infraestructure.adapters.primary;


import com.proyect.authAndUserModule.user.domain.dtos.VerifyUserDto;
import com.proyect.authAndUserModule.user.infraestructure.adapters.secondary.AuthenticateService;
import com.proyect.authAndUserModule.user.infraestructure.adapters.secondary.CustomOAuth2User;
import com.proyect.authAndUserModule.user.domain.model.AuthenticationRequest;
import com.proyect.authAndUserModule.user.domain.model.AuthenticationResponse;
import com.proyect.authAndUserModule.user.domain.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticateService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
    @GetMapping("/oauth2/success")
    public ResponseEntity<AuthenticationResponse> oauth2Authentication(@AuthenticationPrincipal CustomOAuth2User principal) {
        return ResponseEntity.ok(authService.authenticateOAuth2(principal));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        try {
            authService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Todo: UPDATE USER


}