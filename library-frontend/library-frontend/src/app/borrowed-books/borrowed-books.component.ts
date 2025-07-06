import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-borrowed-books',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './borrowed-books.component.html',
  styleUrls: ['./borrowed-books.component.scss']
})
export class BorrowedBooksComponent implements OnInit {
  borrowers: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  logout() {
    // Token-törlés, stb. ha van
    this.router.navigate(['/']);
  }

  toDashboard() {
    this.router.navigate(['/dashboard']);
  }

  refreshPage(){
    location.reload();
  }
  

  ngOnInit(): void {
    this.loadBorrowers();
  }

  loadBorrowers() {
    this.http.get<any[]>('http://localhost:8080/api/borrowers').subscribe(data => this.borrowers = data);
  }

  returnBook(borrower: any) {
    this.http.request('delete', 'http://localhost:8080/api/borrowers', { body: borrower }).subscribe(() => {
      this.loadBorrowers();
      location.reload(); 
    });
  }
}