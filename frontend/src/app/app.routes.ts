import { Routes } from '@angular/router';
import { Home } from '../pages/home/home';
import {Research} from '../pages/research/research';
import {Study} from '../pages/study/study';
import {RegisterComponent} from './auth/register/register';
import { LoginComponent } from './auth/login/login';
import { authGuard } from './auth/auth.guard';

export const routes: Routes = [
  { path: 'home', component: Home, canActivate: [authGuard] },
  { path: 'study', component: Study, canActivate: [authGuard] },
  { path: 'research', component: Research, canActivate: [authGuard] },
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'},
];
