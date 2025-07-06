import { Routes } from '@angular/router';
import { LoginComponent } from './login/login';
import { DashboardComponent } from './dashboard/dashboard.component'; 
import { BorrowedBooksComponent } from './borrowed-books/borrowed-books.component';



export const routes: Routes = [ 
  { path: '', component: LoginComponent, data: { animation: 'LoginPage' } },
  { path: 'dashboard', component: DashboardComponent, data: { animation: 'DashboardPage' } },
  { path: 'kolcsonzesek', component: BorrowedBooksComponent, data: { animation: 'BorrowedPage' } } 
];
