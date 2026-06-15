import { Routes } from '@angular/router';
import { Home } from '../pages/home/home';
import {Research} from '../pages/research/research';
import {Study} from '../pages/study/study';
import {RegisterComponent} from './auth/register/register';
import { LoginComponent } from './auth/login/login';

export const routes: Routes = [
  { path: 'home', component: Home },
  { path: 'study', component: Study },
  { path: 'research', component: Research },
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'},
];
