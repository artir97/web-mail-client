import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { RegisterRequest } from '../auth.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class RegisterComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  firstName = '';
  lastName = '';
  email = '';
  password = '';
  confirmPassword = '';

  successMessage = signal('');
  errorMessage = signal('');
  isLoading = signal(false);

  register() {
    if (this.isLoading()) {
      return;
    }

    this.successMessage.set('');
    this.errorMessage.set('');

    if (this.password !== this.confirmPassword) {
      this.errorMessage.set('Passwords do not match.');
      return;
    }

    const registerRequest: RegisterRequest = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
    };

    this.isLoading.set(true);

    this.authService.register(registerRequest).subscribe({
      next: () => {
        this.successMessage.set('User created successfully. You can now log in.');
        this.errorMessage.set('');
        this.isLoading.set(false);

        this.firstName = '';
        this.lastName = '';
        this.email = '';
        this.password = '';
        this.confirmPassword = '';

        this.router.navigate(['/login']);
      },

      error: error => {
        console.log(error);

        this.successMessage.set('');
        this.isLoading.set(false);

        if (error.status === 409) {
          this.errorMessage.set('This email address is already taken.');
          return;
        }

        if (error.status === 400) {
          this.errorMessage.set(
            'Password must be at least 8 characters long and contain one letter, one number, and one special character.'
          );
          return;
        }

        this.errorMessage.set('Registration failed.');
      },
    });
  }
}
