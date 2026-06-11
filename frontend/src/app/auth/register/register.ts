import {Component, inject} from '@angular/core';
import {AuthService} from '../auth.service';
import {RegisterRequest} from '../auth.model';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [
    FormsModule
  ],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class RegisterComponent {
  private authService = inject(AuthService)

  firstName = '';
  lastName = '';
  email = '';
  password = '';
  confirmPassword = '';

  successMessage = '';
  errorMessage = '';
  isLoading = false;

  register() {
    this.successMessage = '';
    this.errorMessage = '';

    if(this.password != this.confirmPassword){
      this.errorMessage = 'Passwords do not match';
      return;
    }

    const registerRequest: RegisterRequest = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password
    };

    this.authService.register(registerRequest).subscribe({
      next: () => {
        this.successMessage = 'User created successfully.';
        this.errorMessage = '';

        this.firstName = '';
        this.lastName = '';
        this.email = '';
        this.password = '';
        this.confirmPassword = '';
      },
      error: error => {
        if (error.status === 409) {
          this.errorMessage = 'This email address is already taken.';
          return;
        }

        if (error.status === 400) {
          this.errorMessage = 'Please check your input.';
          return;
        }

        this.errorMessage = 'Registration failed.';
      }
    })
  }
}
