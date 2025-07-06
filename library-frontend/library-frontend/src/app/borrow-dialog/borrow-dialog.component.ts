import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-borrow-dialog',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './borrow-dialog.component.html',
  styleUrls: ['./borrow-dialog.component.scss'],
  
})
export class BorrowDialogComponent implements OnInit {
  @Input() book: any;
  @Output() close = new EventEmitter<void>();
  @Output() submitted = new EventEmitter<void>();


  borrower = {
    name: '',
    startDate: '',
    endDate: '',
    bookAuthor: '',
    bookTitle: '',
    phone: '',
    email: ''
};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    if (this.book) {
      this.borrower.bookAuthor = this.book.author;
      this.borrower.bookTitle = this.book.title;
    }
  }

  onSubmit(): void {
    if (
      this.borrower.name &&
      this.borrower.startDate &&
      this.borrower.endDate &&
      this.borrower.phone &&
      this.borrower.email
    ) {
      this.http.post('http://localhost:8080/api/borrowers', this.borrower).subscribe({
        next: () => {
          this.close.emit(); // bezárjuk az ablakot
        },
        error: err => {
          console.error('Hiba történt a mentés során:', err);
        }
      });
    }
    this.submitted.emit();
    this.close.emit();
    location.reload();
  }

  onCancel(): void {
    this.close.emit();
    location.reload();
  }
}
