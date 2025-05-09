package uoc.tfg.cvelascofa.pageturner_backend.shared.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/user")
    public String testUserRole() {
        return "✅ User Role Access: Success! This route is accessible to any user.";
    }

    @GetMapping("/test/librarian")
    public String testLibrarianRole() {
        return "✅ Librarian Role Access: Success! This route is accessible to Librarians and Admins.";
    }

    @GetMapping("/test/admin")
    public String testAdminRole() {
        return "✅ Admin Role Access: Success! This route is accessible only to Admins.";
    }

    @GetMapping("/test/public")
    public String testPublicAccess() {
        return "🚀 Public Access: This route is accessible to everyone.";
    }
}