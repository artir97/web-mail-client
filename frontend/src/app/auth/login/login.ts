import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LoginRequest } from '../auth.model'

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  email = '';
  password = '';

  successMessage = signal('');
  errorMessage = signal('');
  isLoading = signal(false);

  login() {
    this.successMessage.set('');
    this.errorMessage.set('');

    const loginRequest: LoginRequest = {
      email: this.email,
      password: this.password
    };

    this.isLoading.set(true);

    this.authService.login(loginRequest).subscribe({
      next: response => {
        localStorage.setItem('token', response.token);

        this.successMessage.set('Login successful.');
        this.errorMessage.set('');
        this.isLoading.set(false);

        this.email = '';
        this.password = '';

        this.router.navigate(['/']);
      },

      error: err => {
        console.log(err);

        this.successMessage.set('');
        this.isLoading.set(false);

        if (err.status === 401) {
          this.errorMessage.set('Invalid mail or password');
          return;
        }

        this.errorMessage.set('Login failed.');
      }
    })
  }
}
