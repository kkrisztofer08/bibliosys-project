import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { BookService } from '../services/book.service';
import { Book } from '../models/book.model';
import { BorrowDialogComponent } from '../borrow-dialog/borrow-dialog.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, BorrowDialogComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  showDialog = false;
  selectedBook: any;

  books: Book[] = [];
  constructor(private router: Router, private bookService: BookService) {}

  logout() {
    // Token-törlés, stb. ha van
    this.router.navigate(['/']);
  }

  toBorrowedbooks() {
    this.router.navigate(['/kolcsonzesek']);
  }

  refreshPage(){
    location.reload();
  }
  

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getAllBooks().subscribe({
      next: (data) => (this.books = data),
      error: (err) => console.error(err),
    });
  }

  borrow(book: Book): void {
    this.bookService.borrowBook(book.id).subscribe({
      next: () => this.loadBooks(),
      error: (err) => console.error(err),
    });
  }

  openDialog(book: any) {
    this.selectedBook = book;
    this.showDialog = true;
  }

  closeDialog() {
    this.selectedBook = null;
    this.showDialog = false;
    location.reload();
  }

  onBorrowSubmitted() {
  this.closeDialog();
  this.loadBooks();
  location.reload();
}

}
